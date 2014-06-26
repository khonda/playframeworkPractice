# Tasks schema

# --- !Ups
CREATE TABLE task (
    id int(10) not null auto_increment,
    label varchar(255),
    primary key(id)
);

# --- !Downs
DROP TABLE task;
