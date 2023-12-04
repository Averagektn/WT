package by.bsuir.mycoolsite.controller;

import by.bsuir.mycoolsite.broker.RabbitMQ;
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

/**
 * Servlet for handling video requests. Supports GET and POST methods for streaming video content.
 *
 * <p>Expects parameters for film or trailer, retrieves the corresponding video file, and streams it.
 * Redirects to the main page on incorrect requests. Utilizes Log4j for logging.</p>
 */
public class VideoController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(VideoController.class);
    private static final String FILM_NAME = "filmPath";
    private static final String TRAILER_NAME = "trailerPath";

    /**
     * Handles GET requests for video streaming. Retrieves the requested video file based on parameters
     * and streams it as "video/mp4" response. Sends 404 error if the file is not found.
     *
     * @param request  HttpServletRequest containing request parameters
     * @param response HttpServletResponse used to send video content or errors
     * @throws IOException if an I/O error occurs during video content handling
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String filmNameParam = request.getParameter(FILM_NAME);
        String trailerNameParam = request.getParameter(TRAILER_NAME);
        String filePath;
        File file = null;

        if (trailerNameParam != null) {
            filePath = Config.VIDEO_DIRECTORY_PATH + Config.TRAILER_DIR + File.separator + trailerNameParam;
            file = new File(filePath);
            RabbitMQ.sendMessage("Trailer: " + trailerNameParam);
            //logger.info("Trailer: " + trailerNameParam);
        } else if (filmNameParam != null) {
            filePath = Config.VIDEO_DIRECTORY_PATH + Config.FILM_DIR + File.separator + filmNameParam;
            file = new File(filePath);
            RabbitMQ.sendMessage("Film: " + filmNameParam);
            //logger.info("Film: " + filmNameParam);
        } else {
            response.sendRedirect(PageName.MAIN.getUrlPattern());
            RabbitMQ.sendMessage("Incorrect request to VideoController");
            //logger.info("Incorrect request to VideoController");
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

    /**
     * Handles POST requests by delegating to the doGet method to support both GET and POST for video retrieval.
     *
     * @param request  HttpServletRequest containing request parameters
     * @param response HttpServletResponse used to send video content or errors
     * @throws IOException if an I/O error occurs during video content handling
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
