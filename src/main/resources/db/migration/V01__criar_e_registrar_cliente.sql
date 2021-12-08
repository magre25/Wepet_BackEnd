create table cliente(
	codigo bigint(20) PRIMARY KEY auto_increment,
	cpf VARCHAR(14)  NOT NULL,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	telefone varchar(20),
	logradouro VARCHAR(30),
	numero VARCHAR(30),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep VARCHAR(30),
	cidade VARCHAR(30),
	estado VARCHAR(30),
	ativo BOOLEAN NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into cliente (cpf, nome, email, telefone, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
values('324.145.894.90', 'Carlos José da Silva', 'carlinsilva@hotmail.com', '16999216533', 'Rua 1', '10', null, 'Centro', '14.810-564', 'Araraquara', 'SP', true);

insert into cliente (cpf, nome, email, telefone, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
values('427.894.111.43','Mariana Olivera Barbosa', 'maoliveira@yahoo.com.br', '1633375421', 'Avenida Milton Fonseca', '90', 'Apto 101', 'Jd America', '14.805-490', 'Araraquara', 'SP', true);

insert into cliente (cpf, nome, email, telefone, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
values('400.176.192.13','Bruno Ronaldo Silva', 'rsilva@gmail.com.br', '16999761934', 'Rua Sebastiao Almeida', '970', null, 'Jd America', '14.743-110', 'Araraquara', 'SP', false);

insert into cliente (cpf, nome, email, telefone, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
values('350.532.087.20','Larissa Barbosa dos Santos', 'larisantos@hotmail.com', '16999649018', 'Rua Nove de Julho', '1200', null, 'Jd Primavera', '14.800-445', 'Araraquara', 'SP', false);

insert into cliente (cpf, nome, email, telefone, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
values('427.287.325.78','Tânia Garcia Kurti', 'taniakurti@hotmail.com', '1698179764', 'Rua Milton Pirajá', '35', 'Apto 40', 'Jd Iguatemi', '14.867-395', 'Araraquara', 'SP', true);
