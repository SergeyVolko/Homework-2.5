package pro.sky.java.homework25.constants;

import pro.sky.java.homework25.service.Employee;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class EmployeeServiceConstant {
    public static final String FIRST_NAME1 = "Namea";
    public static final String FIRST_NAME2 = "Nameb";
    public static final String FIRST_NAME3 = "Namec";
    public static final String FIRST_NAME4 = "Named";

    public static final String LAST_NAME1 = "LastNamea";
    public static final String LAST_NAME2= "LastNameb";
    public static final String LAST_NAME3 = "LastNamc";
    public static final String LAST_NAME4 = "LastNamed";
    public static final String WRONG_NAME = "12345";

    public static final Employee EMPLOYEE1 = new Employee(FIRST_NAME1, LAST_NAME1);
    public static final Employee EMPLOYEE2 = new Employee(FIRST_NAME2, LAST_NAME2);
    public static final Employee EMPLOYEE3 = new Employee(FIRST_NAME3, LAST_NAME3);
    public static final Employee EMPLOYEE4 = new Employee(FIRST_NAME4, LAST_NAME4);

    public static final String FIRST_NAME_NOT_FOUND = "NameNotFound";
    public static final String LAST_NAME_NOT_FOUND = "LastNameNotFound";
    public static final int SALARY_100 = 100;
    public static final int SALARY_200 = 200;
    public static final int SALARY_300 = 300;
    public static final int SALARY_400 = 400;
    public static final int DEPARTMENT1 = 1;
    public static final int DEPARTMENT2 = 2;
    public static final int DEPARTMENT3 = 3;
    public static final Employee EMPLOYEE_DEP1 = new Employee(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY_100);
    public static final Employee EMPLOYEE_DEP2 = new Employee(FIRST_NAME2, LAST_NAME2, DEPARTMENT1, SALARY_200);
    public static final Employee EMPLOYEE_DEP3 = new Employee(FIRST_NAME3, LAST_NAME3, DEPARTMENT1, SALARY_300);
    public static final Employee EMPLOYEE_DEP4 = new Employee(FIRST_NAME4, LAST_NAME4, DEPARTMENT2, SALARY_400);
    public static final Employee[] EMPLOYEES_AFTER_ADD =  new Employee[] {
            EMPLOYEE1,
            EMPLOYEE2,
            EMPLOYEE3,
            EMPLOYEE4
    };

    public static final Employee[] EMPLOYEES_AFTER_REMOVE_EMPLOYEE_WITH_NAME1_LAST_NAME1 = new Employee[] {
            EMPLOYEE2,
            EMPLOYEE3,
            EMPLOYEE4
    };

    public static final Employee[] EMPLOYEES_IN_DEPARTMENT1 = new Employee[] {
            EMPLOYEE_DEP1,
            EMPLOYEE_DEP2,
            EMPLOYEE_DEP3
    };

    public static final Collection<Employee> EMPLOYEES_DEP = List.of(
            EMPLOYEE_DEP1,
            EMPLOYEE_DEP2,
            EMPLOYEE_DEP3,
            EMPLOYEE_DEP4
    );

    public static final Collection<Employee> EMPLOYEES_EMPTY = List.of();

    public static final Map<Integer, List<Employee>> DEPARTMENT_EMPLOYEE_MAP = Map.of(
            DEPARTMENT1, List.of(EMPLOYEE_DEP1, EMPLOYEE_DEP2, EMPLOYEE_DEP3),
            DEPARTMENT2, List.of(EMPLOYEE_DEP4)
    );
}
