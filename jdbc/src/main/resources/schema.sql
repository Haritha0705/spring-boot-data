-- DROP TABLES

DROP TABLE IF EXISTS student_contacts CASCADE;
DROP TABLE IF EXISTS notifications CASCADE;
DROP TABLE IF EXISTS payments CASCADE;
DROP TABLE IF EXISTS addresses CASCADE;
DROP TABLE IF EXISTS enrollments CASCADE;
DROP TABLE IF EXISTS student_profiles CASCADE;
DROP TABLE IF EXISTS courses CASCADE;
DROP TABLE IF EXISTS instructors CASCADE;
DROP TABLE IF EXISTS students CASCADE;

-- 1. STUDENTS

CREATE TABLE students (
                          id SERIAL PRIMARY KEY,

                          name VARCHAR(100) NOT NULL,

                          email VARCHAR(150) NOT NULL,

                          age INT CHECK (age >= 16) NOT NULL,

                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT uq_student_email
                              UNIQUE (email)
);

-- 2. STUDENT PROFILES
-- ONE-TO-ONE
-- students 1 : 1 student_profiles

CREATE TABLE student_profiles (
                                  id SERIAL PRIMARY KEY,

                                  student_id INT NOT NULL,

                                  date_of_birth DATE,

                                  gender VARCHAR(20),

                                  bio TEXT,

                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                                  CONSTRAINT uq_student_profile_student
                                      UNIQUE (student_id),

                                  CONSTRAINT fk_profile_student
                                      FOREIGN KEY (student_id)
                                      REFERENCES students(id)
                                      ON DELETE CASCADE
);

-- 3. INSTRUCTORS
-- SELF-REFERENCING RELATIONSHIP
-- instructor -> manager
-- One instructor can manage many instructors.

CREATE TABLE instructors (
                             id SERIAL PRIMARY KEY,

                             name VARCHAR(100) NOT NULL,

                             email VARCHAR(150) NOT NULL,

                             manager_id INT,

                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                             CONSTRAINT uq_instructor_email
                                 UNIQUE (email),

                             CONSTRAINT fk_instructor_manager
                                 FOREIGN KEY (manager_id)
                                 REFERENCES instructors(id)
                                 ON DELETE SET NULL
);

-- 4. COURSES
-- instructors 1 : N courses

CREATE TABLE courses (
                         id SERIAL PRIMARY KEY,

                         course_code INT NOT NULL,

                         name VARCHAR(100) NOT NULL,

                         fee DECIMAL(10, 2) NOT NULL
                             CHECK (fee >= 0),

                         instructor_id INT,

                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                         CONSTRAINT uq_course_code
                             UNIQUE (course_code),

                         CONSTRAINT uq_course_name
                             UNIQUE (name),

                         CONSTRAINT fk_course_instructor
                             FOREIGN KEY (instructor_id)
                             REFERENCES instructors(id)
                             ON DELETE SET NULL
);

-- 5. ENROLLMENTS
-- MANY-TO-MANY
-- students N : M courses
-- students -> enrollments -> courses

CREATE TABLE enrollments (
                             id SERIAL PRIMARY KEY,

                             student_id INT NOT NULL,

                             course_id INT NOT NULL,

                             enrollment_date DATE DEFAULT CURRENT_DATE,

                             status VARCHAR(20) DEFAULT 'ACTIVE',

                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                             CONSTRAINT fk_enrollment_student
                                 FOREIGN KEY (student_id)
                                 REFERENCES students(id)
                                 ON DELETE CASCADE,

                             CONSTRAINT fk_enrollment_course
                                 FOREIGN KEY (course_id)
                                 REFERENCES courses(id)
                                 ON DELETE CASCADE,

                             CONSTRAINT uq_student_course
                                 UNIQUE (student_id, course_id),

                             CONSTRAINT check_enrollment_status
                                 CHECK (status IN ('ACTIVE','COMPLETED','DROPPED'))
);

-- 6. PAYMENTS
-- ONE-TO-MANY
-- students 1 : N payments

CREATE TABLE payments (
                          id SERIAL PRIMARY KEY,

                          student_id INT NOT NULL,

                          amount DECIMAL(10, 2) NOT NULL
                              CHECK (amount > 0),

                          payment_method VARCHAR(30) NOT NULL,

                          payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                          status VARCHAR(20) DEFAULT 'SUCCESS',

                          CONSTRAINT fk_payment_student
                              FOREIGN KEY (student_id)
                              REFERENCES students(id)
                              ON DELETE CASCADE,

                          CONSTRAINT check_payment_method
                              CHECK (payment_method IN ('CASH','CARD','BANK_TRANSFER','ONLINE')),

                          CONSTRAINT check_payment_status
                              CHECK (status IN ('SUCCESS','PENDING','FAILED'))
);

-- 7. ADDRESSES
-- ONE-TO-MANY
-- students 1 : N addresses

CREATE TABLE addresses (
                           id SERIAL PRIMARY KEY,

                           student_id INT NOT NULL,

                           address_line VARCHAR(255) NOT NULL,

                           city VARCHAR(100) NOT NULL,

                           country VARCHAR(100) DEFAULT 'Sri Lanka',

                           address_type VARCHAR(20) DEFAULT 'HOME',

                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                           CONSTRAINT fk_address_student
                               FOREIGN KEY (student_id)
                               REFERENCES students(id)
                               ON DELETE CASCADE,

                           CONSTRAINT check_address_type
                               CHECK (address_type IN ('HOME','WORK','OTHER'))
);

-- 8. NOTIFICATIONS
-- ONE-TO-MANY
-- students 1 : N notifications

CREATE TABLE notifications (
                               id SERIAL PRIMARY KEY,

                               student_id INT NOT NULL,

                               title VARCHAR(150) NOT NULL,

                               message TEXT NOT NULL,

                               is_read BOOLEAN DEFAULT FALSE,

                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                               CONSTRAINT fk_notification_student
                                   FOREIGN KEY (student_id)
                                   REFERENCES students(id)
                                   ON DELETE CASCADE
);

-- 9. STUDENT CONTACTS
-- ONE-TO-MANY
-- students 1 : N contacts

CREATE TABLE student_contacts (
                                  id SERIAL PRIMARY KEY,

                                  student_id INT NOT NULL,

                                  contact_type VARCHAR(20) NOT NULL,

                                  contact_value VARCHAR(150) NOT NULL,

                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                                  CONSTRAINT fk_contact_student
                                      FOREIGN KEY (student_id)
                                      REFERENCES students(id)
                                      ON DELETE CASCADE,

                                  CONSTRAINT check_contact_type
                                      CHECK (contact_type IN ('PHONE','WHATSAPP','EMAIL'))
);

-- INDEXES

-- STUDENTS

CREATE INDEX idx_students_name
    ON students(name);

CREATE INDEX idx_students_age
    ON students(age);

-- INSTRUCTORS

CREATE INDEX idx_instructors_name
    ON instructors(name);

CREATE INDEX idx_instructors_manager_id
    ON instructors(manager_id);

-- COURSES

CREATE INDEX idx_courses_instructor_id
    ON courses(instructor_id);

-- ENROLLMENTS

CREATE INDEX idx_enrollments_course_id
    ON enrollments(course_id);

CREATE INDEX idx_enrollments_status
    ON enrollments(status);

CREATE INDEX idx_enrollments_date
    ON enrollments(enrollment_date);

-- PAYMENTS

CREATE INDEX idx_payments_student_id
    ON payments(student_id);

CREATE INDEX idx_payments_payment_date
    ON payments(payment_date);

CREATE INDEX idx_payments_status
    ON payments(status);

-- ADDRESSES

CREATE INDEX idx_addresses_student_id
    ON addresses(student_id);

CREATE INDEX idx_addresses_city
    ON addresses(city);

-- NOTIFICATIONS

CREATE INDEX idx_notifications_student_id
    ON notifications(student_id);

CREATE INDEX idx_notifications_is_read
    ON notifications(is_read);

CREATE INDEX idx_notifications_created_at
    ON notifications(created_at);

-- STUDENT CONTACTS

CREATE INDEX idx_student_contacts_student_id
    ON student_contacts(student_id);

CREATE INDEX idx_student_contacts_type
    ON student_contacts(contact_type);