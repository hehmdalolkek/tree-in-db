package ru.hehmdalolkek.treeindb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hehmdalolkek.treeindb.model.entity.Tree3;
import ru.hehmdalolkek.treeindb.model.repository.Tree3Repository;

import java.util.List;

/**
 * Дерево, которое хранится в БД с помощью подхода - списка вложенности.
 *
 * @author Inna Badekha
 */
@RestController
@RequestMapping("/trees3")
@RequiredArgsConstructor
public class Tree3Controller {

    private final Tree3Repository tree3Repository;

    @GetMapping
    public List<Tree3> getAllTrees3() {
        return tree3Repository.getAll();
    }

    @PostMapping
    public Integer createTree3(@RequestBody Integer parentId) {
        return tree3Repository.create(parentId);
    }

    @GetMapping("/{id}/children")
    public List<Tree3> getTrees3Children(@PathVariable Integer id) {
        return tree3Repository.getChildren(id);
    }

}
