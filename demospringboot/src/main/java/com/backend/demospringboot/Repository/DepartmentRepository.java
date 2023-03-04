package com.backend.demospringboot.Repository;

import com.backend.demospringboot.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
  public Department findByName(String name);

  public Department findByNameIgnoreCase(String name);

}
