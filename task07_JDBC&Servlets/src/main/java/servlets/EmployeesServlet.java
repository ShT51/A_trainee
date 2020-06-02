package servlets;

import connection.DataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/employees")
public class EmployeesServlet extends HttpServlet {
    private Connection connection;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.println("Attempt to connect to DB..");
        try {
            connection = DataSource.getConnection();
            writer.println("Connection to DB successful!");
        } catch (Exception e) {
            writer.println("Connection failed...");
            writer.println(e);
        } finally {
            writer.close();
            writer.println("Connection to DB closed!");
        }
    }
}
