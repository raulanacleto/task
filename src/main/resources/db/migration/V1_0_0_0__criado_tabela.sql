CREATE SEQUENCE "tarefa_id_seq" INCREMENT 1 START 4;

CREATE TABLE tarefa (
	id serial NOT NULL,
	titulo varchar(255) NOT NULL,
	descricao varchar(255) NOT NULL,
	concluido boolean NOT NULL,
	data_criacao timestamp with time zone NOT NULL,
	data_atualizacao timestamp with time zone,
	data_exclusao timestamp with time zone,
	data_conclusao timestamp with time zone
);

ALTER TABLE "tarefa" ADD CONSTRAINT "PK_tarefa"
	PRIMARY KEY ("id")
;