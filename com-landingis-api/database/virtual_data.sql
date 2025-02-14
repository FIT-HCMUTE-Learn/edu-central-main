-- Insert groups
INSERT INTO user_groups (id, name, kind) VALUES
(1, 'GROUP_USER', 1),
(2, 'GROUP_ADMIN', 2);

-- Insert permissions
INSERT INTO permissions (id, pcode) VALUES
(1, 'C_GET'),
(2, 'C_POST'),
(3, 'C_PUT'),
(4, 'C_DELETE');

-- Assign permissions to groups
INSERT INTO group_permissions (group_id, permission_id) VALUES
(1, 1), -- GROUP_USER -> C_GET
(1, 2), -- GROUP_USER -> C_POST
(2, 1), -- GROUP_ADMIN -> C_GET
(2, 2), -- GROUP_ADMIN -> C_POST
(2, 3), -- GROUP_ADMIN -> C_PUT
(2, 4); -- GROUP_ADMIN -> C_DELETE

-- Insert users (10 students, 2 admins)
INSERT INTO users (id, username, password, full_name, avatar, gender, group_id) VALUES
(1, 'student1',  '$2a$10$Q7YVWsUIFK3qzGgL2.TtSuQQ/POJ4pIKNCm6J5GrbQLU1rdQGsVVW', 'John Doe', 'avatar1.png', 1, 1),
(2, 'student2',  '$2a$10$Q7YVWsUIFK3qzGgL2.TtSuQQ/POJ4pIKNCm6J5GrbQLU1rdQGsVVW', 'Jane Smith', 'avatar2.png', 2, 1),
(3, 'student3',  '$2a$10$Q7YVWsUIFK3qzGgL2.TtSuQQ/POJ4pIKNCm6J5GrbQLU1rdQGsVVW', 'Alice Brown', 'avatar3.png', 1, 1),
(4, 'student4',  '$2a$10$Q7YVWsUIFK3qzGgL2.TtSuQQ/POJ4pIKNCm6J5GrbQLU1rdQGsVVW', 'Bob Johnson', 'avatar4.png', 2, 1),
(5, 'student5',  '$2a$10$Q7YVWsUIFK3qzGgL2.TtSuQQ/POJ4pIKNCm6J5GrbQLU1rdQGsVVW', 'Charlie White', 'avatar5.png', 1, 1),
(6, 'student6',  '$2a$10$Q7YVWsUIFK3qzGgL2.TtSuQQ/POJ4pIKNCm6J5GrbQLU1rdQGsVVW', 'David Black', 'avatar6.png', 2, 1),
(7, 'student7',  '$2a$10$Q7YVWsUIFK3qzGgL2.TtSuQQ/POJ4pIKNCm6J5GrbQLU1rdQGsVVW', 'Emma Watson', 'avatar7.png', 1, 1),
(8, 'student8',  '$2a$10$Q7YVWsUIFK3qzGgL2.TtSuQQ/POJ4pIKNCm6J5GrbQLU1rdQGsVVW', 'Frank Green', 'avatar8.png', 2, 1),
(9, 'student9',  '$2a$10$Q7YVWsUIFK3qzGgL2.TtSuQQ/POJ4pIKNCm6J5GrbQLU1rdQGsVVW', 'Grace Hall', 'avatar9.png', 1, 1),
(10, 'student10', '$2a$10$Q7YVWsUIFK3qzGgL2.TtSuQQ/POJ4pIKNCm6J5GrbQLU1rdQGsVVW', 'Hannah Lewis', 'avatar10.png', 2, 1),
(11, 'admin1',  '$2a$10$Q7YVWsUIFK3qzGgL2.TtSuQQ/POJ4pIKNCm6J5GrbQLU1rdQGsVVW', 'Admin One', 'admin_avatar1.png', 1, 2),
(12, 'admin2',  '$2a$10$Q7YVWsUIFK3qzGgL2.TtSuQQ/POJ4pIKNCm6J5GrbQLU1rdQGsVVW', 'Admin Two', 'admin_avatar2.png', 2, 2);

-- Insert students
INSERT INTO students (id, student_code, birth_date, user_id) VALUES
(1, 'S1001', '2002-03-15', 1),
(2, 'S1002', '2001-07-25', 2),
(3, 'S1003', '2000-12-05', 3),
(4, 'S1004', '1999-09-18', 4),
(5, 'S1005', '2003-01-30', 5),
(6, 'S1006', '2002-06-14', 6),
(7, 'S1007', '2000-02-22', 7),
(8, 'S1008', '2001-11-03', 8),
(9, 'S1009', '1998-05-09', 9),
(10, 'S1010', '2003-04-01', 10);

-- Insert admins
INSERT INTO admins (id, level, user_id) VALUES
(1, 1, 11),
(2, 2, 12);

-- Insert sample courses
INSERT INTO courses (id, code, name, status) VALUES
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

-- Assign users to courses with scores
INSERT INTO user_course (id, user_id, course_id, date_register, register_status, learning_state, score) VALUES
(1, 1, 2, CURRENT_DATE(), 'ACTIVE', 'IN_PROGRESS', 7.8),
(2, 2, 3, CURRENT_DATE(), 'PENDING', 'IN_PROGRESS', 8.5),
(3, 3, 5, CURRENT_DATE(), 'ACTIVE', 'IN_PROGRESS', 6.2),
(4, 4, 7, CURRENT_DATE(), 'PENDING', 'IN_PROGRESS', 9.1),
(5, 5, 8, CURRENT_DATE(), 'ACTIVE', 'IN_PROGRESS', 5.7),
(6, 6, 10, CURRENT_DATE(), 'PENDING', 'IN_PROGRESS', 8.0),
(7, 7, 12, CURRENT_DATE(), 'ACTIVE', 'IN_PROGRESS', 7.2);
