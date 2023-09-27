-- criação do banco de dados para o cenário de E-commerce
CREATE DATABASE IF NOT EXISTS ecommerce;
USE ecommerce;

-- criar tabela cliente
CREATE TABLE IF NOT EXISTS clients(
    idClient int auto_increment primary key,
    fName varchar(10),
    mInit char(3),
    lName varchar(20),
    CPF char(11) not null,
    address varchar(80),
    constraint unique_CPF_client unique(CPF)
);

alter table clients auto_increment=1;

/* criar tabela produto
   size = dimensão do produto */
CREATE TABLE IF NOT EXISTS products(
    idProduct int auto_increment primary key,
    pName varchar(10) not null,
    classificationKids bool default false,
    category enum('Eletrônico', 'Vestimenta', 'Brinquedos', 'Alimentos', 'Móveis') not null,
    rate float default 0,
    size varchar(10)
);

-- criar tabela pedido
CREATE TABLE IF NOT EXISTS orders(
	idOrder int auto_increment primary key,
    idOrderClient int,
    orderStatus enum('Cancelado', 'Confirmado', 'Em processamento') default 'Em processamento',
    orderDescription varchar(255),
    shipCost float default 10,
    constraint fk_orders_client foreign key(idOrderClient) references clients(idClient)
);

-- criar tabela pagamentos
CREATE TABLE IF NOT EXISTS payments(
	idPayment int,
    idPaymentOrder int,
    price float,
    paymentType enum('Crédito', 'Débito', 'Boleto') not null,
    isPaid bool default false,
    primary key(idPayment),
    constraint fk_payment_orders foreign key(idPaymentOrder) references orders(idOrder)
);

-- criar tabela estoque
CREATE TABLE IF NOT EXISTS storages(
	idStorage int auto_increment primary key,
    local varchar(40) not null
);

-- cria tabela de relacionamento entre produtos e estoques
CREATE TABLE IF NOT EXISTS productStorage(
	idStorage int,
    idProduct int,
    quantity int,
    primary key(idStorage, idProduct),
    foreign key(idStorage) references storages(idStorage),
    foreign key(idProduct) references products(idProduct)
);

-- criar tabela de informações dos envolvidos em fornecimento ou venda
CREATE TABLE IF NOT EXISTS informations(
	idInfo int auto_increment primary key,
    socialName varchar(255) not null,
    CNPJ char(15) unique,
    CPF char (9) unique,
    contact char(10) not null
);

-- criar tabela fornecedor
CREATE TABLE IF NOT EXISTS supplier(
	idSupplier int auto_increment primary key,
    idInfo int,
    foreign key(idInfo) references informations(idInfo)
);

-- cria tabela de relacionamento entre produtos e fornecedores
CREATE TABLE IF NOT EXISTS productSuppliers(
	idSupplier int,
    idProduct int,
    primary key(idSupplier, idProduct),
    foreign key(idSupplier) references supplier(idSupplier),
    foreign key(idProduct) references products(idProduct)
);

-- criar tabela vendedor
CREATE TABLE IF NOT EXISTS sellers(
	idSeller int auto_increment primary key,
    idInfo int,
    abstName varchar(255),
    location varchar(255),
    foreign key(idInfo) references informations(idInfo)
);

-- cria tabela de relacionamento entre produtos e vendedores
CREATE TABLE IF NOT EXISTS productSeller(
	idSeller int,
    idProduct int,
    quantity int default 1,
    primary key(idSeller, idProduct),
    foreign key(idSeller) references sellers(idSeller),
    foreign key(idProduct) references products(idProduct)
);

-- cria tabela de relacionamento entre produtos e pedidos
CREATE TABLE IF NOT EXISTS productOrders(
	idOrder int,
    idProduct int,
    quantity int default 1,
    productStatus bool,
    primary key(idOrder, idProduct),
    foreign key(idOrder) references orders(idOrder),
    foreign key(idProduct) references products(idProduct)
);


-- Inserção de valores
INSERT INTO clients(fName, mInit, lName, CPF, address) VALUES
	('Maria','M','Silva', 123456789,'Rua silva da prata 29, Carangola - Cidade das flores'),
    ('Matheus','O','Pimentel',987654321,'Rua alameda 289, Centro - Cidade das flores'),
    ('Ricardo', 'F','Silva',456789123,'Avenida alameda vinha, 1009, Centro - Cidade das flores'),
    ('Julia', 'S','França',789123456,'Rua laranjeiras, 861, Centro - Cidade das flores'),
    ('Roberta', 'G','Assis',987456312,'Avenida koller, 19, Centro - Cidade das flores'),
    ('Isabela', 'M','Cruz',654789123,'Avenida alameda das flores, 28, Centro - Cidade das flores');
    
INSERT INTO product(pName, classificationKids, category, rate, size) VALUES
	('Fone de ouvido',false,'Eletrônico','4',null),
    ('Barbie Elsa',true,'Brinquedos','3',null),
    ('Body Carters',true,'Vestimenta','5',null),
    ('Microfone Vedo - Youtuber',false,'Eletrônico','4',null),
    ('Sofá retrátil',false,'Móveis','3','3x57x80'),
    ('Farinha de arroz',false,'Alimentos','2',null),
    ('Fire Stick Amazon',false,'Eletrônico','3',null);
    
INSERT INTO orders(idOrderClient, orderStatus, orderDescription, shipCost) VALUES
	(1,default,'Compra via aplicativo',null,1),
    (2,default,'Compra via aplicativo',50,0),
    (3,'Confirmado',null,null,1),
    (4,default,'Compra via web site',150,0);

INSERT INTO productOrders(idProduct, idOrder, quantity, productStatus) VALUES
	(1,1,2,null),
    (2,1,1,null),
    (3,2,1,null);
    
INSERT INTO storages(local) VALUES
	('Rio de Janeiro'),
    ('São Paulo'),
    ('Brasília');
    
INSERT INTO productStorage(idProductStorage, idProduct, quantity) VALUES
	(1,2,3),
    (2,1,1),
    (3,5,5);

INSERT INTO informations(socialName, CNPJ, contact) VALUES
	('Almeida e filhos', 812345678912345, '21985474'),
    ('Eletrônicos Silva', 598745612365478, '21985484'),
    ('Eletrônicos Valma', 456321789654852, '21975474');
    
INSERT INTO supplier(idInfo) VALUES
	(1),
    (2);

INSERT INTO productSupplier(idSupplier, idProduct) VALUES
	(1,1),
    (1,2),
    (2,4),
    (2,3),
    (2,5);

INSERT INTO sellers(idInfo, abstName, location) VALUES
	(3, 'Lojas Amaral', 'Agapé');

INSERT INTO productSeller(idSeller, idProduct, quantity) VALUES
	(1, 2, 15),
    (1, 4, 3),
    (1, 5, 2);

-- cláusulas de consulta
SELECT 
	count(*) 
FROM 
	clients;

SELECT 
	fName, lName, idOrder, orderStatus
FROM 
	clients c, orders o
WHERE 
	c.idClient = idOrderClient;

SELECT
	concat(fName, ' ', lName) AS Cliente, idOrder AS Request, orderStatus AS Status
FROM
	clientes c, orders o
WHERE 
	c.idClient = idOrderClient;
    
SELECT 
	c.idClient, fName, count(*) as NumberOfOrders 
FROM 
	clients c 
    LEFT OUTER JOIN orders o ON c.idClient = o.idOrderClient
    INNER JOIN product p ON p.idOrder = o.idOrder
GROUP BY idClient;