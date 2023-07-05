INSERT INTO tb_user(name, email, password) VALUES('Pedro Donato', 'pedro@gmail.com', '123456');
INSERT INTO tb_user(name, email, password) VALUES('Gustavo De Souza', 'gustavo@gmail.com', '123456');
INSERT INTO tb_user(name, email, password) VALUES('Gabriel Soares', 'gabriel@gmail.com', '123456');
INSERT INTO tb_user(name, email, password) VALUES('Henrique Ferreira', 'henrique@gmail.com', '123456');
INSERT INTO tb_user(name, email, password) VALUES('Felipe Araujo', 'felipe@gmail.com', '123456');

INSERT INTO tb_role(role_name) VALUES('ROLE_ADMIN');
INSERT INTO tb_role(role_name) VALUES('ROLE_CLIENT');

INSERT INTO tb_user_role(user_id, role_id) VALUES(1, 1);