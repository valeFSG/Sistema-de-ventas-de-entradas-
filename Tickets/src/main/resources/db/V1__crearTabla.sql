create table ticket (
    id bigint not null auto_increment,
    cliente varchar(255) not null,
    evento varchar(255) not null,
    precio double not null,
    cantidad integer not null,
    primary key (id)
);