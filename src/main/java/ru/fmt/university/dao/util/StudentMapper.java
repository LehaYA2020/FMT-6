package ru.fmt.university.dao.util;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.fmt.university.dto.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(rs.getInt("id"), rs.getString("first_name"),
                rs.getString("last_name"), rs.getInt("group_id"));
    }
}
