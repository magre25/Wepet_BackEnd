create table prestador(
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

insert into prestador (cpf, nome, email, telefone, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
values('189.190.124.87', 'Carlos José da Silva', 'carlinsilva@hotmail.com', '16999216533', 'Rua 1', '10', null, 'Centro', '14.810-564', 'Araraquara', 'SP', true);

insert into prestador (cpf, nome, email, telefone, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
values('240.146.835.10','Renata Araujo dos Santos', 'renatara@yahoo.com.br', '1633375421', 'Avenida Milton Fonseca', '90', 'Apto 101', 'Jd America', '14.805-490', 'Araraquara', 'SP', true);
