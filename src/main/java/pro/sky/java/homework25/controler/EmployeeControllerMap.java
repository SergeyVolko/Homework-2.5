package pro.sky.java.homework25.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.homework25.service.Employee;
import pro.sky.java.homework25.service.EmployeeServiceImpl;
import pro.sky.java.homework25.service.EmployeeServiceImplMap;

import java.util.Collection;

@RestController
@RequestMapping("/employeeMap")
public class EmployeeControllerMap {
    private final EmployeeServiceImplMap employeeServiceImplMap;

    public EmployeeControllerMap(EmployeeServiceImplMap employeeServiceImplMap) {
        this.employeeServiceImplMap = employeeServiceImplMap;
    }
    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName) {
        return employeeServiceImplMap.add(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName) {
        return employeeServiceImplMap.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName) {
        return employeeServiceImplMap.find(firstName, lastName);
    }

    @GetMapping
    public Collection<Employee> getAllEmployees() {
        return employeeServiceImplMap.getAllEmployees();
    }
}
