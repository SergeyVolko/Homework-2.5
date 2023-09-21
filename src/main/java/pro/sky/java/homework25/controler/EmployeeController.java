package pro.sky.java.homework25.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.homework25.service.Employee;
import pro.sky.java.homework25.service.EmployeeServiceImpl;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }
    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName) {
        return employeeServiceImpl.add(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName) {
        return employeeServiceImpl.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName) {
        return employeeServiceImpl.find(firstName, lastName);
    }

    @GetMapping
    public Collection<Employee> getAllEmployees() {
        return employeeServiceImpl.getAllEmployees();
    }
}
