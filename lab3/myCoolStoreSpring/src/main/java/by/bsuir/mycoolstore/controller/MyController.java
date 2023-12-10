package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.service.exception.ServiceException;
import by.bsuir.mycoolstore.service.impl.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
    @Autowired
    private FilmService filmService;

    @RequestMapping("/film")
    public ModelAndView filmPage() throws ServiceException {
        var mav = new ModelAndView("index");

        var films = filmService.getFilms();
        mav.addObject("films", films);

        return mav;
    }
}
