package ru.hehmdalolkek.treeindb.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.hehmdalolkek.treeindb.model.entity.Tree3;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Inna Badekha
 */
@Component
public class Tree3RowMapper implements RowMapper<Tree3> {

    @Override
    public Tree3 mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Tree3.builder()
                .id(rs.getInt("id"))
                .left(rs.getInt("left"))
                .right(rs.getInt("right"))
                .build();
    }

}