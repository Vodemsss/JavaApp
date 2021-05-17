package com.thirdSemester.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
    @Table(name = "COURSE")
    public class Course {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO) //генерация ID
        @Column(name = "ID")
        private Long id;

        @Column(name = "NAME", nullable = false)
        private String name;

        @Column(name = "COMMENT")
        private String comment;

        @Column(name = "START_DATE")
        private Date startDate;

        @Column(name = "END_DATE")
        private Date endDate;

        @Column(name = "PRIVACY")
        private Boolean privacy;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        setUser(user, false);
    }

    public void setUser(User user, boolean otherSideHasBeenSet) {
        this.user = user;
        if(otherSideHasBeenSet) {
            return;
        }
        user.addCourse(this, true);
    }

    public void removeUser(User user) {
        removeUser(user, false);
    }

    public void removeUser(User user, boolean otherSideHasBeenSet) {
        this.user = null;
        if(otherSideHasBeenSet) {
            return;
        }
        user.removeCourse(this, true);
    }
@ManyToMany

@JoinTable(name = "COURSE_DEPARTMENT", joinColumns = @JoinColumn(name = "COURSE_ID"), inverseJoinColumns = @JoinColumn(name = "DEPARTMENT_ID"))
    private Set<Department> departmentList = new HashSet<>();

    public Set<Department> getDepartmentList() {
        return departmentList;
    }

    public void addDepartment(Department department) {
        addDepartment(department, false);
    }

    public void addDepartment(Department department, boolean otherSideHasBeenSet) {
        this.getDepartmentList().add(department);
        if(otherSideHasBeenSet) {
            return;
        }
        department.addCourse(this, true);
    }

    public void removeDepartment(Department department) {
        removeDepartment(department, false);
    }

    public void removeDepartment(Department department, boolean otherSideHasBeenSet) {
        this.getDepartmentList().remove(department);
        if(otherSideHasBeenSet) {
            return;
        }
        department.removeCourse(this, true);
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", privacy=" + privacy +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return id.equals(course.id) &&
                name.equals(course.name) &&
                Objects.equals(comment, course.comment) &&
                Objects.equals(startDate, course.startDate) &&
                Objects.equals(endDate, course.endDate) &&
                Objects.equals(privacy, course.privacy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, comment, startDate, endDate, privacy);
    }
    }
