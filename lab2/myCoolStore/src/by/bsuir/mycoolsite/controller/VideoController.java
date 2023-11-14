package by.bsuir.mycoolsite.controller;

import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class VideoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TO DO get filename from db

        HttpSession session = request.getSession(false);
        Object userIdParam = session.getAttribute(SessionAttribute.ID);
        String filmIdParam = request.getParameter("filmId");
        System.out.println("Film: " + filmIdParam);
        String trailerIdParam = request.getParameter("trailerId");
        System.out.println("Trailer: " + trailerIdParam);

        if (trailerIdParam != null){
            // get filename from DB
            // get path from config
            String filePath = "C:/Archive/5 semester/WT/tomcat/files/trailer/1699911967734_turnip turns up.mp4";
            File file = new File(filePath);

            if (file.exists()) {
                response.setContentType("video/mp4");

                try (FileInputStream fileInputStream = new FileInputStream(file);
                     OutputStream outputStream = response.getOutputStream()) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else if (filmIdParam != null && userIdParam != null){
            // check if user is owner
            // get film name
            // get path from config
            String filePath = "C:/Archive/5 semester/WT/tomcat/files/film/1699911967735_turnip turns up.mp4";
            File file = new File(filePath);

            if (file.exists()) {
                response.setContentType("video/mp4");

                try (FileInputStream fileInputStream = new FileInputStream(file);
                     OutputStream outputStream = response.getOutputStream()) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendRedirect(PageName.MAIN.getUrlPattern());
        }

    }

    // Add film saving
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
