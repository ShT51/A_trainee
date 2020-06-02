package model;

import java.util.Objects;

public class Employee {
    private int id;
    private String fName;
    private String lName;
    private String email;
    private String department;
    private long salary;

    public Employee(int id, String fName, String lName, String email, String department, long salary) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }

    public Employee(String fName, String lName, String email, String department, long salary) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                salary == employee.salary &&
                fName.equals(employee.fName) &&
                lName.equals(employee.lName) &&
                email.equals(employee.email) &&
                department.equals(employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fName, lName, email, department, salary);
    }
}
