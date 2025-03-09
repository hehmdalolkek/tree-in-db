-- Дерево, которое хранится в БД с помощью подхода - таблица замыканий.
create table trees4
(
    id   serial,
    primary key (id)
);

create table trees4_relations
(
    parent_id integer,
    child_id integer,
    primary key (parent_id, child_id),
    foreign key (parent_id) references trees4 (id),
    foreign key (child_id) references trees4 (id)
);

insert into trees4 (id)
values (1),
       (2),
       (3),
       (4),
       (5),
       (6),
       (7);

insert into trees4_relations (parent_id, child_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (2, 2),
       (2, 3),
       (3, 3),
       (4, 4),
       (4, 5),
       (4, 6),
       (4, 7),
       (5, 5),
       (6, 6),
       (6, 7),
       (7, 7);


alter sequence trees4_id_seq restart 8;