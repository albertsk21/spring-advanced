INSERT INTO students (ID, AGE, NAME, DELETED)
VALUES
    (1, 33, 'Pesho', 1),
    (2, 23, 'Anna', 0);

INSERT INTO courses(ID, NAME, PRICE)
VALUES
    (1, 'Spring for dummies', 50),
    (2, 'Spring for pros', 100);

INSERT INTO orders (ID, COURSE_ID, STUDENT_ID)
VALUES
    (1, 1, 1),
    (2, 2, 1),
    (3, 2, 2);
