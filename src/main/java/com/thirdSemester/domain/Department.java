package com.thirdSemester.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENT")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "departmentList")
    private Set<Course> courseList = new HashSet<>();

    public Set<Course> getCourseList() {
        return courseList;
    }

    public void addCourse(Course course) {
        addCourse(course, false);
    }

    public void addCourse(Course course, boolean otherSideHasBeenSet) {
        this.getCourseList().add(course);
        if(otherSideHasBeenSet) {
            return;
        }
        course.addDepartment(this, true);
    }

    public void removeCourse(Course course) {
        removeCourse(course, false);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourseList(Set<Course> courseList) {
        this.courseList = courseList;
    }

    public void removeCourse(Course course, boolean otherSideHasBeenSet) {
        this.getCourseList().remove(course);
        if(otherSideHasBeenSet) {
            return;
        }
        course.removeDepartment(this, true);
    }
}
