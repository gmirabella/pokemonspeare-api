\connect pokemon;

CREATE TABLE pokemon (
name                    text   primary key not null,
description             text               not null,
created_on              timestamptz        not null default now()
);
