package ru.hehmdalolkek.treeindb.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.hehmdalolkek.treeindb.model.entity.Tree2;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Inna Badekha
 */
@Component
public class Tree2RowMapper implements RowMapper<Tree2> {

    @Override
    public Tree2 mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Tree2.builder()
                .id(rs.getInt("id"))
                .path(rs.getString("path"))
                .build();
    }

}