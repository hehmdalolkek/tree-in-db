package ru.hehmdalolkek.treeindb.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.hehmdalolkek.treeindb.model.entity.Tree1;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Inna Badekha
 */
@Component
public class Tree1RowMapper implements RowMapper<Tree1> {

    @Override
    public Tree1 mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Tree1.builder()
                .id(rs.getInt("id"))
                .parentId(rs.getInt("parent_id"))
                .build();
    }

}