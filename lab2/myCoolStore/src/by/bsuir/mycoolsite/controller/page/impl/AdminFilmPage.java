package by.bsuir.mycoolsite.controller.page.impl;

import by.bsuir.mycoolsite.bean.Category;
import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.controller.JSPPageName;
import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import by.bsuir.mycoolsite.service.AgeRestrictionService;
import by.bsuir.mycoolsite.service.CategoryService;
import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class AdminFilmPage implements Page {
    private static final String FILM_ID = "filmId";
    private static final String CATEGORIES = "categories";
    private static final String AGE_RESTRICTIONS = "ageRestrictions";
    private static final String FILM = "film";
    private static final String COMMAND = "command";
    @Override
    public String generate(HttpServletRequest request) throws PageException {
        String response;

        List<String> ageRestrictions;
        List<Category> categories;
        Film film;
        boolean isEditFilm = request.getParameter(FILM_ID) != null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FilmService filmService = serviceFactory.getFilmService();
        CategoryService categoryService = serviceFactory.getCategoryService();
        AgeRestrictionService ageRestrictionService = serviceFactory.getAgeRestrictionService();

        try {
            ageRestrictions = ageRestrictionService.getAgeRestrictions();
            categories = categoryService.getCategories();

            if (isEditFilm){
                long filmId = Long.parseLong(request.getParameter(FILM_ID));
                film = filmService.getFilmById(filmId);
                ageRestrictions.remove(film.getAgeRestriction().toString());
                categories.removeIf(c1 -> film.getCategories().stream().anyMatch(c2 -> c2.getName().equals(c1.getName())));
                request.setAttribute(COMMAND, "edit_film");
            } else {
                film = new Film(0);
                request.setAttribute(COMMAND, "add_film");
            }

            request.setAttribute(FILM, film);
            request.setAttribute(AGE_RESTRICTIONS, ageRestrictions);
            request.setAttribute(CATEGORIES, categories);

            response = JSPPageName.PAGE_ADMIN_FILM;
        } catch (ServiceException e) {
            //LOG
            System.out.println("Page exception: " + e);
            throw new PageException("Service exception: ", e);
        }

        return response;
    }
}
