package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.config.file.FileConfig;
import by.bsuir.mycoolstore.entity.CategoryEntity;
import by.bsuir.mycoolstore.entity.FilmEntity;
import by.bsuir.mycoolstore.entity.FilmMediaEntity;
import by.bsuir.mycoolstore.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Admin/")
public class AdminController {
    private final CategoryService categoryService;
    private final FilmService filmService;
    private final UserService userService;
    private final MediaService mediaService;

    @Autowired
    public AdminController(CategoryService cs, FilmService fs, UserService us, MediaService ms) {
        this.categoryService = cs;
        this.filmService = fs;
        this.userService = us;
        this.mediaService = ms;
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
        model.addAttribute("film", film);

        mav.addObject("command", "AddFilm");
        mav.addObject("film", film);
        mav.addObject("ageRestrictions", ageRestrictions);
        mav.addObject("categories", categories);

        return mav;
    }

    @GetMapping("EditFilm")
    public ModelAndView editPage(@RequestParam("filmId") Long filmId, Model model) {
        var mav = new ModelAndView("adminFilm");

        var ageRestrictions = AgeRestrictionService.getAgeRestrictions();
        var categories = categoryService.getCategories();
        FilmEntity film;
        var filmOpt = filmService.getFilmById(filmId);
        if (filmOpt.isPresent()) {
            film = filmOpt.get();
            ageRestrictions.remove(film.getFlmAge());
            categories.removeIf(c1 -> film.getCategories().stream().anyMatch(c2 -> c2.getCatName().equals(c1.getCatName())));
            model.addAttribute("film", film);

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
            @ModelAttribute("film") FilmEntity film,
            @RequestPart("filmFile") MultipartFile filmFile,
            @RequestPart("trailerFile") MultipartFile trailerFile,
            @RequestParam("filmCategory") List<Long> categories
    ) throws IOException {
        addCategories(film, categories);

        var addedFilm = filmService.save(film);

        String filmFilename = Instant.now().toEpochMilli() + filmFile.getOriginalFilename();
        String trailerFilename = Instant.now().toEpochMilli() + trailerFile.getOriginalFilename();

        var media = new FilmMediaEntity();
        media.setFmId(addedFilm.getFlmId());
        media.setFmFilmPath(filmFilename);
        media.setFmTrailerPath(trailerFilename);
        mediaService.save(media);

        saveFile(filmFile, FileConfig.VIDEO_DIRECTORY_PATH + FileConfig.FILM_DIR + File.separator + filmFilename);
        saveFile(trailerFile, FileConfig.VIDEO_DIRECTORY_PATH + FileConfig.TRAILER_DIR + File.separator + trailerFilename);

        return "redirect:/";
    }

    @PostMapping("EditFilm")
    public String editFilm(@ModelAttribute("film") FilmEntity film, @RequestParam("filmCategory") List<Long> categories) {
        addCategories(film, categories);

        filmService.save(film);

        return "redirect:/";
    }

    private void addCategories(FilmEntity film, List<Long> categories) {
        var dbCategories = new ArrayList<CategoryEntity>();

        for (var cat : categories) {
            var c = new CategoryEntity();
            c.setCatId(cat);
            dbCategories.add(c);
        }

        film.setCategories(dbCategories);
    }

    private void saveFile(MultipartFile file, String filePath) throws IOException {
        try {
            Path targetPath = Path.of(filePath);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Не удалось сохранить файл: " + filePath, e);
        }
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
