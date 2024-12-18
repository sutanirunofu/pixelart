CREATE TABLE IF NOT EXISTS roles
(
    id   INTEGER             NOT NULL,
    name VARCHAR(255) UNIQUE NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users
(
    id                BIGINT                   NOT NULL,
    role_id           INTEGER                  NOT NULL,
    firstname         VARCHAR(255)             NOT NULL,
    username          VARCHAR(255) UNIQUE      NOT NULL,
    password          VARCHAR(255)             NOT NULL,
    registration_date TIMESTAMP WITH TIME ZONE NOT NULL,

    PRIMARY KEY (id, role_id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS arts
(
    id               BIGINT                   NOT NULL,
    title            VARCHAR(255)             NOT NULL,
    map              TEXT                     NOT NULL,
    colors           TEXT                     NOT NULL,
    publication_date TIMESTAMP WITH TIME ZONE NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS saved_arts
(
    id            BIGINT                   NOT NULL,
    art_id        BIGINT                   NOT NULL,
    user_id       BIGINT                   NOT NULL,
    map           TEXT                     NOT NULL,
    is_complete   BOOLEAN                  NOT NULL,
    modified_date TIMESTAMP WITH TIME ZONE NOT NULL,


    PRIMARY KEY (id, art_id, user_id),
    FOREIGN KEY (art_id) REFERENCES arts (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);