package dao;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    Employee getEmployeeById(int id) throws SQLException;
    List<Employee> getAllEmployee() throws SQLException;
    boolean addEmployee(Employee emp) throws SQLException;
    boolean deleteEmployeeById(int id) throws SQLException;
    boolean updateEmployeeById(int id, Employee emp) throws SQLException;
}
