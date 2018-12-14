CREATE database model
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE TABLE userGroups(
                         id INT auto_increment PRIMARY KEY,
                         name VARCHAR (255)
);
CREATE TABLE users(
                    id BIGINT auto_increment PRIMARY KEY,
                    username VARCHAR(255),
                    email VARCHAR(255),
                    password VARCHAR(255),
                    usergroup_id INT NOT NULL ,
                    FOREIGN KEY (usergroup_id) REFERENCES userGroups(id)
);
CREATE TABLE exercise(
                       id INT auto_increment PRIMARY KEY,
                       title VARCHAR(255),
                       description TEXT
);
CREATE TABLE solution(
                       id INT auto_increment PRIMARY KEY,
                       created DATETIME,
                       updated DATETIME,
                       description TEXT,
                       exercise_id INT NOT NULL ,
                       user_id BIGINT NOT NULL ,
                       FOREIGN KEY (exercise_id) REFERENCES exercise(id),
                       FOREIGN KEY (user_id) REFERENCES users(id)
);
