package ru.hehmdalolkek.treeindb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hehmdalolkek.treeindb.model.entity.Tree2;
import ru.hehmdalolkek.treeindb.model.repository.Tree2Repository;

import java.util.List;

/**
 * Дерево, которое хранится в БД с помощью подхода - перечисление компонентов пути.
 *
 * @author Inna Badekha
 */
@RestController
@RequestMapping("/trees2")
@RequiredArgsConstructor
public class Tree2Controller {

    private final Tree2Repository tree2Repository;

    @GetMapping
    public List<Tree2> getAllTrees2() {
        return tree2Repository.getAll();
    }

    @PostMapping
    public Integer createTree2(@RequestBody Integer parentId) {
        return tree2Repository.create(parentId);
    }

    @GetMapping("/{id}/children")
    public List<Tree2> getTrees2Children(@PathVariable Integer id) {
        return tree2Repository.getChildren(id);
    }

}
