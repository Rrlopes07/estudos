ALTER TABLE usuarios DROP COLUMN roles;

CREATE TABLE roles(

    id serial PRIMARY KEY,
    nome varchar(100) not null

);

CREATE TABLE usuario_role(

    usuario_id serial,
    role_id serial,
    PRIMARY KEY (usuario_id, role_id),
    FOREIGN KEY (usuario_id) references usuarios(id),
    FOREIGN KEY(role_id) references roles(id)

);