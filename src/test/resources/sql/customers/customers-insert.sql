insert into users (id, username, password, role) values (100, 'ana@email.com', '$2a$12$J2ra.btWdAYC2cEGO2UKL.d6GGl5p8G1iIUu/6WjioB3vx5Gm5pV2', 'ROLE_ADMIN');
insert into users (id, username, password, role) values (101, 'bia@email.com', '$2a$12$J2ra.btWdAYC2cEGO2UKL.d6GGl5p8G1iIUu/6WjioB3vx5Gm5pV2', 'ROLE_CLIENTE');
insert into users (id, username, password, role) values (102, 'bob@email.com', '$2a$12$J2ra.btWdAYC2cEGO2UKL.d6GGl5p8G1iIUu/6WjioB3vx5Gm5pV2', 'ROLE_CLIENTE');
insert into users (id, username, password, role) values (103, 'toby@email.com', '$2a$12$J2ra.btWdAYC2cEGO2UKL.d6GGl5p8G1iIUu/6WjioB3vx5Gm5pV2', 'ROLE_CLIENTE');

insert into customers (id, name, cpf, id_user) values (10, 'Bianca Silva', '00732469058', 101);
insert into customers (id, name, cpf, id_user) values (20, 'Roberto Gomes', '26009338085', 102);
