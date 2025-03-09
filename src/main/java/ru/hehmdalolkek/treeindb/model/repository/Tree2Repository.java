package ru.hehmdalolkek.treeindb.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hehmdalolkek.treeindb.model.entity.Tree2;
import ru.hehmdalolkek.treeindb.model.mapper.Tree2RowMapper;

import java.util.List;

/**
 * Дерево, которое хранится в БД с помощью подхода - перечисление компонентов пути.
 *
 * @author Inna Badekha
 */
@Repository
@RequiredArgsConstructor
public class Tree2Repository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final Tree2RowMapper tree2RowMapper;

    @Transactional
    public List<Tree2> getAll() {
        return jdbcTemplate.query(
                """
                        select id, path
                        from trees2
                        """,
                tree2RowMapper
        );
    }

    @Transactional
    public List<Tree2> getChildren(Integer id) {
        return jdbcTemplate.query(
                """
                        with parent_path_cte as (
                            select path
                            from trees2
                            where id = :id
                        )
                        select id, path
                        from trees2
                        where
                            path like concat((select path from parent_path_cte), '%')
                            and path != (select path from parent_path_cte)
                        """,
                new MapSqlParameterSource("id", id),
                tree2RowMapper
        );
    }

    @Transactional
    public Integer create(Integer parentId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                """
                        insert into trees2 (path)
                        values
                            ((select path from trees2 where id = :parentId))
                        """,
                new MapSqlParameterSource("parentId", parentId),
                keyHolder,
                new String[]{"id"}
        );
        Integer id = keyHolder.getKeyAs(Integer.class);
        jdbcTemplate.update(
                """
                        update trees2
                        set path = concat(path, :id, '/')
                        where id = :id
                        """,
                new MapSqlParameterSource("id", id)
        );
        return id;
    }
}
