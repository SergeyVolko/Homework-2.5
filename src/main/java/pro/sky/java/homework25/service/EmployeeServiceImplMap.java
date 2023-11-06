package pro.sky.java.homework25.service;

import org.springframework.stereotype.Service;
import pro.sky.java.homework25.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.homework25.exceptions.EmployeeNotFoundException;
import pro.sky.java.homework25.exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceImplMap implements EmployeeService {
    private static final int MAX_EMPLOYEE = 5;
    private final Map<String, Employee> employeeMap = new HashMap<>();

    @Override
    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        String key = employee.getFullNameEmployee();
        if (employeeMap.size() >= MAX_EMPLOYEE) {
            String message = String.format("Места для сотрудника %s нет.", employee);
            throw new EmployeeStorageIsFullException(message);
        }
        if (employeeMap.containsKey(key)){
            String message = String.format("Сотрудник %s в компании уже есть", employee);
            throw new EmployeeAlreadyAddedException(message);
        }
        employeeMap.put(key, employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = find(firstName, lastName);
        return employeeMap.remove(employee.getFullNameEmployee());
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employeeMap.containsKey(employee.getFullNameEmployee())) {
            String message = String.format("Сотрудник %s не найден", employee);
            throw new EmployeeNotFoundException(message);
        }
        return employee;
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employeeMap.values());
    }
}
