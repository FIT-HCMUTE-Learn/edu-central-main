-- 20 Users
INSERT INTO `user`(id, username, password, full_name, birth_date) VALUES
(1, 'user1', 'abc', 'John Doe', SYSDATE()),
(2, 'user2', 'abc', 'Jane Smith', SYSDATE()),
(3, 'user3', 'abc', 'Alice Brown', SYSDATE()),
(4, 'user4', 'abc', 'Bob Johnson', SYSDATE()),
(5, 'user5', 'abc', 'Charlie White', SYSDATE()),
(6, 'user6', 'abc', 'David Black', SYSDATE()),
(7, 'user7', 'abc', 'Emma Watson', SYSDATE()),
(8, 'user8', 'abc', 'Frank Green', SYSDATE()),
(9, 'user9', 'abc', 'Grace Hall', SYSDATE()),
(10, 'user10', 'abc', 'Hannah Lewis', SYSDATE()),
(11, 'user11', 'abc', 'Ivy Martin', SYSDATE()),
(12, 'user12', 'abc', 'Jack Lee', SYSDATE()),
(13, 'user13', 'abc', 'Kelly Adams', SYSDATE()),
(14, 'user14', 'abc', 'Liam Scott', SYSDATE()),
(15, 'user15', 'abc', 'Mia Harris', SYSDATE()),
(16, 'user16', 'abc', 'Noah King', SYSDATE()),
(17, 'user17', 'abc', 'Olivia Moore', SYSDATE()),
(18, 'user18', 'abc', 'Paul Wilson', SYSDATE()),
(19, 'user19', 'abc', 'Quinn Young', SYSDATE()),
(20, 'user20', 'abc', 'Rachel Evans', SYSDATE());

-- 20 Courses
INSERT INTO course(id, code, name) VALUES
(1, 'CS101', 'Intro to Computer Science'),
(2, 'CS102', 'Data Structures and Algorithms'),
(3, 'CS103', 'Operating Systems'),
(4, 'CS104', 'Computer Networks'),
(5, 'CS105', 'Database Management Systems'),
(6, 'CS106', 'Software Engineering'),
(7, 'CS107', 'Artificial Intelligence'),
(8, 'CS108', 'Machine Learning'),
(9, 'CS109', 'Cybersecurity Fundamentals'),
(10, 'CS110', 'Blockchain Technology'),
(11, 'CS111', 'Cloud Computing'),
(12, 'CS112', 'Big Data Analytics'),
(13, 'CS113', 'IoT and Embedded Systems'),
(14, 'CS114', 'Web Development'),
(15, 'CS115', 'Mobile App Development'),
(16, 'CS116', 'Game Development'),
(17, 'CS117', 'Computer Vision'),
(18, 'CS118', 'Natural Language Processing'),
(19, 'CS119', 'DevOps and Continuous Integration'),
(20, 'CS120', 'Quantum Computing');

-- 10 record user_course
INSERT INTO user_course(id, user_id, course_id, date_register, register_status) VALUES
(1, 1, 2, CURRENT_DATE(), 'ACTIVE'),
(2, 2, 3, CURRENT_DATE(), 'PENDING'),
(3, 3, 5, CURRENT_DATE(), 'ACTIVE'),
(4, 4, 7, CURRENT_DATE(), 'PENDING'),
(5, 5, 8, CURRENT_DATE(), 'ACTIVE'),
(6, 6, 10, CURRENT_DATE(), 'PENDING'),
(7, 7, 12, CURRENT_DATE(), 'ACTIVE'),
(8, 8, 15, CURRENT_DATE(), 'PENDING'),
(9, 9, 18, CURRENT_DATE(), 'ACTIVE'),
(10, 10, 20, CURRENT_DATE(), 'PENDING');
