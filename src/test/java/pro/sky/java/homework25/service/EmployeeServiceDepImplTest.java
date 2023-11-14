package pro.sky.java.homework25.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.homework25.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.homework25.exceptions.EmployeeNotFoundException;
import pro.sky.java.homework25.exceptions.EmployeeStorageIsFullException;
import pro.sky.java.homework25.exceptions.EmployeeWrongName;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static pro.sky.java.homework25.constants.EmployeeServiceConstant.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EmployeeServiceDepImplTest {

    @Mock
    EmployeeServiceImpl service;

    @InjectMocks
    EmployeeServiceDepImpl serviceDep;

    @Test
    public void whenAddEmployeeThenEqualsSalaryAndDepartment() {
        when(service.add(any(), any())).thenReturn(EMPLOYEE1);
        Employee actual = serviceDep.add(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY_100);
        assertEquals(actual.getSalary(), SALARY_100);
        assertEquals(actual.getDepartment(), DEPARTMENT1);
    }

    @Test
    public void whenMaxSalaryInDepartmentThenSalary() {
        when(service.getAllEmployees()).thenReturn(EMPLOYEES_DEP);
        assertEquals(EMPLOYEE_DEP3, serviceDep.getEmployeeWithMaxSalaryInDepartment(DEPARTMENT1));
    }

    @Test
    public void whenMinSalaryInDepartmentThenSalary() {
        when(service.getAllEmployees()).thenReturn(EMPLOYEES_DEP);
        assertEquals(EMPLOYEE_DEP1, serviceDep.getEmployeeWithMinSalaryInDepartment(DEPARTMENT1));
    }

    @Test
    public void whenSumInDepartment() {
        when(service.getAllEmployees()).thenReturn(EMPLOYEES_DEP);
        int expect = SALARY_100 + SALARY_200 + SALARY_300;
        assertEquals(expect, serviceDep.getSumSalaryInDepartment(DEPARTMENT1));
    }

    @Test
    public void whenGetAllEmployeesInDepartment() {
        when(service.getAllEmployees()).thenReturn(EMPLOYEES_DEP);
        assertArrayEquals(EMPLOYEES_IN_DEPARTMENT1,
                serviceDep.getAllEmployeesInDepartment(DEPARTMENT1).toArray());
    }

    @Test
    public void whenGetMapThen() {
        when(service.getAllEmployees()).thenReturn(EMPLOYEES_DEP);
        Assertions.assertThat(serviceDep.getAllEmployeesInDep())
                .containsAllEntriesOf(DEPARTMENT_EMPLOYEE_MAP);
    }

    @Test
    public void whenAddThenAlreadyException() {
        when(service.add(FIRST_NAME1, LAST_NAME1)).thenThrow(EmployeeAlreadyAddedException.class);
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> serviceDep.add(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY_100));
    }

    @Test
    public void whenAddThenNotFoundException() {
        when(service.add(FIRST_NAME1, LAST_NAME1)).thenThrow(EmployeeNotFoundException.class);
        assertThrows(EmployeeNotFoundException.class,
                () -> serviceDep.add(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY_100));
    }

    @Test
    public void whenAddThenStorageFullException() {
        when(service.add(FIRST_NAME1, LAST_NAME1)).thenThrow(EmployeeStorageIsFullException.class);
        assertThrows(EmployeeStorageIsFullException.class,
                () -> serviceDep.add(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY_100));
    }

    @Test
    public void whenAddThenWrongNameException() {
        when(service.add(FIRST_NAME1, LAST_NAME1)).thenThrow(EmployeeWrongName.class);
        assertThrows(EmployeeWrongName.class,
                () -> serviceDep.add(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY_100));
    }

    @Test
    public void whenMaxSalaryIfNotDepartment() {
        when(service.getAllEmployees()).thenReturn(EMPLOYEES_DEP);
        assertThrows(EmployeeNotFoundException.class,
                () -> serviceDep.getEmployeeWithMaxSalaryInDepartment(DEPARTMENT3));
    }

    @Test
    public void whenMinSalaryIfNotDepartment() {
        when(service.getAllEmployees()).thenReturn(EMPLOYEES_DEP);
        assertThrows(EmployeeNotFoundException.class,
                () -> serviceDep.getEmployeeWithMinSalaryInDepartment(DEPARTMENT3));
    }

    @Test
    public void whenMaxSalaryIfNotDepartmentInEmptyEmployeesList() {
        when(service.getAllEmployees()).thenReturn(EMPLOYEES_EMPTY);
        assertThrows(EmployeeNotFoundException.class,
                () -> serviceDep.getEmployeeWithMaxSalaryInDepartment(DEPARTMENT1));
    }

    @Test
    public void whenMinSalaryIfNotDepartmentInEmptyEmployeesList() {
        when(service.getAllEmployees()).thenReturn(EMPLOYEES_EMPTY);
        assertThrows(EmployeeNotFoundException.class,
                () -> serviceDep.getEmployeeWithMinSalaryInDepartment(DEPARTMENT1));
    }
}