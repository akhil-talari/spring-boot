package accademy.ennate.repository;

import accademy.ennate.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

    Optional<Employee> findByEmail(String email);

    @Query("") // to define custom
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);


}
