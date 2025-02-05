INSERT INTO `user`(id, username, password, full_name, birth_date)
VALUES(1, 'username1', 'abc', 'Join', SYSDATE());
INSERT INTO `user`(id, username, password, full_name, birth_date)
VALUES(2, 'username2', 'abc', 'Atom', SYSDATE());
INSERT INTO `user`(id, username, password, full_name, birth_date)
VALUES(3, 'username3', 'abc', 'Lucy', SYSDATE());

INSERT INTO course(id, code, name)
VALUES(1, 'course1', 'Introductory Programming');
INSERT INTO course(id, code, name)
VALUES(2, 'course2', 'Programming Techniques');
INSERT INTO course(id, code, name)
VALUES(3, 'course3', 'Web Development');

INSERT INTO user_course(id, user_id, course_id, date_register, status)
VALUES(1, 1, 2, CURRENT_DATE(), 'PENDING');
INSERT INTO user_course(id, user_id, course_id, date_register, status)
VALUES(2, 1, 3, CURRENT_DATE(), 'ACTIVE');
