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
import java.util.List;

@WebServlet(urlPatterns = {"/index",""})
public class IndexServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;

    public IndexServlet() {
        this.employeeDAO = new EmployeeDaoImpl();
    }

    /**
     * Получает данные из БД и передает их на страницу index.jsp
     * @param req добвляет в него список работников из БД
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<Employee> employees = employeeDAO.getAllEmployee();
            req.setAttribute("employees", employees);

            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
