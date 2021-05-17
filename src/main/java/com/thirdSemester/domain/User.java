package com.thirdSemester.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private  Long id;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Course> courseList = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
        course.setUser(this, true);
    }


    public void removeCourse(Course course) {
        removeCourse(course, false);
    }

    public void removeCourse(Course course, boolean otherSideHasBeenSet) {
        this.getCourseList().remove(course);
        if(otherSideHasBeenSet) {
            return;
        }
        course.removeUser(this, true);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                email.equals(user.email) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}
