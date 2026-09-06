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


CREATE TABLE user_profiles (
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