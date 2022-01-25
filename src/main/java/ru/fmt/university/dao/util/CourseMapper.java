package ru.fmt.university.dao.util;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.fmt.university.dto.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CourseMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Course(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
    }
}
