insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_SUPERADMIN');

insert into user (username, password, fullname, enabled, role_id, fbid) values ('jedrzej', '$2a$10$1Js.Zut3feESrBW91NP3.eR2SP5ufzIYEjAglZFsYS.xQmjExl6oW','Jędrzej K.',true, 1, 1327256238);

insert into category (name, displayName) values ('przyklad', 'przykład');


insert into page (slug, title, description, body, menuPosition) values ('home', 'home', 'This is homepage', 'This is your homepage', 0);



