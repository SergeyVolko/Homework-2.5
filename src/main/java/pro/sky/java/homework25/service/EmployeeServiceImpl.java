package pro.sky.java.homework25.service;

import org.springframework.stereotype.Service;
import pro.sky.java.homework25.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.homework25.exceptions.EmployeeNotFoundException;
import pro.sky.java.homework25.exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int MAX_EMPLOYEE = 15;
    private final List<Employee> employees = new ArrayList<>(MAX_EMPLOYEE);
    @Override
    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() >= MAX_EMPLOYEE) {
            String message = String.format("Места для сотрудника %s нет.", employee);
            throw new EmployeeStorageIsFullException(message);
        }
        if (employees.contains(employee)) {
            String message = String.format("Сотрудник %s в компании уже есть", employee);
            throw new EmployeeAlreadyAddedException(message);
        }
        employees.add(employee);
        return employee;
    }
    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = find(firstName, lastName);
        employees.remove(employee);
        return employee;
    }
    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            String message = String.format("Сотрудник %s не найден", employee);
            throw new EmployeeNotFoundException(message);
        }
        return employee;
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees);
    }
}
