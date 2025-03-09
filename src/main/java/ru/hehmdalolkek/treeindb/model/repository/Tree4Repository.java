package ru.hehmdalolkek.treeindb.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hehmdalolkek.treeindb.model.entity.Tree4;
import ru.hehmdalolkek.treeindb.model.entity.Tree4Relation;
import ru.hehmdalolkek.treeindb.model.mapper.Tree4RelationsRowMapper;
import ru.hehmdalolkek.treeindb.model.mapper.Tree4RowMapper;

import java.util.List;

/**
 * Дерево, которое хранится в БД с помощью подхода - таблица замыканий.
 *
 * @author Inna Badekha
 */
@Repository
@RequiredArgsConstructor
public class Tree4Repository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final Tree4RowMapper tree4RowMapper;

    private final Tree4RelationsRowMapper tree4RelationsRowMapper;

    @Transactional
    public List<Tree4> getAll() {
        return jdbcTemplate.query(
                """
                        select id
                        from trees4
                        """,
                tree4RowMapper
        );
    }

    @Transactional
    public List<Tree4Relation> getAllRelations() {
        return jdbcTemplate.query(
                """
                        select parent_id, child_id
                        from trees4_relations
                        """,
                tree4RelationsRowMapper
        );
    }

    @Transactional
    public List<Tree4> getChildren(Integer id) {
        return jdbcTemplate.query(
                """
                        select id
                        from trees4
                            left join trees4_relations on trees4.id = trees4_relations.child_id
                        where
                            trees4_relations.parent_id = :id
                            and trees4_relations.child_id != :id
                        """,
                new MapSqlParameterSource("id", id),
                tree4RowMapper
        );
    }

    @Transactional
    public Integer create(Integer parentId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                """
                        insert into trees4
                        default values
                        """,
                new MapSqlParameterSource(),
                keyHolder,
                new String[]{"id"}
        );
        Integer id = keyHolder.getKeyAs(Integer.class);
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("parentId", parentId);
        jdbcTemplate.update(
                """
                        insert into trees4_relations (parent_id, child_id)
                        select parent_id, :id
                        from trees4_relations
                        where child_id = :parentId
                        union all
                        select :id, :id
                        """,
                params
        );
        return id;
    }
}
