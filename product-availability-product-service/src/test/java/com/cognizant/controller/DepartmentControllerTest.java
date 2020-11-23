package com.cognizant.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.cognizant.model.Department;
import com.cognizant.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

class DepartmentControllerTest {

    DepartmentController departmentController;
    DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        departmentService = Mockito.mock(DepartmentService.class);
        departmentController = new DepartmentController(departmentService);
    }

    @Test
    public void getAllDepts() {
        List<Department> expected = Arrays.asList(
                new Department(1, "Shirts"),
                new Department(2, "Sweater")
        );

        Mockito.when(departmentService.getAllDepts()).thenReturn(expected);

        List<Department> actual = departmentController.getAllDepts();

        Mockito.verify(departmentService).getAllDepts();
        assertEquals(expected, actual);
    }

    @Test
    public void getDeptById() {
        Department expected = new Department(1, "Shirts");

        Mockito.when(departmentService.getDeptById(1)).thenReturn(expected);

        Department actual = departmentController.getDeptById(1);

        Mockito.verify(departmentService).getDeptById(1);
        assertEquals(expected, actual);
    }
}