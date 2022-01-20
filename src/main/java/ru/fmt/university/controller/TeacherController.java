package ru.fmt.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.Teacher;
import ru.fmt.university.service.implementation.TeacherService;

import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping(value = "/teachers")
    public ResponseEntity<?> create(@RequestBody Teacher teacher) {
        teacherService.create(teacher);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAll() {
        final List<Teacher> teachers = teacherService.getAll();

        return teachers.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                :  new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable(name = "id") int id) {
        final Teacher teacher = teacherService.getById(id);

        return teacher != null
                ? new ResponseEntity<>(teacher, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/teachers/{id}")
    public ResponseEntity<?> update(@RequestBody Teacher teacher) {
        final Teacher updated = teacherService.update(teacher);
        return updated != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/teachers/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = teacherService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/lessons/{lessonId}")
    public ResponseEntity<Teacher> getTeacherByLesson(@PathVariable(name = "lessonId") int lessonId) {
        final Teacher teacher = teacherService.getByLesson(lessonId);

        return teacher != null
                ? new ResponseEntity<>(teacher, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lessons/{courseId}")
    public ResponseEntity<List<Teacher>> getTeachersByCourse(@PathVariable(name = "courseId") int courseId) {
        final List<Teacher> teachers = teacherService.getByCourse(courseId);

        return !teachers.isEmpty()
                ? new ResponseEntity<>(teachers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lessons/{teacherId}")
    public ResponseEntity<List<Lesson>> getTeacherSchedule(@PathVariable(name = "teacherId") int teacherId) {
        final List<Lesson> lessons = teacherService.getSchedule(teacherId);

        return !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
