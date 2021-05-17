package com.thirdSemester.services;

import com.thirdSemester.domain.Department;
import com.thirdSemester.repositories.DepartmentRepository;
import com.thirdSemester.services.interfaces.IDepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class DepartmentService implements IDepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    @Override
    @Transactional
    public Department findOrCreate(Department department) {

        List<Department> foundDepartments = departmentRepository.findByName(department.getName());

        if(foundDepartments.isEmpty()) {
            departmentRepository.save(department);
            return department;
        } else {
            return foundDepartments.get(0);
        }
    }
}
