package by.bsuir.mycoolsite.controller.page;

import by.bsuir.mycoolsite.controller.page.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for providing instances of pages based on their names.
 */
public final class PageProvider {
    private static final Logger logger = LogManager.getLogger(PageProvider.class);
    private static final PageProvider instance = new PageProvider();
    private final Map<PageName, Page> repository = new HashMap<>();

    /**
     * Private constructor to enforce singleton pattern.
     */
    private PageProvider() {
        repository.put(PageName.MAIN, new MainPage());
        repository.put(PageName.WRONG_REQUEST, new WrongRequestPage());
        repository.put(PageName.FILM, new FilmPage());
        repository.put(PageName.CART, new CartPage());
        repository.put(PageName.LIBRARY, new LibraryPage());
        repository.put(PageName.ADD_FILM, new AdminFilmPage());
        repository.put(PageName.BAN_LIST, new BanListPage());
    }

    /**
     * Gets the instance of the PageProvider.
     *
     * @return PageProvider instance
     */
    public static PageProvider getInstance() {
        return instance;
    }

    /**
     * Gets the instance of the page based on the provided mapping.
     *
     * @param mapping String representing the mapping or URI
     * @return Page instance corresponding to the mapping, or WrongRequestPage if not found
     */
    public Page getPage(String mapping) {
        PageName pageName;
        Page page;

        try {
            pageName = PageName.fromURI(mapping);
            page = repository.get(pageName);
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.error("Wrong request in PageProvider: ", e);
            page = repository.get(PageName.WRONG_REQUEST);
        }

        return page;
    }
}
