package com.backend.demospringboot.Service;

import com.backend.demospringboot.Entity.Department;
import com.backend.demospringboot.Error.DepartmemtNotFoundException;
import com.backend.demospringboot.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements  DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department postdepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getdepartmentList() throws DepartmemtNotFoundException {
        List<Department> dep = departmentRepository.findAll();
        if(!dep.isEmpty()){
            return dep;
        }
        throw new DepartmemtNotFoundException("Ooops!!!Departments Are Not Available...");

    }

    @Override
    public Department getdepartmentbyid(Long id) throws DepartmemtNotFoundException {
        Optional <Department> dep = departmentRepository.findById(id);
        if(!dep.isPresent()){
            throw new DepartmemtNotFoundException("Oops!!! Selected Department Not found...");
        }
        return dep.get();
    }

    @Override
    public Optional<Department> getdepartmentbyname(String name) throws DepartmemtNotFoundException {
        Optional<Department> dep = Optional.ofNullable(departmentRepository.findByNameIgnoreCase(name));
        if(!dep.isPresent()){
            throw new DepartmemtNotFoundException("Oops!!! Selected Name Of Department Is Not Available...");
        }
        return dep;
    }


    @Override
    public void deletedepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department updatedepartment(Long id, Department department) {
        Department dep = departmentRepository.findById(id).get();
        if(Objects.nonNull(department.getName())&&!"".equalsIgnoreCase(department.getName())){
            dep.setName(department.getName());
        }
        if(Objects.nonNull(department.getCodeno())&&!"".equalsIgnoreCase(department.getCodeno())){
            dep.setCodeno(department.getCodeno());
        }
        departmentRepository.save(dep);
      return dep;
    }
}
