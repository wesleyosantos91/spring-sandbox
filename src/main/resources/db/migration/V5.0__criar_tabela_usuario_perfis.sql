CREATE TABLE usuario_perfis (
	usuario_codigo BIGINT NOT NULL,
	perfis_codigo  BIGINT NOT NULL,
    constraint usuario_fk FOREIGN KEY (usuario_codigo) REFERENCES usuario (codigo),
	constraint perfil_fk FOREIGN KEY (perfis_codigo) REFERENCES perfil (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;