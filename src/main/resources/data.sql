insert into music_group(name, members) values('Ramin Djawadi', 1);
insert into music_group(name, members) values('Justin Hurwitz', 1);
insert into music_group(name, members) values('Metallica', 4);

insert into vinyl(name, publish_date, price, size, music_group_id) values('Game of thrones', now(), 15.95, 'SMALL', 1);
insert into vinyl(name, publish_date, price, size, music_group_id) values('La la land', now(), 19.95, 'MINI', 2);
insert into vinyl(name, publish_date, price, size, music_group_id) values('Master of Puppets', now(), 12.95, 'STANDARD', 3);

insert into client(name, password) values('Cristian', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');
insert into client(name, password) values('Alberto', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');
insert into client(name, password) values('Elena', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');
insert into client(name, password) values('Pilar', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');
insert into client(name, password) values('Cuco', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');
insert into client(name, password) values('Jorge', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');

insert into authority(id, name) values(1, 'ROLE_ADMIN');
insert into authority(id, name) values(2, 'ROLE_USER');

insert into client_authorities(users_id, authorities_id) values(1, 1);
insert into client_authorities(users_id, authorities_id) values(2, 2);
insert into client_authorities(users_id, authorities_id) values(3, 2);
insert into client_authorities(users_id, authorities_id) values(4, 2);
insert into client_authorities(users_id, authorities_id) values(5, 2);
insert into client_authorities(users_id, authorities_id) values(6, 2);

insert into purchase(order_number, client_id, quantity, purchase_date, vinyl_id) values('ASD543', 1, 2, now(), 1);
insert into purchase(order_number, client_id, quantity, purchase_date, vinyl_id) values('KJD124', 2, 1, now(), 2);
insert into purchase(order_number, client_id, quantity, purchase_date, vinyl_id) values('CNV963', 3, 4, now(), 3);
