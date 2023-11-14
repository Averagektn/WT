package by.bsuir.mycoolsite.controller;

import by.bsuir.mycoolsite.config.Config;
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
    private static final String FILM_ID = "filmId";
    private static final String TRAILER_ID = "trailerId";
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Object userIdParam = session.getAttribute(SessionAttribute.ID);
        String filmIdParam = request.getParameter(FILM_ID);
        String trailerIdParam = request.getParameter(TRAILER_ID);

        String filePath;
        File file = null;

        if (trailerIdParam != null){
            // get filename from DB
            // get path from config
            filePath = Config.VIDEO_DIRECTORY_PATH + Config.TRAILER_DIR + "/1699956403234_Adolf Hitler Speech at Krupp Factory in Germany (1935) British Pathé.mp4";
            file = new File(filePath);

        } else if (filmIdParam != null && userIdParam != null){
            // check if user is owner
            // get film name
            // get path from config
            filePath = Config.VIDEO_DIRECTORY_PATH + Config.FILM_DIR + "/1699956403234_Adolf Hitler Speech at Krupp Factory in Germany (1935) British Pathé.mp4";
            file = new File(filePath);

        } else {
            response.sendRedirect(PageName.MAIN.getUrlPattern());
        }

        if (file != null && file.exists()) {
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

    }

    // Add film saving
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
