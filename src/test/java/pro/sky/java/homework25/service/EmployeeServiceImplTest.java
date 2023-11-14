package pro.sky.java.homework25.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.homework25.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.homework25.exceptions.EmployeeNotFoundException;
import pro.sky.java.homework25.exceptions.EmployeeStorageIsFullException;
import pro.sky.java.homework25.exceptions.EmployeeWrongName;

import static pro.sky.java.homework25.constants.EmployeeServiceConstant.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeService service;

    @BeforeEach
    public void setService() {
        service = new EmployeeServiceImpl();
    }

    public static Stream<Arguments> getStreamArgumentsForAddMethod() {
        return Stream.of(
                Arguments.of(FIRST_NAME1, LAST_NAME1, EMPLOYEE1),
                Arguments.of(FIRST_NAME2, LAST_NAME2, EMPLOYEE2),
                Arguments.of(FIRST_NAME3, LAST_NAME3, EMPLOYEE3),
                Arguments.of(FIRST_NAME4, LAST_NAME4, EMPLOYEE4)
        );
    }

    @ParameterizedTest
    @MethodSource("getStreamArgumentsForAddMethod")
    public void whenAddEmployee(String firstName, String lastName, Employee employee) {
        Employee actual = service.add(firstName, lastName);
        assertEquals(employee, actual);
    }

    @ParameterizedTest
    @MethodSource("getStreamArgumentsForAddMethod")
    public void whenRemoveEmployee(String firstName, String lastName, Employee employee) {
        addEmployeesInService(service);
        Employee actual = service.remove(firstName, lastName);
        assertEquals(employee, actual);
    }

    @ParameterizedTest
    @MethodSource("getStreamArgumentsForAddMethod")
    public void whenFindEmployee(String firstName, String lastName, Employee employee) {
        addEmployeesInService(service);
        Employee actual = service.remove(firstName, lastName);
        assertEquals(employee, actual);
    }

    @Test
    public void whenAddFirstEmployeeThenEmployee() {
        Employee expect =
                service.add(FIRST_NAME1, LAST_NAME1);
        assertEquals(expect, EMPLOYEE1);
    }

    @Test
    public void whenAddEmployeeThenAlreadyException() {
        addEmployeesInService(service);
        assertThrows(EmployeeAlreadyAddedException.class, () -> service.add(FIRST_NAME1, LAST_NAME1));
    }

    @Test
    public void whenAddWrongName() {
        assertThrows(EmployeeWrongName.class, () -> service.add(WRONG_NAME, LAST_NAME1));
    }

    @Test
    public void whenAdd15EmployeeThenException() {
        add14EmployeeInService(service);
        assertThrows(EmployeeStorageIsFullException.class,
                () -> service.add(FIRST_NAME3 + FIRST_NAME4, LAST_NAME3 + LAST_NAME4));
    }
    @Test
    public void whenAddFourNameThenList() {
        addEmployeesInService(service);
        Assertions.assertArrayEquals(EMPLOYEES_AFTER_ADD,
                service.getAllEmployees().toArray());
    }

    @Test
    public void whenRemoveFirstElementThenList() {
        addEmployeesInService(service);
        service.remove(FIRST_NAME1, LAST_NAME1);
        assertArrayEquals(EMPLOYEES_AFTER_REMOVE_EMPLOYEE_WITH_NAME1_LAST_NAME1,
                service.getAllEmployees().toArray());
    }

    @Test
    public void whenFindEmployeeThenException() {
        addEmployeesInService(service);
        assertThrows(EmployeeNotFoundException.class,
                () -> service.find(FIRST_NAME_NOT_FOUND, LAST_NAME_NOT_FOUND));
    }

    @Test
    public void whenRemoveEmployeeThenException() {
        addEmployeesInService(service);
        assertThrows(EmployeeNotFoundException.class,
                () -> service.remove(FIRST_NAME_NOT_FOUND, LAST_NAME_NOT_FOUND));
    }

    private void addEmployeesInService(EmployeeService service) {
        service.add(FIRST_NAME1, LAST_NAME1);
        service.add(FIRST_NAME2, LAST_NAME2);
        service.add(FIRST_NAME3, LAST_NAME3);
        service.add(FIRST_NAME4, LAST_NAME4);
    }

    private void add14EmployeeInService(EmployeeService service) {
        addEmployeesInService(service);
        service.add(FIRST_NAME1 + FIRST_NAME1, LAST_NAME1 + LAST_NAME1);
        service.add(FIRST_NAME1 + FIRST_NAME2, LAST_NAME1 + LAST_NAME2);
        service.add(FIRST_NAME1 + FIRST_NAME3, LAST_NAME1 + LAST_NAME3);
        service.add(FIRST_NAME1 + FIRST_NAME4, LAST_NAME1 + LAST_NAME4);
        service.add(FIRST_NAME2 + FIRST_NAME1, LAST_NAME2 + LAST_NAME1);
        service.add(FIRST_NAME2 + FIRST_NAME2, LAST_NAME2 + LAST_NAME2);
        service.add(FIRST_NAME2 + FIRST_NAME3, LAST_NAME2 + LAST_NAME3);
        service.add(FIRST_NAME2 + FIRST_NAME4, LAST_NAME2 + LAST_NAME4);
        service.add(FIRST_NAME3 + FIRST_NAME1, LAST_NAME3 + LAST_NAME1);
        service.add(FIRST_NAME3 + FIRST_NAME2, LAST_NAME3 + LAST_NAME2);
        service.add(FIRST_NAME3 + FIRST_NAME3, LAST_NAME3 + LAST_NAME3);
    }

}