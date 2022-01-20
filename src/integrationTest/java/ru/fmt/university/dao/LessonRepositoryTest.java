package ru.fmt.university.dao;

import org.junit.jupiter.api.Test;
import ru.fmt.university.dao.exceptions.DaoException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.LessonType;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class LessonRepositoryTest extends RepositoryTest {
    private static final Lesson FOR_UPDATE = new Lesson(2, 2, 1, 10,
            DayOfWeek.THURSDAY, LocalTime.of(9, 30, 0), LessonType.LECTURE);

    private static final Lesson lesson = new Lesson(4, testCourseList.get(1).getId(), testTeacherList.get(0).getId(), 10,
            DayOfWeek.THURSDAY, LocalTime.of(9, 30, 0), LessonType.LECTURE);

    @Test
    public void create() {
        lessonRepository.create(lesson);
        assertNotEquals(testLessonList.size(), lessonRepository.getAll().size());
        Lesson actual = lessonRepository.getById(lesson.getId());
        assertEquals(lesson, actual);
    }

    @Test
    public void getAll_shouldReturnAllLessonsFromDb() {
        assertEquals(testLessonList, lessonRepository.getAll());
    }

    @Test
    public void getById() {
        assertEquals(testLessonList.get(0), lessonRepository.getById(1));
    }

    @Test
    public void getById_shouldThrowDaoException() {
        Throwable exception = assertThrows(DaoException.class,
                () -> lessonRepository.getById(10));

        assertEquals(MessagesConstants.CANNOT_GET_LESSON_BY_ID, exception.getMessage());
    }

    @Test
    public void update_shouldUpdateLessonInDb() {
        lessonRepository.update(FOR_UPDATE);
        assertEquals(FOR_UPDATE, lessonRepository.getById(2));
    }

    @Test
    public void delete_shouldDeleteLessonFromDb() {
        lessonRepository.delete(3);
        assertEquals(testLessonList.subList(0, 2), lessonRepository.getAll());
    }

    @Test
    public void getByTeacher() {
        assertEquals(testLessonList.subList(0, 2), lessonRepository.getByTeacher(testTeacherList.get(0).getId()));
    }

    @Test
    public void getByCourse() {
        assertEquals(testLessonList.subList(1, 3), lessonRepository.getByCourse(testCourseList.get(1).getId()));
    }

    @Test
    public void getByStudent() {
        assertEquals(testLessonList.subList(0, 2), lessonRepository.getByStudent(1));
    }

    @Test
    public void getByGroup() {
        assertEquals(testLessonList.subList(0, 2), lessonRepository.getByGroup(testGroupList.get(0).getId()));
    }
}
