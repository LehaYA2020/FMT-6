package ru.fmt.university.dao;

import org.springframework.stereotype.Repository;
import ru.fmt.university.dto.Course;

import java.util.List;

@Repository
public interface ICourseRepository extends IRepository<Course, Integer>{
    List<Course> getByGroupId(Integer groupId);
}
