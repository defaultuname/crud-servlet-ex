create table if not exists smartphone
(
    id    bigint auto_increment not null,
    model varchar(255)          not null,
    price decimal               not null,
    primary key (id)
);

insert into smartphone (model, price) values ('iPhone XR', 799.99);
insert into smartphone (model, price) values ('Xiaomi Redmi Note 10', 199.99);
insert into smartphone (model, price) values ('Nokia 3310', 69.49);
insert into smartphone (model, price) values ('Samsung Galaxy Fold 3', 1999.99);
insert into smartphone (model, price) values ('HTC Desire 810', 549.99);