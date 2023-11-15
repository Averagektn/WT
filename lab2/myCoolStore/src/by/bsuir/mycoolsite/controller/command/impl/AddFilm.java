package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.bean.Category;
import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.bean.Media;
import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.config.Config;
import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddFilm implements Command {
    private static final Logger logger = LogManager.getLogger(AddFilm.class);
    private static final String FILM_TITLE = "filmTitle";
    private static final String FILM_AUTHOR = "filmAuthor";
    private static final String FILM_CATEGORIES = "filmCategory";
    private static final String FILM_AGE_RESTRICTION = "filmAgeRestriction";
    private static final String FILM_FILE = "filmFile";
    private static final String FILM_TRAILER = "trailerFile";
    private static final String FILM_DESCRIPTION = "filmDescription";
    private static final String FILM_PRICE = "filmPrice";
    private static final String FILM_DISCOUNT = "filmDiscount";
    private static final String FILM_DIRECTORY = "film";
    private static final String TRAILER_DIRECTORY = "trailer";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FilmService filmService = serviceFactory.getFilmService();

        try {
            Film film = getFilm(request);

            filmService.addNewFilm(film);

            response = PageName.ADD_FILM.getUrlPattern();
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            throw new CommandException("Service exception: ", e);
        } catch (ServletException e) {
            logger.error("Servlet exception in film adder", e);
            throw new CommandException("Servlet exception in film adder: " + e);
        } catch (IOException e) {
            logger.error("IO exception in film adder" , e);
            throw new CommandException("IO exception in film adder: " + e);
        }

        return response;
    }

    private Film getFilm(HttpServletRequest request) throws ServletException, IOException {
        String title = request.getParameter(FILM_TITLE);
        String author = request.getParameter(FILM_AUTHOR);
        String description = request.getParameter(FILM_DESCRIPTION);
        AgeRestriction ageRestriction = AgeRestriction.getAgeRestrictionFromString(request.getParameter(FILM_AGE_RESTRICTION));
        BigDecimal price = new BigDecimal(request.getParameter(FILM_PRICE));
        int discount = Integer.parseInt(request.getParameter(FILM_DISCOUNT));
        Media media = loadFiles(request);
        List<Category> categories = new ArrayList<>();
        for (String cat : request.getParameterValues(FILM_CATEGORIES)) {
            categories.add(new Category(Long.parseLong(cat)));
        }

        return new Film(description, price, media, discount, author, ageRestriction, title, categories);
    }

    private Media loadFiles(HttpServletRequest request) throws ServletException, IOException {
        Part trailerPart = request.getPart(FILM_TRAILER);
        String trailerFileName = generateUniqueFileName(getFileName(trailerPart));
        String trailerFilePath = Config.VIDEO_DIRECTORY_PATH + File.separator + TRAILER_DIRECTORY + File.separator +
                trailerFileName;
        trailerPart.write(trailerFilePath);

        Part filmPart = request.getPart(FILM_FILE);
        String filmFileName = generateUniqueFileName(getFileName(filmPart));
        String filmFilePath = Config.VIDEO_DIRECTORY_PATH + File.separator + FILM_DIRECTORY + File.separator +
                filmFileName;
        filmPart.write(filmFilePath);

        logger.info("Adding film " + filmFileName);
        logger.info("Adding trailer " + trailerFileName);

        return new Media(trailerFileName, filmFileName);
    }

    private String generateUniqueFileName(String fileName) {
        return System.currentTimeMillis() + "_" + fileName;
    }

    private String getFileName(final Part part) {
        String partHeader = part.getHeader("content-disposition");

        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }
}
