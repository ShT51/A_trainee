package servlets;

import dao.EmployeeDAO;
import dao.EmployeeDaoImpl;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/get_employee")
public class EmployeeServlet extends HttpServlet {
    EmployeeDAO employeeDAO;
    Employee employee;

    public EmployeeServlet() {
        employeeDAO = new EmployeeDaoImpl();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email").toLowerCase().trim();
            employee = employeeDAO.getEmployeeByEmail(email);
            if (employee == null) {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("employee", employee);
            getServletContext().getRequestDispatcher("/employee.jsp").forward(req, resp);
            return;
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}
