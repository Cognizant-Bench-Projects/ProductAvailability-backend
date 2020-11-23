package com.cognizant.service;

import com.cognizant.model.Department;
import com.cognizant.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository deptRepository;

    public DepartmentService(DepartmentRepository deptRepository) {
        this.deptRepository = deptRepository;
    }

    public List<Department> getAllDepts() {
        return this.deptRepository.findAll();
    }

    public Department getDeptById(int id) {
        return this.deptRepository.getOne(id);
    }
}
