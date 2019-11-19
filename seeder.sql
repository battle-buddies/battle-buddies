insert into users (username, email, password, is_admin) values ('Admin', 'Admin@mail.com', 'password', true);
insert into users (username, email, password, is_admin) values ('bob', 'bob@mail.com', 'password', true);

insert into user_friends(user_id, friend_id, status) value (1,2, "PENDING");
