package pro.sky.java.homework25.service;

import org.apache.commons.lang3.StringUtils;
import pro.sky.java.homework25.exceptions.EmployeeWrongName;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private int department;
    private int salary;

    public Employee(String firstName, String lastName, int department, int salary) {
        this(firstName, lastName);
        this.department = department;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName) {
        validateInput(firstName, lastName);
        this.firstName = StringUtils.capitalize(firstName.toLowerCase());
        this.lastName = StringUtils.capitalize(lastName.toLowerCase());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    private void validateInput(String firstName,String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new EmployeeWrongName(String.format("Wrong input: %s %s", firstName, lastName));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    public String getFullNameEmployee() {
        return String.format("%s %s", firstName, lastName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
