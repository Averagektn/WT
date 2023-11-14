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
import by.bsuir.mycoolsite.service.MediaService;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddFilm implements Command {
    // Incorrect ageRestrictions provided during page generation
    // Not list<Category> af POST request processing
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
        MediaService mediaService = serviceFactory.getMediaService();

        try {
            Film film = getFilm(request);

            //long mediaId = mediaService.addMedia(film.getMedia());
            //film.getMedia().setId(mediaId);
/*            System.out.println(film.getDiscount());
            System.out.println(film.getMedia().getFilmPath());
            System.out.println(film.getMedia().getTrailerPath());
            System.out.println(film.getAuthor());
            System.out.println(film.getName());
            System.out.println(film.getAgeRestriction().toString());
            System.out.println(film.getDescription());
            System.out.println(film.getPrice());*/
            for (Category cat: film.getCategories()){
                System.out.println(cat.getName() + " " + cat.getId());
            }

            //filmService.addNewFilm(film);

            response = PageName.ADD_FILM.getUrlPattern();
        /*} catch (ServiceException e) {
            //LOG
            System.out.println("Service exception: " + e);
            throw new CommandException("Service exception: ", e);*/
        } catch (ServletException e) {
            throw new CommandException("Servlet exception in film adder: " + e);
        } catch (IOException e) {
            throw new CommandException("IO exception in film adder: " + e);
        }

        return response;
    }

    private Film getFilm(HttpServletRequest request) throws ServletException, IOException {
        System.out.println(request.getParameter(FILM_TITLE));
        System.out.println(request.getParameter(FILM_AUTHOR));
        System.out.println(request.getParameter(FILM_DESCRIPTION));
        System.out.println(request.getParameter(FILM_AGE_RESTRICTION));
        System.out.println(request.getParameter(FILM_PRICE));
        System.out.println(request.getParameter(FILM_DISCOUNT));
        System.out.println(request.getParameter(FILM_CATEGORIES));

        String title = request.getParameter(FILM_TITLE);
        String author = request.getParameter(FILM_AUTHOR);
        String description = request.getParameter(FILM_DESCRIPTION);
        AgeRestriction ageRestriction = AgeRestriction.getAgeRestrictionFromString(request.getParameter(FILM_AGE_RESTRICTION));
        BigDecimal price = new BigDecimal(request.getParameter(FILM_PRICE));
        int discount = Integer.parseInt(request.getParameter(FILM_DISCOUNT));

        Media media = loadFiles(request);

        List<Category> categories = new ArrayList<>();
        List<Long> categoryNames = List.of(Long.valueOf(request.getParameter(FILM_CATEGORIES)));
        for (Long cat: categoryNames){
            System.out.println(cat);
            categories.add(new Category(cat));
        }

        return new Film(description, price, media, discount, author, ageRestriction, title, categories);
    }

    private Media loadFiles(HttpServletRequest request) throws ServletException, IOException {
        Part trailerPart = request.getPart(FILM_TRAILER);
        String trailerFileName = generateUniqueFileName(getFileName(trailerPart));
        String trailerFilePath = Config.VIDEO_DIRECTORY_PATH + "/" + TRAILER_DIRECTORY + "/" + trailerFileName;
        System.out.println("trailer path: " + trailerFilePath);
        trailerPart.write(trailerFilePath);

        Part filmPart = request.getPart(FILM_FILE);
        String filmFileName = generateUniqueFileName(getFileName(filmPart));
        String filmFilePath = Config.VIDEO_DIRECTORY_PATH + "/" + FILM_DIRECTORY + "/" + filmFileName;
        System.out.println("film path: " + filmFilePath);
        filmPart.write(filmFilePath);

        return new Media(trailerFileName, filmFileName);
    }

    private String generateUniqueFileName(String fileName) {
        return System.currentTimeMillis() + "_" + fileName;
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }
}
