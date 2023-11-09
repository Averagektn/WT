package by.bsuir.mycoolsite.controller.page;

import by.bsuir.mycoolsite.controller.page.impl.FilmPage;
import by.bsuir.mycoolsite.controller.page.impl.MainPage;
import by.bsuir.mycoolsite.controller.page.impl.WrongRequestPage;

import java.util.HashMap;
import java.util.Map;

public final class PageProvider {
    private static final PageProvider instance = new PageProvider();
    private final Map<PageName, Page> repository = new HashMap<>();

    public PageProvider() {
        repository.put(PageName.MAIN, new MainPage());
        repository.put(PageName.WRONG_REQUEST, new WrongRequestPage());
        repository.put(PageName.FILM, new FilmPage());
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
