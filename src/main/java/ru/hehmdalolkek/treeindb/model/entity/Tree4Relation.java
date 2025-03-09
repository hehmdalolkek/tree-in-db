package ru.hehmdalolkek.treeindb.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс отношений (родитель/потомок) дерева, которое хранится в БД с помощью подхода - таблица замыканий.
 *
 * @author Inna Badekha
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tree4Relation {

    private Integer parentId;

    private Integer childId;

}
