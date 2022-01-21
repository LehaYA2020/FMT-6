package ru.fmt.university.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.fmt.university.UniversityApp;
import ru.fmt.university.dao.*;
import ru.fmt.university.dto.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import static java.util.Collections.singletonList;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {UniversityApp.class})
public abstract class ServiceTest {

    protected static Course expectedCourse = new Course(1, "Test", "Course");
    protected static Group expectedGroup = new Group(1, "Test");
    protected static Student expectedStudent = new Student(1, "fName", "lName");
    protected static Teacher expectedTeacher = new Teacher(1, "TestT", "lName", expectedCourse.getId());
    protected static Lesson expectedLesson = new Lesson(1, expectedCourse.getId(), expectedTeacher.getId(), 10, DayOfWeek.MONDAY,
            LocalTime.of(9, 30, 0), LessonType.LECTURE);
    protected static List<Lesson> expectedLessons = singletonList(expectedLesson);
    protected static List<Teacher> expectedTeachers = singletonList(expectedTeacher);
    protected static List<Course> expectedCourses = singletonList(expectedCourse);
    protected static List<Group> expectedGroups = singletonList(expectedGroup);
    protected static List<Student> expectedStudents = singletonList(expectedStudent);
    @InjectMocks
    @Autowired
    protected ICourseService courseService;
    @InjectMocks
    @Autowired
    protected IGroupService groupService;
    @InjectMocks
    @Autowired
    protected ILessonService lessonService;
    @InjectMocks
    @Autowired
    protected IStudentService studentService;
    @InjectMocks
    @Autowired
    protected ITeacherService teacherService;

    @Mock
    protected ILessonService lessonServiceMock;

    @Mock
    protected ICourseRepository courseRepository;
    @Mock
    protected IGroupRepository groupRepository;
    @Mock
    protected ILessonRepository lessonRepository;
    @Mock
    protected IStudentRepository studentRepository;
    @Mock
    protected ITeacherRepository teacherRepository;
}
