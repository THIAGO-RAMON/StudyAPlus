create database StudyAPLus;
use StudyAPlus;

drop table if exists usuario;
drop table if exists Tarefas;

create table Usuario(
id int auto_increment,
nome varchar(100) ,
senha varchar(50) not null,
sobreMim varchar(200) null,
escolaridade varchar(50),
objetivo varchar (100),
desempenho double null,
primary key(id,nome) 
);

create table Tarefas(
id int auto_increment,
User_nome varchar(100),
titulo varchar(40) not null,
descricao varchar(300), 
dataInic varchar(10),
dataFim varchar(10),
importante boolean,
concluido boolean,
primary key (id, User_nome),
foreign key (id,User_nome) references Usuario(id,nome) on update cascade on delete restrict
);


select * from tarefas;
select * from usuario;


