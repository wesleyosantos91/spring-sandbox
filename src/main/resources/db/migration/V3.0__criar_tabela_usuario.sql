CREATE TABLE usuario (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO usuario (nome, email, senha) VALUES ('wesley', 'wesleyosantos91@gmail.com', '$2a$10$WUjYAnt/qYAtttjAtTxDBe9jG3Nws2CjuMEHxFHjrxmVrWCLnL8Bu');