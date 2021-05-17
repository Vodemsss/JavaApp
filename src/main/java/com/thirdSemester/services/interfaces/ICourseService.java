package com.thirdSemester.services.interfaces;

import com.thirdSemester.domain.PlainObjects.CoursePojo;
import com.thirdSemester.domain.Course;

import java.util.List;

public interface ICourseService {

    CoursePojo createCourse(Course course, Long userId);
    CoursePojo getCourse(Long id, Long userId);
    List<CoursePojo> getAllCourses(Long userId);
    CoursePojo updateCourse(Course course, Long CourseId, Long userId);
    String deleteCourse(Long id, Long userId);
}

