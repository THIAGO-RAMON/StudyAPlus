create database StudyAPLus;
use StudyAPlus;

#drop schema studyaplus;

drop table if exists recompensa;
drop table if exists desafio;
drop table if exists objetivos;
drop table if exists Tarefas;
drop table if exists usuario;

create table Usuario(
nome varchar(100) ,
senha varchar(50) not null,
idade int not null,
sobreMim varchar(500) null,
escolaridade varchar(50),
desempenho double null,
sexo varchar(10) not null,
imagem longblob,
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
foreign key (user_nome) references Usuario(nome) on update cascade
);

create table desafio(
	id int auto_increment,
    user_nome varchar(100) not null,
    titulo varchar(100) not null,
    primary key (id)
);

create table recompensa(
	id_desafio int not null,
    user_nome varchar(100) not null,
    nome varchar(30) not null,
    descricao varchar(200) not null,
    imagem longblob,
    habilitado boolean,
    foreign key (id_desafio) references desafio(id) on update cascade,
    primary key(id_desafio),
    foreign key (user_nome) references Usuario(nome) on update cascade
);

select * from tarefas;
select * from usuario;
select * from objetivos;
select * from recompensa;
select * from desafio;

delete from usuario;

#update objetivos set dataInic = '23/12/2006' where descricao= 'ua';

#insert into recompensa(id, user_nome, id_desafio, nome, descricao) values (default, 'Ramon', 1, 'Fazer sexo com teu pai', 'it now or never');
#insert into recompensa(id, user_nome, id_desafio, nome, descricao, imagem, habilitado) values (DEFAULT, 'Ramon', 3, 'Teupai', 'teu pai 3', '',true);
