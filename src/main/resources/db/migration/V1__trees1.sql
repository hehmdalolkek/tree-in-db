-- Дерево, которое хранится в БД с помощью подхода - список смежности.
create table trees1
(
    id        serial,
    parent_id integer,
    primary key (id),
    foreign key (parent_id) references trees1 (id)
);

insert into trees1 (id, parent_id)
values (1, null),
       (2, 1),
       (3, 2),
       (4, 1),
       (5, 4),
       (6, 4),
       (7, 6);

alter sequence trees1_id_seq restart 8;