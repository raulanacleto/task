CREATE SEQUENCE "task_id_seq" INCREMENT 1 START 1;

CREATE TABLE task (
	id serial NOT NULL,
	description varchar(255) NOT NULL,
	finished boolean NOT NULL,
	created_at timestamp with time zone NOT NULL
);