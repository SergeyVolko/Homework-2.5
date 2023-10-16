package pro.sky.java.homework25.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.homework25.service.Employee;
import pro.sky.java.homework25.service.EmployeeServiceDep;

import java.util.stream.Collectors;
@RestController
@RequestMapping("/departments")
public class EmployeeControllerDep {
    private final EmployeeServiceDep serviceDep;
    @Autowired
    public EmployeeControllerDep(EmployeeServiceDep serviceDep) {
        this.serviceDep = serviceDep;
    }
    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("department") int department,
                        @RequestParam("salary") int salary) {
        return serviceDep.add(firstName, lastName, department, salary);
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalaryInDepartment(@RequestParam("department")
                                                         int department) {
        return serviceDep.getEmployeeWithMaxSalaryInDepartment(department);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalaryInDepartment(@RequestParam("department")
                                                         int department) {
        return serviceDep.getEmployeeWithMinSalaryInDepartment(department);
    }

    @GetMapping(path = "/all", params = "department")
    public String getAllEmployeesInDepartment(@RequestParam(value = "department")
                                              int department) {
        return serviceDep.getAllEmployeesInDepartment(department).stream()
                .map(Employee::toString)
                .collect(Collectors.joining("<br>"));
    }

    @GetMapping(path = "/all")
    public String getAllEmployeesInDepartments() {
        return serviceDep.getAllEmployeesInDep()
                .entrySet().stream()
                .map(e -> String.format("Департамент №%d:<br>%s", e.getKey(), e.getValue()
                        .stream().map(e1 -> String.format("%s<br>", e1.toString()))
                        .collect(Collectors.joining())))
                .collect(Collectors.joining());
    }
}
