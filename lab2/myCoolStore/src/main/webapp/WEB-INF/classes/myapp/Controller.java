package myapp;

import by.bsuir.mycoolsite.config.Config;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.sql.*;

public class Controller extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse
            response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        requestDispatcher.forward(request, response);

        Connection con = null;
        try{
            Class.forName(Config.DBConnectionClassname);
            con = DriverManager.getConnection(Config.DBConnectionURL, Config.DBUser, Config.DBPassword);
            System.out.println("Connected");

            // Statements
/*            String query = "SELECT * FROM user";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()){
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +
                        rs.getString(3) + " " + rs.getString(4));
            }*/

            String query = "SELECT * FROM user";
            PreparedStatement pstat = con.prepareStatement(query);
            ResultSet rs = pstat.executeQuery();

        } catch (SQLException e) {
            System.out.println("SQL exception: " + e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.toString());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("SQL exception: " + e.toString());
            }
        }
    }
}

