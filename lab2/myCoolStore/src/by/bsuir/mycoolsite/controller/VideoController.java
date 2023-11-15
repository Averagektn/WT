package by.bsuir.mycoolsite.controller;

import by.bsuir.mycoolsite.config.Config;
import by.bsuir.mycoolsite.controller.page.PageName;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class VideoController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(VideoController.class);
    private static final String FILM_NAME = "filmPath";
    private static final String TRAILER_NAME = "trailerPath";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String filmNameParam = request.getParameter(FILM_NAME);
        String trailerNameParam = request.getParameter(TRAILER_NAME);
        String filePath;
        File file = null;

        if (trailerNameParam != null) {
            filePath = Config.VIDEO_DIRECTORY_PATH + Config.TRAILER_DIR + File.separator + trailerNameParam;
            file = new File(filePath);
            logger.info("Trailer: " + trailerNameParam);
        } else if (filmNameParam != null) {
            filePath = Config.VIDEO_DIRECTORY_PATH + Config.FILM_DIR + File.separator + filmNameParam;
            file = new File(filePath);
            logger.info("Film: " + filmNameParam);
        } else {
            response.sendRedirect(PageName.MAIN.getUrlPattern());
            logger.info("Incorrect request to VideoController");
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
