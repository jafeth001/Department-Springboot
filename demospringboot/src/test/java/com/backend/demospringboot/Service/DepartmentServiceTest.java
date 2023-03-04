package com.backend.demospringboot.Service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.backend.demospringboot.Entity.Department;
import com.backend.demospringboot.Error.DepartmemtNotFoundException;
import com.backend.demospringboot.Repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class DepartmentServiceTest {
  @Autowired
  private DepartmentService departmentService;
  @MockBean
  private DepartmentRepository departmentRepository;
  private Department department;

  @BeforeEach
  void setUp() {
    department = Department.builder()
            .id(1L)
            .name("IT")
            .codeno("IT-01")
            .build();
  }

  @Test
  @DisplayName("junit taste for saving department")
  void whenDepartmentSaved_thenReturnDepartment() {
    Mockito.when(departmentRepository.save(department))
            .thenReturn(department);
    Department savedDepartment = departmentService.postdepartment(department);
    assert (savedDepartment != null);
  }

  @Test
  @DisplayName("junit taste for get department list")
  void whenDepartmentsfound_thenreturnDepartments() throws DepartmemtNotFoundException {
    Department newDepartment = Department.builder()
            .id(1L)
            .name("IT")
            .codeno("IT-01")
            .build();
    Mockito.when(departmentRepository.findAll())
            .thenReturn(List.of(department, newDepartment));
    List<Department> found = departmentService.getdepartmentList();
    assertEquals(2, found.size());
  }

  @Test
  @DisplayName("Junit test for get department by name")
  void whenDepartmentNamefound_thenreturnDepartment() throws DepartmemtNotFoundException {
    String name = "IT";
    Mockito.when(departmentRepository.findByNameIgnoreCase(department.getName()))
            .thenReturn(department);
    Optional<Department> found = departmentService.getdepartmentbyname(name);
    assertEquals(department.getName(), found.get().getName());
  }

  @Test
  @DisplayName("Junit test for update department")
  void whenDepartmentFound_thenDeleteDepartment() {
    Long id = 1L;
    Mockito.doNothing().when(departmentRepository).deleteById(department.getId());
    assertAll(() -> departmentService.deletedepartment(id));
  }

  @Test
  @DisplayName("Junit test for update department")
  void whenDepartmentSaved_thenUpdateDepartment() {
    Long id = 1L;
    department.setId(id);
    Mockito.when(departmentRepository.findById(1L))
            .thenReturn(Optional.ofNullable(department));
    Department updatedDepartment = departmentService.updatedepartment(1L, department);
    assertEquals(id, updatedDepartment.getId());

  }
}