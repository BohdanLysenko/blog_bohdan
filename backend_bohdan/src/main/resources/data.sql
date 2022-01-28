/*BEGIN
IF
NOT EXISTS (SELECT email FROM blog_user
WHERE  blog_user.email = 'test_admin') THEN
INSERT INTO blog_user (email, last_name, name, password, role_id)
VALUES ('test_admin', 'admin', 'admin', 'admin', 1);
END IF;
END
*/