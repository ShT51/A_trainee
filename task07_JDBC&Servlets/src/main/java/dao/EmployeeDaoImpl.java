package dao;

import connection.DataSource;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE id = ?;";
    private static final String SELECT_EMPLOYEE_BY_EMAIL = "SELECT * FROM employees WHERE email = ?;";
    private static final String SELECT_ALL_EMPLOYEE = "SELECT * FROM employees;";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE id = ?;";
    private static final String UPDATE_EMPLOYEE = "UPDATE employees SET first_name = ?, last_name = ?, " +
            "email = ?, department = ?, salary = ? " +
            " where id = ?;";
    private static final String ADD_EMPLOYEE = "INSERT INTO employees"
            + " (id, first_name, last_name, email, department, salary) VALUES "
            + "(?, ?, ?, ?, ?, ?)";


    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        Employee employee = null;
        ResultSet rs = null;

        try {
            connection = DataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int emp_id = rs.getInt("id");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String email = rs.getString("email");
                String department = rs.getString("department");
                long salary = rs.getLong("salary");
                employee = new Employee(emp_id, fName, lName, email, department, salary);
            }
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, rs);
        }
        return employee;
    }

    @Override
    public Employee getEmployeeByEmail(String email) throws SQLException {
        Employee employee = null;
        ResultSet rs = null;

        try {
            connection = DataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_EMAIL);
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String department = rs.getString("department");
                long salary = rs.getLong("salary");
                employee = new Employee(id, fName, lName, email, department, salary);
            }
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, rs);
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployee() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        ResultSet rs = null;

        try {
            connection = DataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEE);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String email = rs.getString("email");
                String department = rs.getString("department");
                long salary = rs.getLong("salary");
                employees.add(new Employee(id, fName, lName, email, department, salary));
            }
            return employees;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, rs);
        }
        return employees;
    }

    @Override
    public boolean addEmployee(Employee emp) throws SQLException {
        boolean rowAdded = false;

        try {
            connection = DataSource.getConnection();
            preparedStatement = connection.prepareStatement(ADD_EMPLOYEE);
            preparedStatement.setInt(1, emp.getId());
            preparedStatement.setString(2, emp.getFirstName());
            preparedStatement.setString(3, emp.getLastName());
            preparedStatement.setString(4, emp.getEmail());
            preparedStatement.setString(5, emp.getDepartment());
            preparedStatement.setLong(6, emp.getSalary());
            rowAdded = preparedStatement.executeUpdate() > 0;
            return rowAdded;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, null);
        }
        return rowAdded;
    }

    @Override
    public boolean updateEmployeeById(int id, Employee emp) throws SQLException {
        boolean rowUpdated = false;

        try {
            connection = DataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE);
            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getEmail());
            preparedStatement.setString(4, emp.getDepartment());
            preparedStatement.setLong(5, emp.getSalary());
            preparedStatement.setInt(6, id);
            rowUpdated = preparedStatement.executeUpdate() > 0;
            return rowUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, null);
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteEmployeeById(int id) throws SQLException {
        boolean rowDeleted = false;

        try {
            connection = DataSource.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE);
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
            return rowDeleted;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, null);
        }
        return rowDeleted;
    }

    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
        if (myRs != null) {
            myRs.close();
        }
        if (myStmt != null) {
            myStmt.close();
        }
        if (myConn != null) {
            myConn.close();
        }
    }
}
