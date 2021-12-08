create table animal(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	tipo VARCHAR(10) NOT NULL,
	idade SMALLINT,
	sexo VARCHAR(10),
	castrado VARCHAR(5),
	raca VARCHAR(20),		
	ativo BOOLEAN NOT NULL,
	codigo_cliente BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO animal(nome,tipo,idade,sexo,castrado,raca,ativo, codigo_cliente)
values ('Tobias','Cão', 5 ,'macho','sim','Pit Bull', true, 1);

INSERT INTO animal(nome,tipo,idade,sexo,castrado,raca,ativo, codigo_cliente)
values ('Bastião','Gato', 2 ,'macho','sim', null, true, 4);

INSERT INTO animal(nome,tipo,idade,sexo,castrado,raca,ativo, codigo_cliente)
values ('Amora','Cão', 2 ,'macho','sim','Pit Bull', true, 3);