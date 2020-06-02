package service;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployee();
    boolean addEmployee(Employee emp);
    boolean deleteEmployeeById(int id);
    boolean updateEmployeeById(int id, Employee emp);
}
