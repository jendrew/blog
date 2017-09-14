insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_SUPERADMIN');

insert into user (username, password, fullname, enabled, role_id) values ('jedrzej', '$2a$06$CzXAK3icLkBnpgi5zOTB.O/jzZqnxbPhtUQaxGZOhedahxAV2Iy4K','Jędrzej K.',true, 1);
insert into user (username, password, fullname, enabled, role_id) values ('anielka', '$2a$06$8FHgYeHducWsZkTbrOd8Te.1K4fnCDV2mZ1Z2iOweqC2VT.kITq86','Aniela Masna',true, 1);

insert into category (name, displayName) values ('przyklad', 'przykład');
insert into category (name, displayName) values ('tech', 'tech');
insert into category (name, displayName) values ('diy', 'diy');
insert into category (name, displayName) values ('bezpieczenstwo', 'bezpieczenstwo');
insert into category (name, displayName) values ('film', 'film');

