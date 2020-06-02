package dao;

import model.Employee;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeDaoImplTest {

    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    private static Employee newEmployee = new Employee(999,"Jon", "Doe", "jon@missing.com", "lost_department", 10_000L);
    private static Employee updatedEmployee = new Employee(999,"Jon", "Updated - Doe", "jon@missing.com", "lost_department", 99_000L);

    @Test
    @Order(1)
    void addNewEmployee() throws SQLException {
        assertTrue(employeeDao.addEmployee(newEmployee));
    }

    @Test
    @Order(2)
    void getEmployeeById_New_Employee() throws SQLException {
        Employee actual = employeeDao.getEmployeeById(999);
        assertEquals(newEmployee.getId(), actual.getId());
        assertEquals(newEmployee.getFirstName(), actual.getFirstName());
        assertEquals(newEmployee.getLastName(), actual.getLastName());
        assertEquals(newEmployee.getEmail(), actual.getEmail());
        assertEquals(newEmployee.getDepartment(), actual.getDepartment());
        assertEquals(newEmployee.getSalary(), actual.getSalary());
    }

    @Test
    @Order(3)
    void updateEmployee() throws SQLException {
        assertTrue(employeeDao.updateEmployeeById(999, updatedEmployee));
    }

    @Test
    @Order(4)
    void getEmployeeById_Updated_Employee() throws SQLException {
        Employee actual = employeeDao.getEmployeeById(999);
        assertEquals(updatedEmployee.getId(), actual.getId());
        assertEquals(updatedEmployee.getFirstName(), actual.getFirstName());
        assertEquals(updatedEmployee.getLastName(), actual.getLastName());
        assertEquals(updatedEmployee.getEmail(), actual.getEmail());
        assertEquals(updatedEmployee.getDepartment(), actual.getDepartment());
        assertEquals(updatedEmployee.getSalary(), actual.getSalary());
    }

    @Test
    @Order(5)
    void getAllEmployees() throws SQLException {
       assertEquals(13, employeeDao.getAllEmployee().size());
    }
    @Test
    @Order(6)
    void getEmployeeByEmail() throws SQLException {
        Employee actual = employeeDao.getEmployeeByEmail("jon@missing.com");
        assertEquals(updatedEmployee.getId(), actual.getId());
        assertEquals(updatedEmployee.getFirstName(), actual.getFirstName());
        assertEquals(updatedEmployee.getLastName(), actual.getLastName());
        assertEquals(updatedEmployee.getEmail(), actual.getEmail());
        assertEquals(updatedEmployee.getDepartment(), actual.getDepartment());
        assertEquals(updatedEmployee.getSalary(), actual.getSalary());
    }


    @Test
    @Order(7)
    void deleteEmployeeById() throws SQLException {
        assertTrue(employeeDao.deleteEmployeeById(999));
    }
}