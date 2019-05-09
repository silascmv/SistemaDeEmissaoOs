create database dbinfox;

-- criação de tabelas


create table usuarios(

	id_usu int primary key,
    nome varchar(50) not null,
    fone varchar(15),
    login varchar(15) not null unique,
    senha varchar(15) not null
    

);

	create table clientes(

	id_cliente int primary key auto_increment,
    nome_cliente varchar(50) not null,
    end_cliente varchar(100) not null,
    fone_cliente varchar(50) not null,
    email_cliente varchar(60) not null


);


create table ordem_servi(
	
    id_os int primary key auto_increment,
    fk_id_cliente int not null,
    data_os timestamp default current_timestamp,
    produto varchar(50) not null,
    descricao varchar(150) not null,
    defeito varchar(150) not null,
    servico varchar(150) not null,
    tecnico_respo varchar(50) not null,
    valor decimal(10,2),
    
    foreign key (fk_id_cliente) references clientes (id_cliente)
	


);


	

-- o comando abaixo CRUD

-- create (equivale ao insert)

insert into usuarios (id_usu,nome,fone,loin,senha)values(1,"Silas Cardoso","34472239","silascmv","059316");
-- read

select * from usuarios;

-- update (modificação de dados)

update usuarios set telefone = '8888-8888' where id_usu = 2; 


-- drop 

	delete from usuarios where id_usu =3 ;

-- _________________________________________________________

insert into clientes (id_cliente,nome_cliente,end_cliente,fone_cliente,email_cliente)values(1,"Silas Cliente","Gilvandro Soares","34452239","sillas.c@hotmail.com")



	select
O.id_os,data_os,produto,descricao,
C.id_cliente,nome_cliente,end_cliente
from clientes as C
inner join ordem_servi O 
on O.fk_id_cliente = C.id_cliente