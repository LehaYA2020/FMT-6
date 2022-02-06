package ru.fmt.university.dao.implementation.hibernate;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import ru.fmt.university.dao.exceptions.DaoException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.dao.interfaces.ITeacherRepository;
import ru.fmt.university.dao.util.TeacherMapper;
import ru.fmt.university.model.dto.Teacher;
import ru.fmt.university.model.entity.CourseEntity;
import ru.fmt.university.model.entity.LessonEntity;
import ru.fmt.university.model.entity.TeacherEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@Repository
@Transactional
@ConditionalOnProperty(name = "daoImpl", havingValue = "hibernate")
public class TeacherRepositoryHibernateImpl implements ITeacherRepository {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private TeacherMapper teacherMapper;
    private EntityManager entityManager;

    public Teacher create(Teacher teacher) {
        log.trace("create({})", teacher);
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(teacherMapper.toEntity(teacher));
            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (RuntimeException e) {
            log.error(MessagesConstants.CANNOT_INSERT_TEACHERS_LIST, e);
            throw new DaoException(MessagesConstants.CANNOT_INSERT_TEACHERS_LIST, e);
        }
        log.debug("Teacher {} created.", teacher);
        return teacher;
    }

    public List<Teacher> getAll() {
        log.trace("getAll()");
        List<Teacher> teachers;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            teachers = teacherMapper.toTeacher(entityManager.createQuery("FROM TeacherEntity", TeacherEntity.class)
                    .getResultList());
            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            log.error(MessagesConstants.CANNOT_GET_ALL_TEACHERS, e);
            throw new DaoException(MessagesConstants.CANNOT_GET_ALL_TEACHERS, e);
        }
        log.debug("{} found", teachers);
        return teachers;
    }

    public Teacher getById(Integer id) {
        log.trace("getById({})", id);
        TeacherEntity teacher;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            teacher = entityManager.find(TeacherEntity.class, id);
            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.close();
            log.debug("Teacher {} found", teacher);
            return teacherMapper.toTeacher(teacher);
        } catch (Exception e) {
            log.error(MessagesConstants.CANNOT_GET_TEACHER_BY_ID, e);
            throw new DaoException(MessagesConstants.CANNOT_GET_TEACHER_BY_ID, e);
        }
    }

    public Teacher update(Teacher teacher) {
        log.trace("update({})", teacher);
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(teacherMapper.toEntity(teacher));
            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            log.error(MessagesConstants.CANNOT_UPDATE_TEACHER_BY_ID, e);
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_TEACHER_BY_ID, e);
        }
        log.debug("Teacher with id {} updated", teacher.getId());
        return teacher;
    }

    public boolean delete(Integer id) {
        log.trace("delete({})", id);
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(TeacherEntity.class, id));
            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            log.error(MessagesConstants.CANNOT_DELETE_TEACHER, e);
            throw new DaoException(MessagesConstants.CANNOT_DELETE_TEACHER, e);
        }
        log.debug("Teacher with id {} deleted", id);
        return true;
    }

    public List<Teacher> getByCourse(Integer courseId) {
        log.trace("getByCourse({})", courseId);
        List<Teacher> teachers;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            teachers = teacherMapper.toTeacher(entityManager.find(CourseEntity.class, courseId).getTeachers());
            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            log.error(MessagesConstants.CANNOT_GET_TEACHERS_BY_COURSE, e);
            throw new DaoException(MessagesConstants.CANNOT_GET_TEACHERS_BY_COURSE, e);
        }
        log.debug("Teachers {} found", teachers);
        return teachers;
    }

    public Teacher getByLesson(Integer lessonId) {
        log.trace("getByLesson({})", lessonId);
        Teacher teacher;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            teacher = teacherMapper.toTeacher(entityManager.find(LessonEntity.class, lessonId).getTeacher());
            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            log.error(MessagesConstants.CANNOT_GET_TEACHERS_BY_LESSON, e);
            throw new DaoException(MessagesConstants.CANNOT_GET_TEACHERS_BY_LESSON, e);
        }
        log.debug("Teacher {} found", teacher);
        return teacher;
    }
}
