package accademy.ennate.service;

import accademy.ennate.entity.Employee;
import accademy.ennate.exception.EmployeeNotFoundException;
import accademy.ennate.repository.EmployeeRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {

    @TestConfiguration
     static class EmployeeServiceImplTestConfiguration {

        @Bean
        public EmployeeService getService(){
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    private List<Employee> employeeList;
    @Before
    public void setup(){
        Employee employee = new Employee();

        employee.setFirstName("Anusha");
        employee.setLastName("buul");
        employee.setEmail("buul@gmail.com");



        employeeList = Collections.singletonList(employee);

        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);

        Mockito.when(employeeRepository.findById(employee.getId())).thenReturn(java.util.Optional.ofNullable(employeeList.get(0)));
    }

    @After
    public void cleanup(){

    }

    @Test
    public void findAll() {
        List<Employee> result = employeeService.findAll();
        Assert.assertEquals("employee", employeeList, result);
    }

    @Test
    public void findOne() {
        Employee oneemployee = employeeService.findOne(employeeList.get(0).getId());
        Assert.assertEquals("employee", employeeList.get(0), oneemployee);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void findOneException() throws Exception {
        Employee oneemployee = employeeService.findOne("12");
        //Assert.assertEquals("employee", employeeList.get(0), oneemployee); //not needed
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}