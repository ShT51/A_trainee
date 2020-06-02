package dao;

import model.Employee;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeDaoImplTest {

    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    private Connection connection;
    private static Employee newEmployee = new Employee(999,"Jon", "Doe", "jon@missing.com", "lost_department", 10_000L);
    private static Employee updatedEmployee = new Employee(999,"Jon", "Updated - Doe", "jon@missing.com", "lost_department", 99_000L);


    @BeforeEach
    void getConnectionTest() throws SQLException {
        connection = employeeDao.getConnection();
    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }

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
        assertEquals(newEmployee.getfName(), actual.getfName());
        assertEquals(newEmployee.getlName(), actual.getlName());
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
        assertEquals(updatedEmployee.getfName(), actual.getfName());
        assertEquals(updatedEmployee.getlName(), actual.getlName());
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
    void deleteEmployeeById() throws SQLException {
        assertTrue(employeeDao.deleteEmployeeById(999));
    }
}