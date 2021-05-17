package com.thirdSemester.utils;

import com.thirdSemester.domain.Course;
import com.thirdSemester.domain.Department;
import com.thirdSemester.domain.PlainObjects.CoursePojo;
import com.thirdSemester.domain.PlainObjects.DepartmentPojo;
import com.thirdSemester.domain.PlainObjects.UserPojo;
import com.thirdSemester.domain.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Converter {

    public UserPojo userToPojo(User source) {
        UserPojo result = new UserPojo();

        result.setId(source.getId());
        result.setEmail(source.getEmail());
        result.setPassword(source.getPassword());

        result.setCourseList(source.getCourseList().stream().map(course -> courseToPojo(course)).collect(Collectors.toSet()));

        return result;
    }

    public CoursePojo courseToPojo(Course source) {

        CoursePojo result = new CoursePojo();

        result.setId(source.getId());
        result.setName(source.getName());
        result.setComment(source.getComment());
        result.setStartDate(source.getStartDate());
        result.setEndDate(source.getEndDate());
        result.setPrivacy(source.getPrivacy());
//        result.setPriority(source.getPriority());
        result.setUserId(source.getUser().getId());

//        result.setDepartmentList(source.getDepartmentList().stream().map(Department -> DepartmentToPojo(Department)).collect(Collectors.toSet()));

        return result;
    }

    public DepartmentPojo departmentToPojo (Department source) {
        DepartmentPojo result = new DepartmentPojo();

        result.setId(source.getId());
        result.setName(source.getName());
        result.setCourseListIds(source.getCourseList().stream().map(course -> course.getId()).collect(Collectors.toSet()));

        return result;
    }

}
