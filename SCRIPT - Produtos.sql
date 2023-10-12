create database uc11;

use uc11;

create table Produtos(
	id int not null primary key auto_increment,
    nome varchar(100) not null,
    valor int not null,
    status varchar(40) not null
)
