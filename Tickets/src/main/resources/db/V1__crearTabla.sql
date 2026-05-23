create table ticket (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente varchar(255) not null,
    evento varchar(255) not null,
    precio double not null,
    cantidad integer not null,
);