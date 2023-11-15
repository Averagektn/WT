package by.bsuir.mycoolsite.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Servlet for handling video requests. Supports GET and POST methods for streaming video content.
 *
 * <p>Expects parameters for film or trailer, retrieves the corresponding video file, and streams it.
 * Redirects to the main page on incorrect requests. Utilizes Log4j for logging.</p>
 *
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
        // ... (Method implementation)
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
