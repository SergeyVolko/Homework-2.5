package pro.sky.java.homework25.service;

import org.springframework.stereotype.Service;
import pro.sky.java.homework25.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.homework25.exceptions.EmployeeNotFoundException;
import pro.sky.java.homework25.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService {
    private static final int MAX_EMPLOYEE = 5;
    private final List<Employee> employees = new ArrayList<>(MAX_EMPLOYEE);

    public Employee add(Employee employee) {
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

    public Employee remove(Employee employee) {
        int index = findByIndexEmployee(employee);
        employees.remove(index);
        return employee;
    }

    public Employee find(Employee employee) {
        int index = findByIndexEmployee(employee);
        if (index < 0) {
            String message = String.format("Сотрудник %s не найдем", employee);
            throw new EmployeeNotFoundException(message);
        }
        return employees.get(index);
    }

    private int findByIndexEmployee(Employee employee) {
        int index = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employee.equals(employees.get(i))) {
                index = i;
                break;
            }
        }
        return index;
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

}
