CREATE TABLE servico(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	valor decimal(8,2),
	ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO servico (descricao,valor,ativo) 
values ('hospedagem hotel espaço animal',250.00, true);

INSERT INTO servico(descricao,valor,ativo)
values ('hospedagem área verde', 400.00, false);