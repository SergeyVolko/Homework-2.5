package pro.sky.java.homework25.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.homework25.service.Employee;
import pro.sky.java.homework25.service.EmployeeServiceDep;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final EmployeeServiceDep serviceDep;

    @Autowired
    public DepartmentController(EmployeeServiceDep serviceDep) {
        this.serviceDep = serviceDep;
    }

    @GetMapping("{id}/employees")
    public List<Employee> getAllEmployeesInDepartment(@PathVariable int id) {
        return serviceDep.getAllEmployeesInDepartment(id);
    }

    @GetMapping("{id}/salary/sum")
    public int getSumSalaryInDepartment(@PathVariable int id) {
        return serviceDep.getSumSalaryInDepartment(id);
    }

    @GetMapping("{id}/salary/max")
    public int getMaxSalaryInDepartment(@PathVariable int id) {
        return serviceDep.getEmployeeWithMaxSalaryInDepartment(id).getSalary();
    }

    @GetMapping("{id}/salary/min")
    public int getMinSalaryInDepartment(@PathVariable int id) {
        return serviceDep.getEmployeeWithMinSalaryInDepartment(id).getSalary();
    }

    @GetMapping("/employees")
    Map<Integer,List<Employee>> getAllEmployeesInDep() {
        return serviceDep.getAllEmployeesInDep();
    }
}
