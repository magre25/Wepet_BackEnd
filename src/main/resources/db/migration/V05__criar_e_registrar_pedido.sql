create table pedido(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_animal BIGINT(20) NOT NULL,  
	dt_inicio DATE NOT NULL,
	dt_fim DATE,
	codigo_servico BIGINT(20) NOT NULL,
	codigo_prestador BIGINT(20) NOT NULL, 
	status BOOLEAN NOT NULL,
	FOREIGN KEY (codigo_animal) REFERENCES animal(codigo),
	FOREIGN KEY (codigo_prestador) REFERENCES prestador(codigo),
	FOREIGN KEY (codigo_servico) REFERENCES servico(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pedido (codigo_animal, dt_inicio, dt_fim, codigo_servico, codigo_prestador, status)
values(3,'2021/11/10','2021/11/20', 1, 2, true);

INSERT INTO pedido (codigo_animal, dt_inicio, dt_fim, codigo_servico, codigo_prestador, status)
values(2,'2021/12/13','2021/12/30', 2, 1, true);