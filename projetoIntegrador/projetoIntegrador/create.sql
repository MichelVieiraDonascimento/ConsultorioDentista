create table if not exists dentista (id integer auto_increment primary key,
nome varchar(255), sobrenome varchar(255), matricula varchar(255));

create table if not exists paciente ( id integer auto_increment primary key, nome varchar(255), sobrenome varchar(255),
 endereco varchar(255), rg varchar(255));