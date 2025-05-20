-- Xóa các bảng nếu đã tồn tại
DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

-- Tạo bảng roles
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- Tạo bảng users
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tạo bảng user_roles (bảng trung gian giữa users và roles)
CREATE TABLE user_roles (
    user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    role_id INTEGER REFERENCES roles(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

-- Tạo bảng notes
CREATE TABLE notes (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    title VARCHAR(255),
    content TEXT,
    is_public BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Tạo bảng notifications (dành cho thông báo thời gian thực)
CREATE TABLE notifications (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_read BOOLEAN DEFAULT FALSE
);

-- Thêm chỉ mục để tối ưu hóa truy vấn
CREATE INDEX idx_notes_user_id ON notes (user_id);
CREATE INDEX idx_notes_created_at ON notes (created_at);
CREATE INDEX idx_notifications_user_id ON notifications (user_id);
CREATE INDEX idx_notifications_created_at ON notifications (created_at);

-- Hàm để tự động cập nhật cột updated_at
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger để cập nhật updated_at khi bản ghi trong bảng users được sửa
CREATE TRIGGER update_users_updated_at
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

-- Trigger để cập nhật updated_at khi bản ghi trong bảng notes được sửa
CREATE TRIGGER update_notes_updated_at
BEFORE UPDATE ON notes
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

-- Chèn dữ liệu mẫu vào bảng roles
INSERT INTO roles (name) VALUES ('admin'), ('user');

-- Chèn dữ liệu mẫu vào bảng users
INSERT INTO users (username, email, password_hash) 
VALUES 
('admin_user', 'admin@example.com', 'hashed_password_1'),
('regular_user', 'user@example.com', 'hashed_password_2');

-- Chèn dữ liệu mẫu vào bảng user_roles
INSERT INTO user_roles (user_id, role_id) 
VALUES 
(1, 1),  -- admin_user là admin
(2, 2);  -- regular_user là user

-- Chèn dữ liệu mẫu vào bảng notes
INSERT INTO notes (user_id, title, content, is_public) 
VALUES 
(1, 'Admin Note', 'Đây là ghi chú của admin.', TRUE),
(2, 'User Note', 'Đây là ghi chú của người dùng thường.', FALSE);

-- Chèn dữ liệu mẫu vào bảng notifications
INSERT INTO notifications (user_id, message, is_read) 
VALUES 
(2, 'Chào mừng bạn đến với hệ thống!', FALSE),
(1, 'Bạn có một ghi chú mới được chia sẻ.', TRUE);