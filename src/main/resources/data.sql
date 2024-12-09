CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT NOT NULL,
                                     firstname VARCHAR(255) NOT NULL,
                                     username VARCHAR(255) UNIQUE NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     registration_date TIMESTAMP WITH TIME ZONE NOT NULL,

                                     PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles (
                                     id INTEGER NOT NULL,
                                     name VARCHAR(255) UNIQUE NOT NULL,

                                     PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS arts (
                                    id BIGINT NOT NULL,
                                    title VARCHAR(255) NOT NULL,
                                    map TEXT NOT NULL,
                                    colors TEXT NOT NULL,
                                    publication_date TIMESTAMP WITH TIME ZONE NOT NULL,

                                    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS saved_arts (
                                          id BIGINT NOT NULL,
                                          map TEXT NOT NULL,
                                          last_modified TIMESTAMP WITH TIME ZONE NOT NULL,
                                          is_complete BOOLEAN NOT NULL,

                                          PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_saved_arts (
                                                user_id BIGINT NOT NULL,
                                                art_id BIGINT NOT NULL,

                                                PRIMARY KEY (user_id, art_id),
                                                FOREIGN KEY (user_id) REFERENCES users (id),
                                                FOREIGN KEY (art_id) REFERENCES saved_arts (id)
);

CREATE TABLE IF NOT EXISTS stars (
                                     art_id BIGINT NOT NULL,
                                     user_id BIGINT NOT NULL,

                                     PRIMARY KEY (art_id, user_id),
                                     FOREIGN KEY (art_id) REFERENCES arts (id),
                                     FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO roles (name) VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');

INSERT INTO users (role_id, firstname, username, password, registration_date) VALUES
    (1, 'Stanislav', 'user', '$2a$10$pY4rL5iyouRwTwCXcdzue.Ye7Y/5sccu55glo5HD.jwAQds2mEZHy', '2006-06-09T11:11:11+0000'),
    (2, 'Alexander', 'admin', '$2a$10$pY4rL5iyouRwTwCXcdzue.Ye7Y/5sccu55glo5HD.jwAQds2mEZHy', '1984-07-01T11:11:11+0000');

INSERT INTO arts (title, map, colors, publication_date) VALUES
    ('Dog', '[[1, 2, 1], [3, 1, 3], [2, 1, 3]]', '[0xff0000, 0xffffff, 0x0000ff]', '2014-09-14T11:11:11+0000'),
    ('Nature', '[[2, 1, 3], [1, 1, 2], [3, 3, 3]]', '[0xff0000, 0xffffff, 0x0000ff]', '2014-09-14T11:11:11+0000'),
    ('Car', '[[2, 2, 2], [1, 1, 1], [3, 3, 3]]', '[0xff0000, 0xffffff, 0x0000ff]', '2014-09-14T11:11:11+0000');

INSERT INTO saved_arts (art_id, map, is_complete, last_modified) VALUES
    (1, '[[1, 0, 0], [0, 1, 0], [0, 0, 1]]', false, '2014-09-14T11:11:11+0000');

INSERT INTO users_saved_arts (user_id, art_id) VALUES
    (1, 1);

