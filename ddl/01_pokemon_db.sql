CREATE DATABASE pokemon;

\connect pokemon;

CREATE EXTENSION btree_gist;

CREATE ROLE user_rw LOGIN PASSWORD 'test';

GRANT connect on database "pokemon" to user_rw;