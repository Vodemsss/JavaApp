package com.thirdSemester.services.interfaces;


import com.thirdSemester.domain.Department;

public interface IDepartmentService {

    Department findOrCreate(Department department);
}
