package com.thirdSemester.domain.PlainObjects;

import java.util.HashSet;
import java.util.Set;

public class DepartmentPojo {

    private Long id;
    private String name;
    private Set<Long> courseListIds = new HashSet<>();

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

    public Set<Long> getCourseListIds() {
        return courseListIds;
    }

    public void setCourseListIds(Set<Long> courseListIds) {
        this.courseListIds = courseListIds;
    }
}
