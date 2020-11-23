package com.cognizant.service;

import com.cognizant.model.Department;
import com.cognizant.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentServiceTest {

    DepartmentService deptService;
    DepartmentRepository deptRepository;

    @BeforeEach
    public void setup() {
        deptRepository = Mockito.mock(DepartmentRepository.class);
        deptService = new DepartmentService(deptRepository);
    }

    @Test
    void getAllDepts() {
        List<Department> expected = Arrays.asList(
                new Department(1, "Shirts"),
                new Department(2, "Sweater")
        );

        Mockito.when(deptRepository.findAll()).thenReturn(expected);

        List<Department> actual = deptService.getAllDepts();

        Mockito.verify(deptRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void getDeptById() {
        Department expected = new Department(1, "Shirts");

        Mockito.when(deptRepository.getOne(1)).thenReturn(expected);

        Department actual = deptService.getDeptById(1);

        Mockito.verify(deptRepository).getOne(1);
        assertEquals(expected, actual);
    }
}