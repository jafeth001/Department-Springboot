package com.backend.demospringboot.Controller;

import com.backend.demospringboot.Entity.Department;
import com.backend.demospringboot.Error.DepartmemtNotFoundException;
import com.backend.demospringboot.Service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
  @Autowired
  private DepartmentService departmentService;
  private final Logger log = LoggerFactory.getLogger(DepartmentController.class);

  @PostMapping("save")
  public Department postdepartment(@RequestBody Department department) {
    log.info("======Inside Post Department=====");

    return departmentService.postdepartment(department);
  }

  @GetMapping("/all")
  public List<Department> getdepartmentList() throws DepartmemtNotFoundException {
    log.info("======Inside Get Department List=====");
    return departmentService.getdepartmentList();
  }

  @GetMapping("/{id}")
  public Department getdepartmentbyid(@PathVariable("id") Long id) throws DepartmemtNotFoundException {
    log.info("======Inside Get Department by Id=====");
    return departmentService.getdepartmentbyid(id);
  }

  @GetMapping("/name/{name}")
  public Optional<Department> getdepartmentbyname(@PathVariable("name") String name) throws DepartmemtNotFoundException {
    log.info("======Inside Get Department by Name=====");
    return departmentService.getdepartmentbyname(name);
  }

  @DeleteMapping("/delete/{id}")

  public String deletedepartment(@PathVariable("id") Long id) {
    log.info("======Inside Delete Department======");
    departmentService.deletedepartment(id);
    return "Department Successfully Deleted";
  }

  @PutMapping("/update/{id}")
  public Department updatedepartment(@PathVariable("id") Long id, @RequestBody Department department) {
    log.info("======Inside Delete Department======");
    return departmentService.updatedepartment(id, department);
  }
}
