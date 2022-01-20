package ru.fmt.university.dao;

import org.springframework.stereotype.Repository;
import ru.fmt.university.dto.Lesson;

import java.util.List;

@Repository
public interface ILessonRepository extends IRepository<Lesson, Integer> {
    List<Lesson> getByStudent(Integer studentId);
    List<Lesson> getByTeacher(Integer teacherId);
    List<Lesson> getByGroup(Integer groupId);
    List<Lesson> getByCourse(Integer courseId);
}
