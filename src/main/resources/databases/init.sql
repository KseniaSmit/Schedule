CREATE TABLE IF NOT EXISTS weekday
(
    id SERIAL PRIMARY KEY ,
    name TEXT NOT NULL
);
create table if not exists users
(
    id serial primary key,
    email text,
    username text,
    password text,
    role text
);
CREATE TABLE IF NOT EXISTS records
(
    id SERIAL PRIMARY KEY,
    types_id integer,
    user_id integer,
    name TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (types_id) REFERENCES weekday (id) ON DELETE CASCADE
);