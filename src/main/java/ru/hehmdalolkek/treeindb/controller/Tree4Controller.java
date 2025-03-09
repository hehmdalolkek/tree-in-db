package ru.hehmdalolkek.treeindb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hehmdalolkek.treeindb.model.entity.Tree4;
import ru.hehmdalolkek.treeindb.model.entity.Tree4Relation;
import ru.hehmdalolkek.treeindb.model.repository.Tree4Repository;

import java.util.List;

/**
 * Дерево, которое хранится в БД с помощью подхода - таблица замыканий.
 *
 * @author Inna Badekha
 */
@RestController
@RequestMapping("/trees4")
@RequiredArgsConstructor
public class Tree4Controller {

    private final Tree4Repository tree4Repository;

    @GetMapping
    public List<Tree4> getAllTrees4() {
        return tree4Repository.getAll();
    }


    @GetMapping("/relations")
    public List<Tree4Relation> getAllTrees4Relations() {
        return tree4Repository.getAllRelations();
    }

    @PostMapping
    public Integer createTree4(@RequestBody Integer parentId) {
        return tree4Repository.create(parentId);
    }

    @GetMapping("/{id}/children")
    public List<Tree4> getTrees4Children(@PathVariable Integer id) {
        return tree4Repository.getChildren(id);
    }

}
