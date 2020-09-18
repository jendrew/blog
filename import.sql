insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_SUPERADMIN');

insert into user (username, password, fullname, enabled, role_id, fbid) values ('jedrzej', '$2a$10$rsTfl7aSS4hWztBcpGBfLu4HJUP5Fb0bQqneXvQntJO6eowlomStu','Jędrzej K.',true, 0, 1327256238);

insert into category (name, displayName) values ('przyklad', 'przykład');


insert into page (slug, title, description, body, menuPosition, published) values ('home', 'home', 'This is homepage', 'This is your homepage', 0, true);



