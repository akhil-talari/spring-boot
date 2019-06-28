package accademy.ennate.service;

import accademy.ennate.entity.Employee;
import accademy.ennate.exception.EmployeeNotFoundException;
import accademy.ennate.exception.ResourceNotFoundException;
import accademy.ennate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee findOne(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        return employee.orElseThrow(
                () -> new EmployeeNotFoundException("Employee with id ="+ id +" NOT FOUND IN DATABASE")
        );

//        if(!employee.isPresent()){
//            throw new EmployeeNotFoundException("Employee with id ="+ id +" NOT FOUND IN DATABASE");
//        }else
//            return employee.get();
    }

    @Transactional
    @Override
    public Employee create(Employee emp) {
        Optional<Employee> existing = employeeRepository.findByEmail(emp.getEmail());
        if(existing.isPresent()){
            throw new ResourceNotFoundException("Employee with eamil ="+ emp.getEmail() +" already exists");
        }
        return employeeRepository.save(emp);
    }

    @Transactional
    @Override
    public Employee update(String id, Employee emp) {
        Optional<Employee>  employee = employeeRepository.findById(id);
        if(employee == null){
            throw new EmployeeNotFoundException("Employee with id ="+ id +" doesn't exist");
        }else
            return employeeRepository.save(emp);
    }

    @Transactional
    @Override
    public void delete(String id) {
        Optional<Employee>  employee = employeeRepository.findById(id);
        if(!employee.isPresent()){
            throw new EmployeeNotFoundException("Employee with id ="+ id +" doesn't exist");
        }else
            employeeRepository.delete(employee.get());

    }
}
