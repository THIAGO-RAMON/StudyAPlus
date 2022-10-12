create database StudyAPLus;
use StudyAPlus;

drop table if exists usuario;
drop table if exists Tarefas;
drop table if exists objetivos;

create table Usuario(
nome varchar(100) ,
senha varchar(50) not null,
sobreMim varchar(200) null,
escolaridade varchar(50),
desempenho double null,
primary key(nome) 
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
foreign key (User_nome) references Usuario(nome) on update cascade
);

create table objetivos(
id int auto_increment,
user_nome varchar(100) ,
descricao varchar(150),
dataInic varchar(10), 
primary key (id),
foreign key (user_nome) references Usuario(nome)
);


select * from tarefas;
select * from usuario;
select * from objetivos;

delete from objetivos;
update objetivos set dataInic = '23/12/2006' where descricao= 'ua';

