package ru.hehmdalolkek.treeindb.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hehmdalolkek.treeindb.model.entity.Tree1;
import ru.hehmdalolkek.treeindb.model.mapper.Tree1RowMapper;

import java.util.List;

/**
 * Дерево, которое хранится в БД с помощью подхода - список смежности.
 *
 * @author Inna Badekha
 */
@Repository
@RequiredArgsConstructor
public class Tree1Repository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final Tree1RowMapper tree1RowMapper;

    @Transactional
    public List<Tree1> getAll() {
        return jdbcTemplate.query(
                """
                        select id, parent_id
                        from trees1
                        """,
                tree1RowMapper
        );
    }

    @Transactional
    public List<Tree1> getChildren(Integer id) {
        return jdbcTemplate.query(
                """
                        with recursive trees1_cte as (
                            select id, parent_id
                            from trees1
                            where id = :id
                        
                            union
                        
                            select t1.id, t1.parent_id
                            from trees1 t1
                                inner join trees1_cte t_cte on (t1.parent_id = t_cte.id)
                        )
                        select *
                        from trees1_cte
                        where id != :id
                        """,
                new MapSqlParameterSource("id", id),
                tree1RowMapper
        );
    }

    @Transactional
    public Integer create(Integer parentId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                """
                        insert into trees1 (parent_id)
                        values
                            (:parentId)
                        """,
                new MapSqlParameterSource("parentId", parentId),
                keyHolder,
                new String[]{"id"}
        );
        return keyHolder.getKeyAs(Integer.class);
    }
}
