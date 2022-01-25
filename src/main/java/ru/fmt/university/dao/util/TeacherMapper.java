package ru.fmt.university.dao.util;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.fmt.university.dto.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int i) throws SQLException {
        return new Teacher(rs.getInt("id"), rs.getString("first_name"),
                rs.getString("last_name"), rs.getInt("course_id"));
    }
}
