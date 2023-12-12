package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.entity.UserEntity;
import by.bsuir.mycoolstore.entity.enums.Role;
import by.bsuir.mycoolstore.service.exception.ServiceException;
import by.bsuir.mycoolstore.service.impl.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/")
public class CommonController {
    private final FilmService filmService;
    private final UserService userService;
    private final FeedbackService feedbackService;
    private final CartService cartService;
    private final LibraryService libraryService;
    private final MediaService mediaService;

    @Autowired
    public CommonController(FilmService filmService, UserService userService, FeedbackService feedbackService,
                            CartService cartService, LibraryService libraryService, MediaService mediaService) {
        this.filmService = filmService;
        this.userService = userService;
        this.feedbackService = feedbackService;
        this.cartService = cartService;
        this.libraryService = libraryService;
        this.mediaService = mediaService;
    }

    @GetMapping("/")
    public ModelAndView mainPage() throws ServiceException {
        var mav = new ModelAndView("index");

        var films = filmService.getFilms();
        mav.addObject("films", films);

        return mav;
    }

    @GetMapping("Register")
    public String registrationPage(Map<String, Object> model) {
        var user = new UserEntity();

        model.put("user", user);

        return "register";
    }

    @PostMapping("Register")
    public String registration(@ModelAttribute("user") UserEntity user, HttpServletRequest request) throws ServiceException {
        user.setUsrRole(Role.CUSTOMER.toString());
        user.setUsrPassword(UserEntity.getHashSha512Password(user.getUsrPassword()));

        Long id = userService.registration(user);

        request.getSession().setAttribute("userID", id);

        return "redirect:/";
    }

    @GetMapping("Authorization")
    public String authorisationPage(Map<String, Object> model) {
        var user = new UserEntity();

        model.put("user", user);

        return "authorization";
    }

    @PostMapping("Authorization")
    public String authorization(@ModelAttribute("user") UserEntity user, HttpServletRequest request) throws ServiceException {
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
    public ModelAndView filmPage(HttpServletRequest request) {
        var session = request.getSession();
        Long userId = (Long) session.getAttribute("userID");

        var mav = new ModelAndView("customerFilm");
        Long filmId = Long.valueOf(request.getParameter("filmId"));

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

        return mav;
    }

    @PostMapping("Exit")
    public String exit(HttpServletRequest request) {
        var session = request.getSession();

        session.removeAttribute("userID");
        session.removeAttribute("isAdmin");

        return "redirect:/";
    }
}