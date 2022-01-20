package ru.fmt.university.dao.util;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.LessonType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;

@Service
public class LessonMapper implements RowMapper<Lesson> {

    @Override
    public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Lesson(rs.getInt("id"), rs.getInt("course_id"),
                rs.getInt("teacher_id"), rs.getInt("classroom"),
                DayOfWeek.valueOf(rs.getString("day")), rs.getTime("time").toLocalTime(),
                LessonType.valueOf(rs.getString("type")));
    }
}
