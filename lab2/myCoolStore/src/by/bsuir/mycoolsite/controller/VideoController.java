package by.bsuir.mycoolsite.controller;

import by.bsuir.mycoolsite.config.Config;
import by.bsuir.mycoolsite.controller.page.PageName;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class VideoController extends HttpServlet {
    private static final String FILM_NAME = "filmPath";
    private static final String TRAILER_NAME = "trailerPath";
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String filmNameParam = request.getParameter(FILM_NAME);
        String trailerNameParam = request.getParameter(TRAILER_NAME);
        System.out.println("FILM: " + filmNameParam);
        System.out.println("TRAILER: " + trailerNameParam);

        String filePath;
        File file = null;

        if (trailerNameParam != null){
            filePath = Config.VIDEO_DIRECTORY_PATH + Config.TRAILER_DIR + File.separator + trailerNameParam;
            System.out.println("TRAILER: " + filePath);
            file = new File(filePath);

        } else if (filmNameParam != null){

            filePath = Config.VIDEO_DIRECTORY_PATH + Config.FILM_DIR + File.separator + filmNameParam;
            System.out.println("FILM: " + filePath);
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
        doGet(request, response);
    }
}
