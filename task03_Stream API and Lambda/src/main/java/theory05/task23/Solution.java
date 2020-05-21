package theory05.task23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You have two classes:
 * Employee (name: String, salary: Long) and
 * Department (name: String, code: String, employees: List<Employee>).
 * Both classes have getters for all fields with the corresponding names (getName(), getSalary(), getEmployees() and so on).
 * <p>
 * Write a method using Stream API that calculates the general number of employees with salary >= threshold
 * for all departments whose code starts with string "111-" (without "").
 * Perhaps, flatMap method can help you to implement it.
 * <p>
 * Classes Employee and Department will be available during testing.
 * You can define your own classes for local testing.
 * <p>
 * Examples: there are 2 departments (in JSON notation) below and ﻿threshold = 20 000.
 * The result is 1 (because there is only one suitable employee).
 */
public class Solution {
    private static List<Department> departments = new ArrayList<>();

    static {
        Department one = new Department("dep-1", "111-1",
                Arrays.asList(new Employee("William", 20_000L), new Employee("Sophia", 1_000L)));
        Department two = new Department("dep-2", "222-1",
                Arrays.asList(new Employee("John", 50_000L), new Employee("Doe", 5_000L)));

        departments.add(one);
        departments.add(two);
    }

    /**
     * Calculates the number of employees with salary >= threshold (only for 111- departments)
     *
     * @param departments are list of departments
     * @param threshold   is lower edge of salary
     * @return the number of employees
     */
    public static long calcNumberOfEmployees(List<Department> departments, long threshold) {
        return departments.stream()
                .filter(dep -> dep.getCode().startsWith("111-"))
                .flatMap(dep -> dep.getEmployees().stream())
                .mapToLong(Employee::getSalary)
                .filter(sal -> sal >= threshold)
                .count();
    }

    public static void main(String[] args) {
        System.out.println(calcNumberOfEmployees(departments, 10_000L));
    }

}

class Employee {
    private String name;
    private Long salary;

    public Employee(String name, Long salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public Long getSalary() {
        return salary;
    }
}

class Department {
    private String name;
    private String code;
    private List<Employee> employees;

    public Department(String name, String code, List<Employee> employees) {
        this.name = name;
        this.code = code;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}