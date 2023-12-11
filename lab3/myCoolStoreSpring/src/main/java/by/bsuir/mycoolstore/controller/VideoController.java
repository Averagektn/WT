package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.config.file.FileConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/Video/")
public class VideoController {
    private static final Logger logger = LogManager.getLogger(VideoController.class);
    private static final String FILM_NAME = "filmPath";
    private static final String TRAILER_NAME = "trailerPath";

    @GetMapping("Trailer")
    public void getTrailer(HttpServletRequest request, HttpServletResponse response) {
        String trailerNameParam = request.getParameter(TRAILER_NAME);
        String filePath;
        File file;

        filePath = FileConfig.VIDEO_DIRECTORY_PATH + FileConfig.TRAILER_DIR + File.separator + trailerNameParam;
        file = new File(filePath);
        logger.info("Trailer: " + trailerNameParam);

        getFile(response, file);
    }

    @GetMapping("Film")
    public void getFilm(HttpServletRequest request, HttpServletResponse response) {
        String filmNameParam = request.getParameter(FILM_NAME);
        String filePath;
        File file;

        filePath = FileConfig.VIDEO_DIRECTORY_PATH + FileConfig.FILM_DIR + File.separator + filmNameParam;
        file = new File(filePath);
        logger.info("Film: " + filmNameParam);

        getFile(response, file);
    }

    private void getFile(HttpServletResponse response, File file) {
        if (file.exists()) {
            response.setContentType("video/mp4");

            try (var fileInputStream = new FileInputStream(file); var outputStream = response.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
