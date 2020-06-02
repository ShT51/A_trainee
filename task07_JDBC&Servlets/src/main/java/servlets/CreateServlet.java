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
import java.sql.SQLException;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;

    public CreateServlet() {
        this.employeeDAO = new EmployeeDaoImpl();
    }

    /**
     * Перенаправялет на форму создания нового сотрудника create.jsp
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create.jsp").forward(req, resp);
    }

    /**
     * Получает данные из формы заполнения и добавляет юзера в БД.
     * В случае успеха перенаправляет на IndexServlet, в случае ошибки возвращает снова
     * на страницу создания нового сотрудника
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");
            String department = req.getParameter("department");
            long salary = Long.parseLong(req.getParameter("salary"));

            Employee employee = new Employee(firstName, lastName, email, department, salary);
            employeeDAO.addEmployee(employee);
            resp.sendRedirect(req.getContextPath() + "/index");

        } catch (SQLException e) {
            getServletContext().getRequestDispatcher("/create.jsp").forward(req, resp);
        }
    }
}
