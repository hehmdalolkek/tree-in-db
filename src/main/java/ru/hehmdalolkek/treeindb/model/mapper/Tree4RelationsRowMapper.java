package ru.hehmdalolkek.treeindb.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.hehmdalolkek.treeindb.model.entity.Tree4Relation;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Inna Badekha
 */
@Component
public class Tree4RelationsRowMapper implements RowMapper<Tree4Relation> {

    @Override
    public Tree4Relation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Tree4Relation.builder()
                .parentId(rs.getInt("parent_id"))
                .childId(rs.getInt("child_id"))
                .build();
    }

}