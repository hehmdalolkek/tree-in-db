package ru.hehmdalolkek.treeindb.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Дерево, которое хранится в БД с помощью подхода - перечисление компонентов пути.
 *
 * @author Inna Badekha
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tree2 {

    private Integer id;

    private String path;

}
