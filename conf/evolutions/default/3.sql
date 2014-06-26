# --- !Ups
create table Post (
  id int(10) not null auto_increment,
  userId int(10) not null,
  title varchar(100) not null,
  body text,
  createDate timestamp default current_timestamp(),
  primary key(id),
  foreign key(userId) references USer(id)
);
# --- !Downs
drop table Post;