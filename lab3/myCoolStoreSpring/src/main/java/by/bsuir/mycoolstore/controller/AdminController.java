package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.entity.CategoryEntity;
import by.bsuir.mycoolstore.entity.FilmEntity;
import by.bsuir.mycoolstore.service.impl.AgeRestrictionService;
import by.bsuir.mycoolstore.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Admin/")
public class AdminController {
    private CategoryService categoryService;

    @Autowired
    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("AddFilm")
    public ModelAndView addPage() {
        var mav = new ModelAndView("adminFilm");

        var ageRestrictions = AgeRestrictionService.getAgeRestrictions();
        var categories = categoryService.getCategories();
        var film = new FilmEntity();
        film.setCategories(new ArrayList<>());
        film.setFlmAge("");
        film.setFlmAuthor("");
        film.setFlmDescription("");
        film.setFlmDiscount((short) 0);
        film.setFlmName("");
        film.setFlmPrice(BigDecimal.ZERO);

        mav.addObject("command", "AddFilm");
        mav.addObject("film", film);
        mav.addObject("ageRestrictions", ageRestrictions);
        mav.addObject("categories", categories);

        return mav;
    }

    @GetMapping("EditFilm")
    public ModelAndView editPage() {
        var mav = new ModelAndView("adminFilm");

        return mav;
    }

    @GetMapping("BanList")
    public ModelAndView banListPage() {
        var mav = new ModelAndView("banList");

        return mav;
    }
}
