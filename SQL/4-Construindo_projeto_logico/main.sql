-- criação do banco de dados para o cenário de Oficina
CREATE DATABASE IF NOT EXISTS oficina;
USE oficina;

-- criar tabela cliente
CREATE TABLE IF NOT EXISTS clients(
    idClient int auto_increment primary key,
    name varchar(50) not null,
    CPF char(9) unique not null
);

-- criar tabela carro
CREATE TABLE IF NOT EXISTS cars(
    idCar int auto_increment primary key,
    idClient int,
    identifier char(8) not null,
    foreign key(idClient) references clients.idClient
);

-- criar tabela ordem de servico
CREATE TABLE IF NOT EXISTS serviceOrders(
    idServiceOrder int auto_increment primary key,
    emissionDate date not null,
    value float not null,
    status enum('Concluído', 'Cancelado', 'Processando') default 'Processando',
    conclusionDate date 
);

-- criar tabela mecanico
CREATE TABLE IF NOT EXISTS mechanicists(
    idMechanicist int auto_increment primary key,
    name varchar(50) not null,
    address varchar(255) not null,
    speciality varchar(255)
);

-- criar tabela serviço
CREATE TABLE IF NOT EXISTS services(
    idService int auto_increment primary key,
    name varchar(50) not null,
    value float not null
);

-- criar tabela produto
CREATE TABLE IF NOT EXISTS products(
    idProduct int auto_increment primary key,
    name varchar(50) not null,
    value float not null
);

-- criar tabela pedido
CREATE TABLE IF NOT EXISTS orders(
    idOrder int auto_increment primary key,
    idClient int,
    idServiceOrder int,
    foreign key(idClient) references clients.idClient,
    foreign key(idServiceOrder) references serviceOrders.idServiceOrder
);

-- criar relação entre pedido e mecanico
CREATE TABLE IF NOT EXISTS mechanicistsPerOrders(
    idMechanicist int,
    idOrder int,
    quantity int not null,
    primary key(idMechanicist, idOrder),
    foreign key(idMechanicist) references mechanicists.idMechanicist,
    foreign key(idOrder) references orders.idOrder
);

-- criar relação entre pedido e servico
CREATE TABLE IF NOT EXISTS servicesPerOrder(
    idOrder int,
    idService int,
    quantity int not null,
    primary key(idOrder, idProduct),
    foreign key(idOrder) references orders.idOrder,
    foreign key(idService) references services.idService
);

-- criar relação entre pedido e produto
CREATE TABLE IF NOT EXISTS productsPerOrder(
    idOrder int,
    idProduct int,
    quantity int not null,
    primary key(idOrder, idProduct),
    foreign key(idOrder) references orders.idOrder,
    foreign key(idProduct) references products.idProduct
);

-- Inserção de valores
INSERT INTO clients(name, CPF) VALUES
	('José Juarez da Silva', 123456789),
    ('Claudio Alvarez Góes', 789456123),
    ('Ana Claudia Silva', 123789654),
    ('Juliana Alvarez', 456789321),
    ('Luan Cordeiro', 741369820);

INSERT INTO cars(idClient, identifier) VALUES
    (1, ABC1236),
    (2, BCN4568),
    (3, KCL7895),
    (4, LSP7563),
    (5, KCJ4219);

INSERT INTO serviceOrders(emissionDate, value, status, conclusionDate) VALUES
    ('2022-11-23', 750, 'Processando', null),
    ('2022-01-05', 500, 'Concluído', '2022-07-18'),
    ('2022-07-09', 300, 'Concluído', '2022-09-07');

INSERT INTO mechanicists(name, address, speciality) VALUES
    ('Juan Claudio Amargo', 'Rua Imaculada Conceição, 239', 'Injeção elétrica'),
    ('Amarildo Dos Anjos', 'Alameda Conceição, 67', null),
    ('Stephane Amaraldo', 'Rua Inácio de Freitas, 11', 'Martelinho de ouro');

INSERT INTO products(name, value) VALUES
    ('Óleo lubrificante', 100.00),
    ('Fusível', 50.00),
    ('Retrovisor', 75.00);

INSERT INTO services(name, value) VALUES
    ('Manutenção', 75.00),
    ('Limpeza', 50.00),
    ('Checagem', 25.00);

INSERT INTO mechanicistsPerOrders(idMechanicist, idOrder) VALUES
    (1,1),
    (2,2),
    (3,3);

INSERT INTO servicesPerOrder(idOrder, idService, quantity) VALUES
    (1,1,2),
    (2,2,1),
    (3,1,1),
    (3,2,1),
    (3,1,1);

INSERT INTO productsPerOrder(idOrder, idProduct, quantity) VALUES
    (1,1,6),
    (2,2,9),
    (3,3,2);

-- Query de consulta
SELECT 
    c.name, count(*), value
FROM
    clients c INNER JOIN orders o ON c.idClient = o.idClient
    INNER JOIN serviceOrders s ON o.idClient = s.idClient
GROUP BY 
    idClient
HAVING 
    value > 200.00
ORDER BY 
    value; 