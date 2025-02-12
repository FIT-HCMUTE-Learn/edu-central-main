-- Insert sample users
INSERT INTO `user` (id, username, password, full_name, birth_date, role) VALUES
(1, 'user1', '$2a$10$Xy9N5pEiY1qU/NayWqf9COvF91Khp8Xg2f9gMHdtIbT5c1T3JuJmK', 'John Doe', SYSDATE(), 'USER'),
(2, 'user2', '$2a$10$Xy9N5pEiY1qU/NayWqf9COvF91Khp8Xg2f9gMHdtIbT5c1T3JuJmK', 'Jane Smith', SYSDATE(), 'USER'),
(3, 'user3', '$2a$10$Xy9N5pEiY1qU/NayWqf9COvF91Khp8Xg2f9gMHdtIbT5c1T3JuJmK', 'Alice Brown', SYSDATE(), 'USER'),
(4, 'user4', 'abc', 'Bob Johnson', SYSDATE(), 'USER'),
(5, 'user5', 'abc', 'Charlie White', SYSDATE(), 'USER'),
(6, 'user6', 'abc', 'David Black', SYSDATE(), 'USER'),
(7, 'user7', 'abc', 'Emma Watson', SYSDATE(), 'USER'),
(8, 'user8', 'abc', 'Frank Green', SYSDATE(), 'USER'),
(9, 'user9', 'abc', 'Grace Hall', SYSDATE(), 'USER'),
(10, 'user10', 'abc', 'Hannah Lewis', SYSDATE(), 'USER'),
(11, 'user11', 'abc', 'Ivy Martin', SYSDATE(), 'USER'),
(12, 'user12', 'abc', 'Jack Lee', SYSDATE(), 'USER'),
(13, 'user13', 'abc', 'Kelly Adams', SYSDATE(), 'USER'),
(14, 'user14', 'abc', 'Liam Scott', SYSDATE(), 'USER'),
(15, 'user15', 'abc', 'Mia Harris', SYSDATE(), 'USER'),
(16, 'user16', 'abc', 'Noah King', SYSDATE(), 'USER'),
(17, 'user17', 'abc', 'Olivia Moore', SYSDATE(), 'USER'),
(18, 'user18', '$2a$10$Xy9N5pEiY1qU/NayWqf9COvF91Khp8Xg2f9gMHdtIbT5c1T3JuJmK', 'Paul Wilson', SYSDATE(), 'ADMIN'),
(19, 'user19', '$2a$10$Xy9N5pEiY1qU/NayWqf9COvF91Khp8Xg2f9gMHdtIbT5c1T3JuJmK', 'Quinn Young', SYSDATE(), 'ADMIN'),
(20, 'user20', '$2a$10$Xy9N5pEiY1qU/NayWqf9COvF91Khp8Xg2f9gMHdtIbT5c1T3JuJmK', 'Rachel Evans', SYSDATE(), 'ADMIN');

-- Insert sample courses
INSERT INTO course(id, code, name, status) VALUES
(1, 'CS101', 'Intro to Computer Science', 'IN_PROGRESS'),
(2, 'CS102', 'Data Structures and Algorithms', 'IN_PROGRESS'),
(3, 'CS103', 'Operating Systems', 'IN_PROGRESS'),
(4, 'CS104', 'Computer Networks', 'IN_PROGRESS'),
(5, 'CS105', 'Database Management Systems', 'IN_PROGRESS'),
(6, 'CS106', 'Software Engineering', 'IN_PROGRESS'),
(7, 'CS107', 'Artificial Intelligence', 'IN_PROGRESS'),
(8, 'CS108', 'Machine Learning', 'IN_PROGRESS'),
(9, 'CS109', 'Cybersecurity Fundamentals', 'IN_PROGRESS'),
(10, 'CS110', 'Blockchain Technology', 'IN_PROGRESS'),
(11, 'CS111', 'Cloud Computing', 'IN_PROGRESS'),
(12, 'CS112', 'Big Data Analytics', 'IN_PROGRESS'),
(13, 'CS113', 'IoT and Embedded Systems', 'IN_PROGRESS'),
(14, 'CS114', 'Web Development', 'IN_PROGRESS'),
(15, 'CS115', 'Mobile App Development', 'IN_PROGRESS'),
(16, 'CS116', 'Game Development', 'IN_PROGRESS'),
(17, 'CS117', 'Computer Vision', 'IN_PROGRESS'),
(18, 'CS118', 'Natural Language Processing', 'IN_PROGRESS'),
(19, 'CS119', 'DevOps and Continuous Integration', 'IN_PROGRESS'),
(20, 'CS120', 'Quantum Computing', 'IN_PROGRESS');

-- Assign users to courses
INSERT INTO user_course(id, user_id, course_id, date_register, register_status, learning_state) VALUES
(1, 1, 2, CURRENT_DATE(), 'ACTIVE', 'IN_PROGRESS'),
(2, 2, 3, CURRENT_DATE(), 'PENDING', 'IN_PROGRESS'),
(3, 3, 5, CURRENT_DATE(), 'ACTIVE', 'IN_PROGRESS'),
(4, 4, 7, CURRENT_DATE(), 'PENDING', 'IN_PROGRESS'),
(5, 5, 8, CURRENT_DATE(), 'ACTIVE', 'IN_PROGRESS'),
(6, 6, 10, CURRENT_DATE(), 'PENDING', 'IN_PROGRESS'),
(7, 7, 12, CURRENT_DATE(), 'ACTIVE', 'IN_PROGRESS'),
(8, 8, 15, CURRENT_DATE(), 'PENDING', 'IN_PROGRESS'),
(9, 9, 18, CURRENT_DATE(), 'ACTIVE', 'IN_PROGRESS'),
(10, 10, 20, CURRENT_DATE(), 'PENDING', 'IN_PROGRESS');

-- Insert sample permissions
INSERT INTO permissions (pcode) VALUES
('C_GET'), ('C_CRE'), ('C_UPD'), ('C_DEL');

-- Assign permissions to 'user1'
INSERT INTO user_permissions (user_id, permission_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(3, 1),
(3, 3);
