-- =========================================================
-- DATA.SQL
-- =========================================================


-- =========================================================
-- 1. STUDENTS
-- =========================================================

INSERT INTO students
(name, email, age)
VALUES
    ('Haritha', 'haritha@gmail.com', 22),
    ('Kamal', 'kamal@gmail.com', 24),
    ('Nimal', 'nimal@gmail.com', 21),
    ('Saman', 'saman@gmail.com', 27),
    ('Kasun', 'kasun@gmail.com', 23),
    ('Amal', 'amal@gmail.com', 25),
    ('Tharindu', 'tharindu@gmail.com', 20),
    ('Dinesh', 'dinesh@gmail.com', 28),
    ('Chamod', 'chamod@gmail.com', 22),
    ('Ravindu', 'ravindu@gmail.com', 26);


-- =========================================================
-- 2. STUDENT PROFILES
-- ONE-TO-ONE
-- =========================================================

INSERT INTO student_profiles
(student_id, date_of_birth, gender, bio)
VALUES
    (1, '2004-05-15', 'MALE', 'Software engineering student'),
    (2, '2002-08-20', 'MALE', 'Backend developer student'),
    (3, '2005-01-10', 'MALE', 'Computer science student'),
    (4, '1999-11-25', 'MALE', 'Full stack developer'),
    (5, '2003-03-18', 'MALE', 'Database enthusiast'),
    (6, '2001-07-12', 'MALE', 'Java developer'),
    (7, '2005-09-05', 'MALE', 'Frontend developer'),
    (8, '1998-12-30', 'MALE', 'Software engineer'),
    (9, '2004-04-22', 'MALE', 'Mobile developer'),
    (10, '2000-06-14', 'MALE', 'DevOps student');


-- =========================================================
-- 3. INSTRUCTORS
-- SELF-REFERENCING RELATIONSHIP
-- =========================================================

-- Managers first

INSERT INTO instructors
(name, email, manager_id)
VALUES
    ('John Manager', 'john@academy.com', NULL),
    ('Sarah Manager', 'sarah@academy.com', NULL);


-- Employees reporting to John/Sarah

INSERT INTO instructors
(name, email, manager_id)
VALUES
    ('David Perera', 'david@academy.com', 1),
    ('Michael Silva', 'michael@academy.com', 1),
    ('Emma Fernando', 'emma@academy.com', 2),
    ('Daniel Jayasuriya', 'daniel@academy.com', 2);


-- =========================================================
-- 4. COURSES
-- instructors 1 : N courses
-- =========================================================

INSERT INTO courses
(course_code, name, fee, instructor_id)
VALUES
    (1001, 'Java Programming', 75000.00, 3),
    (1002, 'Spring Boot', 85000.00, 3),
    (1003, 'PostgreSQL', 60000.00, 4),
    (1004, 'Web Development', 70000.00, 5),
    (1005, 'React Development', 65000.00, 5),
    (1006, 'Python Programming', 55000.00, 6),
    (1007, 'Data Structures', 50000.00, 1),
    (1008, 'Database Design', 65000.00, 2),
    (1009, 'DevOps', 90000.00, 6),
    (1010, 'Software Architecture', 95000.00, 4);


-- =========================================================
-- 5. ENROLLMENTS
-- MANY-TO-MANY
--
-- students <-> courses
-- =========================================================

INSERT INTO enrollments
(student_id, course_id, enrollment_date, status)
VALUES
    -- Haritha
    (1, 1, '2026-01-10', 'COMPLETED'),
    (1, 2, '2026-02-15', 'ACTIVE'),
    (1, 3, '2026-03-01', 'ACTIVE'),

    -- Kamal
    (2, 1, '2026-01-12', 'COMPLETED'),
    (2, 4, '2026-02-20', 'ACTIVE'),

    -- Nimal
    (3, 3, '2026-01-15', 'ACTIVE'),
    (3, 5, '2026-02-10', 'ACTIVE'),
    (3, 6, '2026-03-05', 'ACTIVE'),

    -- Saman
    (4, 2, '2026-01-20', 'COMPLETED'),
    (4, 7, '2026-02-15', 'COMPLETED'),

    -- Kasun
    (5, 3, '2026-01-25', 'ACTIVE'),
    (5, 8, '2026-02-18', 'ACTIVE'),

    -- Amal
    (6, 1, '2026-02-01', 'ACTIVE'),
    (6, 9, '2026-02-25', 'ACTIVE'),

    -- Tharindu
    (7, 4, '2026-02-05', 'ACTIVE'),
    (7, 5, '2026-02-28', 'DROPPED'),

    -- Dinesh
    (8, 8, '2026-02-10', 'COMPLETED'),
    (8, 10, '2026-03-01', 'ACTIVE'),

    -- Chamod
    (9, 2, '2026-02-12', 'ACTIVE'),
    (9, 6, '2026-03-05', 'ACTIVE'),

    -- Ravindu
    (10, 9, '2026-02-20', 'ACTIVE'),
    (10, 10, '2026-03-10', 'ACTIVE');


-- =========================================================
-- 6. PAYMENTS
-- students 1 : N payments
-- =========================================================

INSERT INTO payments
(student_id, amount, payment_method, payment_date, status)
VALUES
    -- Haritha
    (1, 40000.00, 'CARD', '2026-01-10 10:00:00', 'SUCCESS'),
    (1, 35000.00, 'BANK_TRANSFER', '2026-02-15 11:00:00', 'SUCCESS'),
    (1, 20000.00, 'ONLINE', '2026-03-01 09:30:00', 'SUCCESS'),

    -- Kamal
    (2, 50000.00, 'CASH', '2026-01-12 10:30:00', 'SUCCESS'),
    (2, 25000.00, 'CARD', '2026-02-20 12:00:00', 'SUCCESS'),

    -- Nimal
    (3, 30000.00, 'ONLINE', '2026-01-15 09:00:00', 'SUCCESS'),
    (3, 20000.00, 'CARD', '2026-02-10 14:00:00', 'SUCCESS'),
    (3, 15000.00, 'BANK_TRANSFER', '2026-03-05 15:00:00', 'PENDING'),

    -- Saman
    (4, 60000.00, 'BANK_TRANSFER', '2026-01-20 10:00:00', 'SUCCESS'),
    (4, 15000.00, 'CARD', '2026-02-15 13:00:00', 'SUCCESS'),

    -- Kasun
    (5, 45000.00, 'ONLINE', '2026-01-25 11:00:00', 'SUCCESS'),
    (5, 15000.00, 'CARD', '2026-02-18 10:30:00', 'SUCCESS'),

    -- Amal
    (6, 30000.00, 'CASH', '2026-02-01 09:30:00', 'SUCCESS'),
    (6, 25000.00, 'ONLINE', '2026-02-25 11:30:00', 'SUCCESS'),

    -- Tharindu
    (7, 35000.00, 'CARD', '2026-02-05 12:00:00', 'SUCCESS'),

    -- Dinesh
    (8, 65000.00, 'BANK_TRANSFER', '2026-02-10 10:00:00', 'SUCCESS'),
    (8, 30000.00, 'ONLINE', '2026-03-01 12:00:00', 'SUCCESS'),

    -- Chamod
    (9, 40000.00, 'CARD', '2026-02-12 14:00:00', 'SUCCESS'),

    -- Ravindu
    (10, 50000.00, 'BANK_TRANSFER', '2026-02-20 15:00:00', 'SUCCESS'),
    (10, 40000.00, 'ONLINE', '2026-03-10 10:00:00', 'PENDING');


-- =========================================================
-- 7. ADDRESSES
-- students 1 : N addresses
-- =========================================================

INSERT INTO addresses
(student_id, address_line, city, country, address_type)
VALUES
    (1, '123 Main Street', 'Weligama', 'Sri Lanka', 'HOME'),
    (1, '45 University Road', 'Colombo', 'Sri Lanka', 'WORK'),

    (2, '12 Lake Road', 'Colombo', 'Sri Lanka', 'HOME'),

    (3, '88 Temple Road', 'Galle', 'Sri Lanka', 'HOME'),
    (3, '22 School Road', 'Galle', 'Sri Lanka', 'WORK'),

    (4, '10 Beach Road', 'Matara', 'Sri Lanka', 'HOME'),

    (5, '55 Station Road', 'Kandy', 'Sri Lanka', 'HOME'),

    (6, '78 Main Road', 'Colombo', 'Sri Lanka', 'HOME'),
    (6, '90 Park Road', 'Colombo', 'Sri Lanka', 'WORK'),

    (7, '15 School Lane', 'Galle', 'Sri Lanka', 'HOME'),

    (8, '100 City Road', 'Kandy', 'Sri Lanka', 'HOME'),

    (9, '25 Temple Street', 'Matara', 'Sri Lanka', 'HOME'),

    (10, '40 Lake Street', 'Colombo', 'Sri Lanka', 'HOME');


-- =========================================================
-- 8. NOTIFICATIONS
-- students 1 : N notifications
-- =========================================================

INSERT INTO notifications
(student_id, title, message, is_read)
VALUES
    (1, 'Payment Successful', 'Your payment was successful.', TRUE),
    (1, 'New Course', 'Spring Boot course is available.', FALSE),

    (2, 'Course Completed', 'You completed Java Programming.', TRUE),
    (2, 'Payment Successful', 'Your payment was successful.', TRUE),

    (3, 'Payment Pending', 'Your payment is pending.', FALSE),
    (3, 'New Course', 'React Development is available.', FALSE),

    (4, 'Course Completed', 'Congratulations on completing your course.', TRUE),

    (5, 'Welcome', 'Welcome to the academy.', TRUE),
    (5, 'Course Reminder', 'Your PostgreSQL class starts tomorrow.', FALSE),

    (6, 'Payment Successful', 'Your payment was successful.', TRUE),

    (7, 'Course Dropped', 'Your React course was dropped.', FALSE),

    (8, 'Course Completed', 'Congratulations on completing your course.', TRUE),
    (8, 'New Course', 'Software Architecture is available.', FALSE),

    (9, 'Course Reminder', 'Your Spring Boot class starts tomorrow.', FALSE),

    (10, 'Payment Pending', 'Your latest payment is pending.', FALSE);


-- =========================================================
-- 9. STUDENT CONTACTS
-- students 1 : N contacts
-- =========================================================

INSERT INTO student_contacts
(student_id, contact_type, contact_value)
VALUES
    (1, 'PHONE', '0771234567'),
    (1, 'WHATSAPP', '0771234567'),
    (1, 'EMAIL', 'haritha@gmail.com'),

    (2, 'PHONE', '0772345678'),
    (2, 'EMAIL', 'kamal@gmail.com'),

    (3, 'PHONE', '0773456789'),
    (3, 'WHATSAPP', '0773456789'),

    (4, 'PHONE', '0774567890'),
    (4, 'EMAIL', 'saman@gmail.com'),

    (5, 'PHONE', '0775678901'),

    (6, 'PHONE', '0776789012'),
    (6, 'WHATSAPP', '0776789012'),

    (7, 'PHONE', '0777890123'),

    (8, 'PHONE', '0778901234'),
    (8, 'EMAIL', 'dinesh@gmail.com'),

    (9, 'PHONE', '0779012345'),
    (9, 'WHATSAPP', '0779012345'),

    (10, 'PHONE', '0770123456'),
    (10, 'EMAIL', 'ravindu@gmail.com');