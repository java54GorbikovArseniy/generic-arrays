package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.employees.*;

import java.util.NoSuchElementException;

class CompanyTest {
    private static final long ID1 = 123;
    private static final int SALARY1 = 1000;
    private static final String DEPARTMENT1 = "Development";
    private static final long ID2 = 120;
    private static final int SALARY2 = 2000;
    private static final long ID3 = 125;
    private static final int SALARY3 = 3000;
    private static final String DEPARTMENT2 = "QA";
    Employee empl1 = new Employee(ID1, SALARY1, DEPARTMENT1);
    Employee empl2 = new Employee(ID2, SALARY2, DEPARTMENT1);
    Employee empl3 = new Employee(ID3, SALARY3, DEPARTMENT2);
    Company company;

    @BeforeEach
    void setCompany() {
        company = new Company(new Employee[]{empl1, empl2, empl3});
    }

    @Test
    void testAddEmployee() {
        //TODO
        Company expected = new Company(new Employee[]{empl1, empl2, empl3, new Employee(130, 144, DEPARTMENT1)});
        company.addEmployee(new Employee(130, 144, DEPARTMENT1));
        assertArrayEquals(expected.getEmployees(), company.getEmployees());
        assertThrowsExactly(IllegalStateException.class, () -> company.addEmployee(empl1));
        assertThrowsExactly(IllegalStateException.class, () -> company.addEmployee(empl2));
    }

    @Test
    void testGetEmployee() {
        assertEquals(empl1, company.getEmployee(ID1));
        assertNull(company.getEmployee(453));
    }

    @Test
    void testRemoveEmployee() {
        Company expected = new Company(new Employee[]{empl1, empl2});
        company.removeEmployee(ID3);
        assertArrayEquals(expected.getEmployees(), company.getEmployees());
        assertThrowsExactly(NoSuchElementException.class, () -> company.removeEmployee(1234));
    }

    @Test
    void testGetDepartmentBudget() {
        assertEquals(SALARY1 + SALARY2, company.getDepartmentBudget(DEPARTMENT1));
        assertEquals(SALARY3, company.getDepartmentBudget(DEPARTMENT2));
    }

    @Test
    void testIterator() {
        Employee[] employees = new Employee[company.getEmployees().length];
        int index = 0;
        for (Employee employee : company) {
            employees[index] = employee;
            index++;
        }
        assertArrayEquals(company.getEmployees(), employees);
    }

}
