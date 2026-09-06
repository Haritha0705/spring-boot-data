DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS profiles CASCADE;
DROP TABLE IF EXISTS addresses CASCADE;
DROP TABLE IF EXISTS profiles CASCADE;
DROP TABLE IF EXISTS courses CASCADE;
DROP TABLE IF EXISTS enrollments CASCADE;
DROP TABLE IF EXISTS payments CASCADE;
DROP TABLE IF EXISTS notifications CASCADE;
DROP TABLE IF EXISTS contacts CASCADE;
DROP TABLE IF EXISTS orders CASCADE;

-- USERS

CREATE TABLE users (
                       id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(150) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       is_active BOOLEAN NOT NULL DEFAULT TRUE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                       CONSTRAINT uq_users_email UNIQUE (email)
);

-- ADDRESSESff
-- STUDENT 1 : N ADDRESSES

CREATE TABLE addresses (
                           id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           user_id BIGINT NOT NULL,
                           address_line VARCHAR(255) NOT NULL,
                           city VARCHAR(100) NOT NULL,
                           country VARCHAR(100) DEFAULT 'Sri Lanka',
                           address_type VARCHAR(20) DEFAULT 'HOME',
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                           CONSTRAINT addresses_user_fk
                               FOREIGN KEY (user_id)
                                   REFERENCES users(id)
                                   ON UPDATE CASCADE
                                   ON DELETE CASCADE,

                           CONSTRAINT check_address_type CHECK (address_type IN ('HOME', 'WORK', 'OTHER'))
);

-- STUDENT PROFILES
-- STUDENT 1 : 1 PROFILE

CREATE TABLE profiles (
                          id BIGINT PRIMARY KEY,
                          date_of_birth DATE,
                          gender VARCHAR(20),
                          bio TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT fk_user_profile
                              FOREIGN KEY (id)
                                  REFERENCES users(id)
                                  ON UPDATE CASCADE
                                  ON DELETE CASCADE
);

-- COURSES

CREATE TABLE courses (
                         id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         course_code VARCHAR(20) NOT NULL,
                         name VARCHAR(100) NOT NULL,
                         fee DECIMAL(10, 2) NOT NULL CHECK (fee >= 0),
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                         CONSTRAINT uq_course_code UNIQUE (course_code),
                         CONSTRAINT uq_course_name UNIQUE (name)
);

-- ENROLLMENTS
-- STUDENTS N : M COURSES

CREATE TABLE enrollments (
                             id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             user_id BIGINT NOT NULL,
                             course_id BIGINT NOT NULL,
                             enrollment_date DATE DEFAULT CURRENT_DATE,
                             status VARCHAR(20) DEFAULT 'ACTIVE',
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                             CONSTRAINT fk_enrollment_user
                                 FOREIGN KEY (user_id)
                                     REFERENCES users(id)
                                     ON UPDATE CASCADE
                                     ON DELETE CASCADE,

                             CONSTRAINT fk_enrollment_course
                                 FOREIGN KEY (course_id)
                                     REFERENCES courses(id)
                                     ON UPDATE CASCADE
                                     ON DELETE CASCADE,

                             CONSTRAINT uq_user_course UNIQUE (user_id, course_id),
                             CONSTRAINT check_enrollment_status CHECK (status IN ('ACTIVE', 'COMPLETED', 'DROPPED'))
);

-- PAYMENTS
-- STUDENT 1 : N PAYMENTS

CREATE TABLE payments (
                          id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          course_id BIGINT NOT NULL,
                          amount DECIMAL(10, 2) NOT NULL CHECK (amount > 0),
                          payment_method VARCHAR(30) NOT NULL,
                          payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          status VARCHAR(20) DEFAULT 'SUCCESS',

                          CONSTRAINT payment_user_fk
                              FOREIGN KEY (user_id)
                                  REFERENCES users(id)
                                  ON DELETE CASCADE,

                          CONSTRAINT check_payment_method CHECK (payment_method IN ('CASH', 'CARD', 'BANK_TRANSFER', 'ONLINE')),
                          CONSTRAINT check_payment_status CHECK (status IN ('SUCCESS', 'PENDING', 'FAILED'))
);

-- NOTIFICATIONS
-- STUDENT 1 : N NOTIFICATIONS

CREATE TABLE notifications (
                               id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               title VARCHAR(150) NOT NULL,
                               message TEXT NOT NULL,
                               is_read BOOLEAN DEFAULT FALSE,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                               CONSTRAINT notification_user_fk
                                   FOREIGN KEY (user_id)
                                       REFERENCES users(id)
                                       ON DELETE CASCADE,
);

-- STUDENT CONTACTS
-- STUDENT 1 : N CONTACTS

CREATE TABLE contacts (
                          id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          contact_type VARCHAR(20) NOT NULL,
                          contact_value VARCHAR(150) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT contact_user_fk
                              FOREIGN KEY (user_id)
                                  REFERENCES users(id)
                                  ON DELETE CASCADE,

                          CONSTRAINT check_contact_type CHECK (contact_type IN ('PHONE', 'WHATSAPP', 'EMAIL'))
);

-- 14. ORDERS
-- STUDENT 1 : N ORDERS
-- COURSE 1 : N ORDERS
-- PAYMENT 1 : N ORDERS

CREATE TABLE orders (
                        id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        course_id BIGINT NOT NULL,
                        payment_id BIGINT NOT NULL ,
                        amount DECIMAL(10, 2) NOT NULL CHECK (amount > 0),
                        status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                        CONSTRAINT order_user_fk
                            FOREIGN KEY (user_id)
                                REFERENCES users(id)
                                ON DELETE CASCADE,

                        CONSTRAINT order_course_fk
                            FOREIGN KEY (course_id)
                                REFERENCES courses(id)
                                ON DELETE CASCADE,

                        CONSTRAINT order_payment_fk
                            FOREIGN KEY (payment_id)
                                REFERENCES payments(id)
                                ON DELETE CASCADE,

                        CONSTRAINT check_order_status CHECK (status IN ('PENDING', 'PAID', 'PAYMENT_FAILED', 'COMPLETED', 'CANCELLED'))
);