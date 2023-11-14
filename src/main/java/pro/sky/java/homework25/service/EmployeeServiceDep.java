package pro.sky.java.homework25.service;

import java.util.List;
import java.util.Map;

public interface EmployeeServiceDep {
    Employee add(String firstName, String lastName, int department, int salary);
    Employee getEmployeeWithMaxSalaryInDepartment(int department);
    Employee getEmployeeWithMinSalaryInDepartment(int department);
    List<Employee> getAllEmployeesInDepartment(int department);
    Map<Integer, List<Employee>> getAllEmployeesInDep();
    int getSumSalaryInDepartment(int department);
}
