package accademy.ennate.controller;

import accademy.ennate.entity.Employee;
import accademy.ennate.service.EmployeeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "employees")
@Api(value = "Employee controller")
public class EmployeeController {

    @Qualifier("employeeServiceImpl")
    @Autowired
    private EmployeeService service;
    @ApiOperation(value = "Finds all employees",
            notes = "...find list of all employees in the database...")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "OK"),
            @ApiResponse(code = 404, message = "internal bad server") })
    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> findAll(){
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Employee findOne(
    @ApiParam(value = "ID of the employee", required = true)

    @PathVariable("id") String empId)
    {
        return service.findOne(empId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Employee create(@RequestBody Employee employee){
        return service.create(employee);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Employee update(@PathVariable("id") String empId,@RequestBody Employee employee){
        return service.update(empId, employee);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable("id") String empId){
        service.delete(empId);
    }

}
