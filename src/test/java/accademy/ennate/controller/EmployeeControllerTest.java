package accademy.ennate.controller;

import accademy.ennate.entity.Employee;
import accademy.ennate.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("integration")
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Before
    public void setup(){
        Employee emp  = new Employee();
        emp.setId("bul");
        emp.setFirstName("Anusha");
        emp.setLastName("Reddy");
        emp.setEmail("bula@gmail.com");

        employeeRepository.save(emp);
    }

    @After
    public void cleanup(){
        employeeRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void findOne() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/bul"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("bula@gmail.com")));
    }

    @Test
    public void findOneException() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/b"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void create() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Employee emp  = new Employee();
        emp.setFirstName("Anusha");
        emp.setLastName("Reddy");
        emp.setEmail("bulaii@gmail.com");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(emp))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("bulaii@gmail.com")));
    }

    @Test
    public void create400() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Employee emp  = new Employee();
        emp.setFirstName("Anusha");
        emp.setLastName("Reddy");
        emp.setEmail("bula@gmail.com");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(emp))
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}