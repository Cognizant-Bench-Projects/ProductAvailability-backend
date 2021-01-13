package com.cognizant.controller;

import com.cognizant.model.Department;
import com.cognizant.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentService deptService;

    public DepartmentController(DepartmentService deptService) {
        this.deptService = deptService;
    }

    @GetMapping
    public List<Department> getAllDepts() {
        return this.deptService.getAllDepts();
    }

    @GetMapping("/{deptId}")
    public Department getDeptById(@PathVariable int deptId) {
        return this.deptService.getDeptById(deptId);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException() {
        return ResponseEntity.status(500).body("Server Error");
    }
}
