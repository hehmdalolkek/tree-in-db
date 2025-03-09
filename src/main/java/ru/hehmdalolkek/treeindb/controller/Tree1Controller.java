package ru.hehmdalolkek.treeindb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hehmdalolkek.treeindb.model.entity.Tree1;
import ru.hehmdalolkek.treeindb.model.repository.Tree1Repository;

import java.util.List;

/**
 * Дерево, которое хранится в БД с помощью подхода - список смежности.
 *
 * @author Inna Badekha
 */
@RestController
@RequestMapping("/trees1")
@RequiredArgsConstructor
public class Tree1Controller {

    private final Tree1Repository tree1Repository;

    @GetMapping
    public List<Tree1> getAllTrees1() {
        return tree1Repository.getAll();
    }

    @PostMapping
    public Integer createTree1(@RequestBody Integer parentId) {
        return tree1Repository.create(parentId);
    }

    @GetMapping("/{id}/children")
    public List<Tree1> getTrees1Children(@PathVariable Integer id) {
        return tree1Repository.getChildren(id);
    }

}
