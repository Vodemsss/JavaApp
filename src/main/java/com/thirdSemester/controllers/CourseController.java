package com.thirdSemester.controllers;

import com.thirdSemester.domain.Course;
import com.thirdSemester.domain.PlainObjects.CoursePojo;
import com.thirdSemester.services.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CourseController {

    private final ICourseService сourseService;
    Long userId;

    @Autowired
    public CourseController(ICourseService сourseService) {
        this.сourseService = сourseService;
    }

    @PostMapping(path = "/user/{userId}/сourse")
    public ResponseEntity<CoursePojo> createCourse (HttpServletRequest request, @RequestBody Course course, @PathVariable Long userId) {
        CoursePojo result = сourseService.createCourse(course, userId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(path = "/user/{userId}/сourse/{id}")
    public ResponseEntity<CoursePojo> getCourse (HttpServletRequest request, @PathVariable Long id) {
        return new ResponseEntity<>(сourseService.getCourse(id, userId), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{userId}/сourses")
    public ResponseEntity<List<CoursePojo>> getAll (HttpServletRequest request) {
        return new ResponseEntity<>(сourseService.getAllCourses(userId), HttpStatus.OK);
    }


    @PutMapping(path = "/user/{userId}/сourse/{id}")
    public ResponseEntity<CoursePojo> updateCourse (HttpServletRequest request, @RequestBody Course source, @PathVariable Long id) {
        return new ResponseEntity<>(сourseService.updateCourse(source, id, userId), HttpStatus.OK);
    }


    @DeleteMapping(path = "/user/{userId}/сourse/{id}")
    public ResponseEntity<String> deleteCourse (HttpServletRequest request, @PathVariable Long id) {
        return new ResponseEntity<>(сourseService.deleteCourse(id, userId), HttpStatus.OK);
    }
}

