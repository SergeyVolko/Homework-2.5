package pro.sky.java.homework25.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.homework25.service.Employee;
import pro.sky.java.homework25.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName) {
        return employeeService.add(new Employee(firstName, lastName));
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName) {
        return employeeService.remove(new Employee(firstName, lastName));
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName) {
        return employeeService.find(new Employee(firstName, lastName));
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }
}
