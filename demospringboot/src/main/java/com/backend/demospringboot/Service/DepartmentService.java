package com.backend.demospringboot.Service;

import com.backend.demospringboot.Entity.Department;
import com.backend.demospringboot.Error.DepartmemtNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
  public   Department postdepartment(Department department);

  public List<Department> getdepartmentList() throws DepartmemtNotFoundException;

   public Department getdepartmentbyid(Long id) throws DepartmemtNotFoundException;

   public Optional<Department> getdepartmentbyname(String name) throws DepartmemtNotFoundException;

   public  void deletedepartment(Long id);

   public Department updatedepartment(Long id, Department department);
}
