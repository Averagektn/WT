package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.entity.FeedbackEntity;
import by.bsuir.mycoolstore.entity.UserEntity;
import by.bsuir.mycoolstore.entity.enums.Role;
import by.bsuir.mycoolstore.service.exception.ServiceException;
import by.bsuir.mycoolstore.service.impl.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/")
public class CommonController {
    private static final Logger logger = LogManager.getLogger(CommonController.class);
    private final FilmService filmService;
    private final UserService userService;
    private final FeedbackService feedbackService;
    private final CartService cartService;
    private final LibraryService libraryService;
    private final MediaService mediaService;

    @Autowired
    public CommonController(FilmService fs, UserService us, FeedbackService fbs, CartService cs, LibraryService ls,
                            MediaService ms) {
        this.filmService = fs;
        this.userService = us;
        this.feedbackService = fbs;
        this.cartService = cs;
        this.libraryService = ls;
        this.mediaService = ms;
    }

    @GetMapping("/")
    public ModelAndView mainPage() {
        var mav = new ModelAndView("index");

        var films = filmService.getFilms();
        mav.addObject("films", films);

        logger.info("Main page GET");

        return mav;
    }

    @GetMapping("Register")
    public String registrationPage(Model model) {
        var user = new UserEntity();

        model.addAttribute("user", user);
        logger.info("Registration GET");

        return "register";
    }

    @PostMapping("Register")
    public String registration(@ModelAttribute("user") UserEntity user, HttpServletRequest request) {
        user.setUsrRole(Role.CUSTOMER.toString());
        user.setUsrPassword(UserEntity.getHashSha512Password(user.getUsrPassword()));
        try {
            Long id = userService.registration(user);
            request.getSession().setAttribute("userID", id);
            logger.info("Registration of " + user.getUsrEmail());
        } catch (ServiceException e) {
            logger.error("Registration od " + user.getUsrEmail() + " failed");
            return "redirect:/Error";
        }

        return "redirect:/";
    }

    @GetMapping("Authorization")
    public String authorisationPage(Map<String, Object> model) {
        var user = new UserEntity();

        model.put("user", user);

        logger.info("Authorisation GET");

        return "authorization";
    }

    @PostMapping("Authorization")
    public String authorization(@ModelAttribute("user") UserEntity user, HttpServletRequest request) {
        user.setUsrPassword(UserEntity.getHashSha512Password(user.getUsrPassword()));
        var signedUser = userService.signIn(user);

        if (signedUser.isPresent()) {
            var session = request.getSession();
            if (signedUser.get().getUsrRole().equalsIgnoreCase(Role.ADMIN.toString())) {
                session.setAttribute("isAdmin", signedUser.get().getUsrRole());
            }
            session.setAttribute("userID", signedUser.get().getUsrId());

            return "redirect:/";
        }

        return "error";
    }

    @GetMapping("Film")
    public ModelAndView filmPage(@RequestParam("filmId") Long filmId, HttpServletRequest request, Model model) {
        Long userId = (Long) request.getSession().getAttribute("userID");
        model.addAttribute("feedback", new FeedbackEntity());

        var mav = new ModelAndView("customerFilm");

        var isFilmInCart = Boolean.FALSE;
        var isUserBanned = Boolean.TRUE;
        var isPaid = Boolean.FALSE;

        if (userId != null) {
            isFilmInCart = cartService.isInCart(userId, filmId);
            isUserBanned = userService.isBanned(userId);
            isPaid = libraryService.isInLibrary(userId, filmId);
        }

        mav.addObject("isFilmInCart", isFilmInCart);
        mav.addObject("isBanned", isUserBanned);
        mav.addObject("isPaid", isPaid);

        var film = filmService.getFilmById(filmId);
        var feedbacks = feedbackService.getFilmFeedbacks(filmId);
        var media = mediaService.getFIlmMedia(filmId);

        film.ifPresent(filmEntity -> mav.addObject("film", filmEntity));
        media.ifPresent(filmMediaEntity -> mav.addObject("media", filmMediaEntity));
        mav.addObject("feedbacks", feedbacks);

        logger.info("Film page GET");

        return mav;
    }

    @PostMapping("Exit")
    public String exit(HttpServletRequest request) {
        var session = request.getSession();

        session.removeAttribute("userID");
        session.removeAttribute("isAdmin");

        logger.info("Exiting");

        return "redirect:/";
    }

    @PostMapping("Language")
    public String changeLanguage(HttpServletRequest request) {
        var session = request.getSession();

        if (session.getAttribute("lang") != null) {
            session.removeAttribute("lang");
        } else {
            session.setAttribute("lang", "ru");
        }

        logger.info("Changing language");

        return "redirect:/";
    }

    @GetMapping("Error")
    public ModelAndView error() {
        return new ModelAndView("error");
    }
}
