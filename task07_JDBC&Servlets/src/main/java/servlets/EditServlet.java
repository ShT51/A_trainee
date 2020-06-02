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

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    private EmployeeDAO employeeDAO;

    public EditServlet() {
        this.employeeDAO = new EmployeeDaoImpl();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Employee employee = employeeDAO.getEmployeeById(id);
            if (employee != null) {
                req.setAttribute("employee", employee);
                getServletContext().getRequestDispatcher("/edit.jsp").forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");
            String department = req.getParameter("department");
            long salary = Long.parseLong(req.getParameter("salary"));
            Employee employee = new Employee(id, firstName, lastName, email, department, salary);
            employeeDAO.updateEmployeeById(id, employee);
            resp.sendRedirect(req.getContextPath() + "/index");

        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}
