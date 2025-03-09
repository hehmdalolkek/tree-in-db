package ru.hehmdalolkek.treeindb.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Дерево, которое хранится в БД с помощью подхода - списка вложенности.
 *
 * @author Inna Badekha
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tree3 {

    private Integer id;

    private Integer left;

    private Integer right;

}
