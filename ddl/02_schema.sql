\connect pokemon;

CREATE TABLE pokemon (
id                      int8  primary key not null,
name                    text              not null,
desciption              text              not null,
translated_description  text              not null,
created_on              timestamptz       not null default now()
);
