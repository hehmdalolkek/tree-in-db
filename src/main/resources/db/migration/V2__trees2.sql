-- Дерево, которое хранится в БД с помощью подхода - перечисление компонентов пути.
create table trees2
(
    id   serial,
    path text,
    primary key (id)
);

insert into trees2 (id, path)
values (1, '1/'),
       (2, '1/2/'),
       (3, '1/2/3/'),
       (4, '1/4/'),
       (5, '1/4/5/'),
       (6, '1/4/6/'),
       (7, '1/4/6/7/');


alter sequence trees2_id_seq restart 8;