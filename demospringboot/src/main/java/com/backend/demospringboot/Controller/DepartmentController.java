package com.backend.demospringboot.Controller;

import com.backend.demospringboot.Entity.Department;
import com.backend.demospringboot.Error.DepartmemtNotFoundException;
import com.backend.demospringboot.Service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    private final Logger log = LoggerFactory.getLogger(DepartmentController.class);
    @PostMapping("/dp")
    public Department postdepartment(@RequestBody Department department){
        log.info("======Inside Post Department=====");

        return departmentService.postdepartment(department);
    }
    @GetMapping("/dp")
    public List<Department> getdepartmentList() throws DepartmemtNotFoundException {
        log.info("======Inside Get Department List=====");
        return departmentService.getdepartmentList();
    }
    @GetMapping("/dp/{id}")
    public Department getdepartmentbyid(@PathVariable ("id") Long id) throws DepartmemtNotFoundException {
        log.info("======Inside Get Department by Id=====");
        return departmentService.getdepartmentbyid(id);
    }
    @GetMapping("/dp/name/{name}")
    public Optional<Department> getdepartmentbyname(@PathVariable ("name") String name) throws DepartmemtNotFoundException {
        log.info("======Inside Get Department by Name=====");
        return departmentService.getdepartmentbyname(name);
    }
    @DeleteMapping("/dp/{id}")

    public String deletedepartment(@PathVariable ("id")Long id){
        log.info("======Inside Delete Department======");
        departmentService.deletedepartment(id);
        return "Department Successfully Deleted";
    }
    @PutMapping ("/dp/{id}")
    public String updatedepartment (@PathVariable ("id") Long id , @RequestBody Department department){
        log.info("======Inside Delete Department======");
        departmentService.updatedepartment(id,department);
        return "Department Successfully Updated";

    }
    }
