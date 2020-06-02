package servlets;

import dao.EmployeeDAO;
import dao.EmployeeDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;

    public DeleteServlet() {
        this.employeeDAO = new EmployeeDaoImpl();
    }

    /**
     * По ID удаляет сотрудника
     * @param req id сотрудника
     * Если таклй id не существует кидает на страницу ошибки
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            employeeDAO.deleteEmployeeById(id);
            resp.sendRedirect(req.getContextPath() + "/index");
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}

