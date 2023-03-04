package com.backend.demospringboot.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.backend.demospringboot.Entity.Department;
import com.backend.demospringboot.Service.DepartmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = DepartmentController.class)
class DepartmentControllerTest {
  @MockBean
  private DepartmentService departmentService;
  @Autowired
  private MockMvc mvc;
  @Autowired
  private ObjectMapper objectMapper;
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
  void whenDepartmentSaved_thenReturnDepartment() throws Exception {
    Mockito.when(departmentService.postdepartment(department))
            .thenReturn(department);
    mvc.perform(post("/department/save")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(department)))
            .andExpect(status().isOk())
            .andDo(print());

  }

  @Test
  void whenDepartmentsfound_thenreturnDepartments() throws Exception {
    Department newDepart
            = Department.builder()
            .id(1L)
            .name("IT")
            .codeno("IT-01")
            .build();
    Mockito.when(departmentService.getdepartmentList())
            .thenReturn(List.of(department, newDepart));
    mvc.perform(get("/department/all")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());

  }

  @Test
  @DisplayName("Junit test for findbyName method")
  void whenNameIsPresent_thenReturnDepartment() throws Exception {
    String name = "IT";
    Mockito.when(departmentService.getdepartmentbyname(name))
            .thenReturn(Optional.ofNullable(department));
    mvc.perform(get("/department/name/{name}", name)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(department.getName()))
            .andDo(print());
  }

  Department newDepart
          = Department.builder()
          .id(1L)
          .name("IT")
          .codeno("IT-01")
          .build();

  @Test
  @DisplayName("Junit test for delete department")
  void whenDepartmentFound_thenDeleteDepartment() throws Exception {
    Long id = 1L;
    Mockito.doNothing().when(departmentService).deletedepartment(id);
    mvc.perform(delete("/department/delete/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(department)))
            .andExpect(status().isOk())
            .andDo(print());

  }

  @Test
  @DisplayName("Junit test for update department")
  void whenDepartmentFound_thenUpdateDepartment() throws Exception {
    Long id = 1L;
    department.setId(2L);
    Mockito.when(departmentService.updatedepartment(id,department))
            .thenReturn(department);
    mvc.perform(put("/department/update/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(department)))
            .andExpect(status().isOk())
            .andDo(print());
  }
}