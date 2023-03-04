package com.backend.demospringboot.Repository;

import static org.junit.jupiter.api.Assertions.*;
import com.backend.demospringboot.Entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class DepartmentRepositoryTest {
  @Autowired
  private DepartmentRepository departmentRepository;
  @Autowired
  private TestEntityManager testEntityManager;
  private Department department;

  @BeforeEach
  void setUp() {
    department = Department.builder()
            .name("IT")
            .codeno("IT-01")
            .build();
    testEntityManager.persist(department);
  }
@Test
@DisplayName("Junit test for save method")
void whenDepartmentSaved_thenReturnDepartment() {
  Department newDepartment = Department.builder()
          .id(2L)
          .name("CS")
          .codeno("CS-01")
          .build();
  Department savedDepartment = departmentRepository.save(newDepartment);
  assertEquals(2, departmentRepository.count());
}
  @Test
  @DisplayName("Junit test for findById method")
  void whenNameIsPresent_thenReturnDepartment() {
    Long departmentId = 1L;
    Department found = departmentRepository.findById(department.getId()).get();
    assertEquals(departmentId, found.getId());
  }

  @Test
  @DisplayName("Junit test for findByName method")
  void whenNameIsNotPresent_thenReturnDepartment() {
    String name = "IT";
    Department found = departmentRepository.findByName(name);
    assertEquals(department.getName(), found.getName());
  }

  @Test
  @DisplayName("Junit test for delete method")
  void whenIdIsPresent_thenDeleteDepartment() {
    Long id = 1L;
    departmentRepository.deleteById(id);
    assertEquals(0, departmentRepository.count());
  }
}