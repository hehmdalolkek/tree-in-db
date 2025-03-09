-- Дерево, которое хранится в БД с помощью подхода - списка вложенности.
create table trees3
(
    id   serial,
    "left" integer,
    "right" integer,
    primary key (id)
);

insert into trees3 (id, "left", "right")
values (1, 1, 14),
       (2, 2, 5),
       (3, 3, 4),
       (4, 6, 13),
       (5, 7, 8),
       (6, 9, 12),
       (7, 10, 11);


alter sequence trees3_id_seq restart 8;