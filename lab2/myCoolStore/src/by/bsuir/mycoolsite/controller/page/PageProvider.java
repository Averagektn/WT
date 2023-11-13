package by.bsuir.mycoolsite.controller.page;

import by.bsuir.mycoolsite.controller.page.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class PageProvider {
    private static final PageProvider instance = new PageProvider();
    private final Map<PageName, Page> repository = new HashMap<>();

    public PageProvider() {
        repository.put(PageName.MAIN, new MainPage());
        repository.put(PageName.WRONG_REQUEST, new WrongRequestPage());
        repository.put(PageName.FILM, new FilmPage());
        repository.put(PageName.CART, new CartPage());
        repository.put(PageName.LIBRARY, new LibraryPage());
        repository.put(PageName.ADD_FILM, new AdminFilmPage());
        repository.put(PageName.BAN_LIST, new BanListPage());
        //...
    }

    public static PageProvider getInstance() {
        return instance;
    }

    public Page getPage(String mapping) {
        PageName pageName;
        Page page;

        try {
            pageName = PageName.fromURI(mapping);
            page = repository.get(pageName);
        } catch (IllegalArgumentException | NullPointerException e) {
            //write log
            page = repository.get(PageName.WRONG_REQUEST);
        }

        return page;
    }

}
