package ru.hehmdalolkek.treeindb.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.hehmdalolkek.treeindb.model.entity.Tree4;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Inna Badekha
 */
@Component
public class Tree4RowMapper implements RowMapper<Tree4> {

    @Override
    public Tree4 mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Tree4.builder()
                .id(rs.getInt("id"))
                .build();
    }

}