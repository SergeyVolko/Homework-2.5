package pro.sky.java.homework25.service;

import org.springframework.stereotype.Service;
import pro.sky.java.homework25.exceptions.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceDepImpl implements EmployeeServiceDep {
    private static final String MESSAGE_EXCEPTION = "Do not employees in department №%s";
    private final EmployeeServiceImpl service;

    public EmployeeServiceDepImpl(EmployeeServiceImpl service) {
        this.service = service;
    }

    @Override
    public Employee add(String firstName, String lastName, int department, int salary) {
        Employee employee = service.add(firstName, lastName);
        employee.setSalary(salary);
        employee.setDepartment(department);
        return employee;
    }

    @Override
    public Employee getEmployeeWithMaxSalaryInDepartment(int department) {
        return getStreamFilter(department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(
                        () -> new EmployeeNotFoundException(String.format(MESSAGE_EXCEPTION, department))
                );
    }

    @Override
    public Employee getEmployeeWithMinSalaryInDepartment(int department) {
        return getStreamFilter(department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(
                        () -> new EmployeeNotFoundException(String.format(MESSAGE_EXCEPTION, department))
                );
    }

    @Override
    public List<Employee> getAllEmployeesInDepartment(int department) {
        return getStreamFilter(department).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesInDep() {
        return service.getAllEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public int getSumSalaryInDepartment(int department) {
        return getStreamFilter(department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    private Stream<Employee> getStreamFilter(int department){
        return service.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == department);
    }
}
