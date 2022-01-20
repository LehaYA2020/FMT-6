package ru.fmt.university.dao;

import ru.fmt.university.dto.Teacher;

import java.util.List;

public interface ITeacherRepository extends IRepository<Teacher, Integer> {
    List<Teacher> getByCourse(Integer courseId);
    Teacher getByLesson(Integer lessonId);
}
