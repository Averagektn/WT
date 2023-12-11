package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.entity.FilmEntity;
import by.bsuir.mycoolstore.service.impl.AgeRestrictionService;
import by.bsuir.mycoolstore.service.impl.CategoryService;
import by.bsuir.mycoolstore.service.impl.FilmService;
import by.bsuir.mycoolstore.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/Admin/")
public class AdminController {
    private final CategoryService categoryService;
    private final FilmService filmService;
    private final UserService userService;

    @Autowired
    public AdminController(CategoryService categoryService, FilmService filmService, UserService userService) {
        this.categoryService = categoryService;
        this.filmService = filmService;
        this.userService = userService;
    }

    @GetMapping("AddFilm")
    public ModelAndView addPage(Map<String, Object> model) {
        var mav = new ModelAndView("adminFilm");

        var ageRestrictions = AgeRestrictionService.getAgeRestrictions();
        var categories = categoryService.getCategories();
        var film = new FilmEntity();
        film.setCategories(new ArrayList<>());
        film.setFlmId(0L);
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
        model.put("filmA", new FilmEntity());

        return mav;
    }

    @GetMapping("EditFilm")
    public ModelAndView editPage(HttpServletRequest request) {
        var mav = new ModelAndView("adminFilm");

        var filmId = Long.valueOf(request.getParameter("filmId"));

        var ageRestrictions = AgeRestrictionService.getAgeRestrictions();
        var categories = categoryService.getCategories();
        FilmEntity film;
        var filmOpt = filmService.getFilmById(filmId);
        if (filmOpt.isPresent()) {
            film = filmOpt.get();
            ageRestrictions.remove(film.getFlmAge());
            categories.removeIf(c1 -> film.getCategories().stream().anyMatch(c2 -> c2.getCatName().equals(c1.getCatName())));

            mav.addObject("command", "EditFilm");
            mav.addObject("film", film);
            mav.addObject("ageRestrictions", ageRestrictions);
            mav.addObject("categories", categories);
        } else {
            mav.setViewName("error");
        }

        return mav;
    }

    @GetMapping("BanList")
    public ModelAndView banListPage() {
        var mav = new ModelAndView("banList");

        var users = userService.getBannedUsers();
        mav.addObject("users", users);

        return mav;
    }

    @PostMapping("AddFilm")
    public String addFilm(@ModelAttribute("filmA") FilmEntity film) {
        filmService.addFilm(film);

        return "redirect:/Admin/AddFilm";
    }

    @PostMapping("Unban")
    public void unban(HttpServletRequest request) {

    }

    @PostMapping("Ban")
    public void ban(HttpServletRequest request) {

    }
}
