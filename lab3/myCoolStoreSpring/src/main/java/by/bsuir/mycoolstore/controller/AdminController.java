package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.entity.FilmEntity;
import by.bsuir.mycoolstore.service.impl.AgeRestrictionService;
import by.bsuir.mycoolstore.service.impl.CategoryService;
import by.bsuir.mycoolstore.service.impl.FilmService;
import by.bsuir.mycoolstore.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Admin/")
public class AdminController {
    private final CategoryService categoryService;
    private final FilmService filmService;
    private final UserService userService;

    @Autowired
    public AdminController(CategoryService cs, FilmService fs, UserService us) {
        this.categoryService = cs;
        this.filmService = fs;
        this.userService = us;
    }

    @GetMapping("AddFilm")
    public ModelAndView addPage(Model model) {
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
        model.addAttribute("filmAdd", film);

        mav.addObject("command", "AddFilm");
        mav.addObject("film", film);
        mav.addObject("ageRestrictions", ageRestrictions);
        mav.addObject("categories", categories);

        return mav;
    }

    @GetMapping("EditFilm")
    public ModelAndView editPage(@RequestParam("filmId") Long filmId) {
        var mav = new ModelAndView("adminFilm");

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

    @PostMapping(value = "AddFilm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addFilm(
            @ModelAttribute("filmAdd") FilmEntity film,
            @RequestPart("filmFile") MultipartFile filmFile,
            @RequestPart("trailerFile") MultipartFile trailerFile,
            @RequestParam("filmCategory") List<Long> categories,
            HttpServletRequest request
    ) {


        return "redirect:/";
    }

    @PostMapping("EditFilm")
    public String editFilm() {


        return "redirect:/";
    }

    @PostMapping("Unban")
    public String unban(@RequestParam("userId") Long userId) {
        userService.unban(userId);

        return "redirect:/Admin/BanList";
    }

    @PostMapping("Ban")
    public String ban(@RequestParam("authorId") Long userId, @SessionAttribute("userID") Long adminId) {
        userService.ban(userId, adminId);

        return "redirect:/Admin/BanList";
    }
}
