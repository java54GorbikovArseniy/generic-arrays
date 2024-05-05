package telran.employees;

import telran.util.Arrays;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Company implements Iterable<Employee> {
    private Employee[] employees;

    //Method create just for test
    public Employee[] getEmployees() {
        return employees;
    }

    public void addEmployee(Employee empl) {
        if (getEmployee(empl.getId()) != null) {
            throw new IllegalStateException();
        }
        employees = Arrays.add(employees, empl);
    }

    public Employee getEmployee(long id) {
        Employee employee = new Employee(id, 0, "");
        int res;
        res = Arrays.indexOf(employees, employee);
        return res >= 0 ? employees[res] : null;
    }

    public Employee removeEmployee(long id) {
        Employee employee = getEmployee(id);
        if (employee == null) {
            throw new NoSuchElementException();
        }
        employees = Arrays.removeIf(employees, (e) -> e.getId() == id);
        return employee;
    }

    public int getDepartmentBudget(String department) {
        int budget = 0;
        for (Employee employee : employees) {
            if (employee.getDepartment().equals(department)) {
                budget += employee.getBasicSalary();
            }
        }
        return budget;
    }

    public Company(Employee[] employees) {
        this.employees = Arrays.copy(employees);
    }

    @Override
    public Iterator<Employee> iterator() {

        return new CompanyIterator();
    }

    private class CompanyIterator implements Iterator<Employee> {
        int length = employees.length;
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < length;
        }

        @Override
        public Employee next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return employees[index++];
        }
    }
}
