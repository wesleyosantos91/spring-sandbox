CREATE TABLE pessoa (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	data_nascimento DATE NOT NULL,
	cpf VARCHAR(11),
	email VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;