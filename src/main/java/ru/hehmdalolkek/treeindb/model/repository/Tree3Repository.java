package ru.hehmdalolkek.treeindb.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hehmdalolkek.treeindb.model.entity.Tree3;
import ru.hehmdalolkek.treeindb.model.mapper.Tree3RowMapper;

import java.util.List;

/**
 * Дерево, которое хранится в БД с помощью подхода - списка вложенности.
 *
 * @author Inna Badekha
 */
@Repository
@RequiredArgsConstructor
public class Tree3Repository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final Tree3RowMapper tree3RowMapper;

    @Transactional
    public List<Tree3> getAll() {
        return jdbcTemplate.query(
                """
                        select id, "left", "right"
                        from trees3
                        """,
                tree3RowMapper
        );
    }

    @Transactional
    public List<Tree3> getChildren(Integer id) {
        return jdbcTemplate.query(
                """
                        with parent_left_right_cte as (
                            select id, "left", "right"
                            from trees3
                            where id = :id
                        )
                        select id, "left", "right"
                        from trees3
                        where
                            "left" > (select "left" from parent_left_right_cte)
                            and "right" < (select "right" from parent_left_right_cte)
                        """,
                new MapSqlParameterSource("id", id),
                tree3RowMapper
        );
    }

    @Transactional
    public Integer create(Integer parentId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                """
                        with parent_left_right_cte as (
                            select id, "left", "right"
                            from trees3
                            where id = :parentId
                        )
                        update trees3
                        set
                            "right" = "right" + 2
                        where
                            "right" >= (select "left" from parent_left_right_cte)
                        """,
                new MapSqlParameterSource("parentId", parentId)
        );
        jdbcTemplate.update(
                """
                        with parent_left_right_cte as (
                            select id, "left", "right"
                            from trees3
                            where id = :parentId
                        )
                        update trees3
                        set
                            "left" = "left" + 2
                        where
                            "left" >= (select "left" + 1 from parent_left_right_cte)
                        """,
                new MapSqlParameterSource("parentId", parentId)
        );
        jdbcTemplate.update(
                """
                        with parent_left_right_cte as (
                            select id, "left", "right"
                            from trees3
                            where id = :parentId
                        )
                        insert into trees3 ("left", "right")
                        values
                            ((select "left" + 1 from parent_left_right_cte), (select "left" + 2 from parent_left_right_cte))
                        """,
                new MapSqlParameterSource("parentId", parentId),
                keyHolder,
                new String[]{"id"}
        );
        return keyHolder.getKeyAs(Integer.class);
    }
}
