package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.entity.CartEntity;
import by.bsuir.mycoolstore.entity.FeedbackEntity;
import by.bsuir.mycoolstore.entity.FilmEntity;
import by.bsuir.mycoolstore.entity.UserEntity;
import by.bsuir.mycoolstore.service.exception.ServiceException;
import by.bsuir.mycoolstore.service.impl.CartService;
import by.bsuir.mycoolstore.service.impl.FeedbackService;
import by.bsuir.mycoolstore.service.impl.LibraryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("/User/")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final LibraryService libraryService;
    private final CartService cartService;
    private final FeedbackService feedbackService;

    public UserController(LibraryService ls, CartService cs, FeedbackService fbs) {
        this.libraryService = ls;
        this.cartService = cs;
        this.feedbackService = fbs;
    }

    @GetMapping("Library")
    public ModelAndView libraryPage(@SessionAttribute("userID") Long userId) {
        var mav = new ModelAndView("library");

        var films = libraryService.getUserFilms(userId);
        mav.addObject("films", films);

        logger.info("Library GET");

        return mav;
    }

    @GetMapping("Cart")
    public ModelAndView cartPage(@SessionAttribute("userID") Long userId) {
        BigDecimal total = BigDecimal.ZERO;

        var mav = new ModelAndView("cart");

        var films = cartService.getCartFilms(userId);

        for (FilmEntity film : films) {
            total = total.add(film.getRealPrice());
        }

        mav.addObject("films", films);
        mav.addObject("total", total);

        logger.info("Cart GET");

        return mav;
    }

    @PostMapping("Cart/Remove")
    public String remove(@SessionAttribute("userID") Long userId) {
        cartService.remove(userId);

        logger.info("Removing from cart");

        return "redirect:/User/Cart";
    }

    @PostMapping("Cart/Buy")
    public String buy(@SessionAttribute("userID") Long userId) {
        cartService.buy(userId);

        logger.info(userId + " bought films");

        return "redirect:/";
    }

    @PostMapping("Cart/Add")
    public String addToCart(@RequestParam("filmID") Long filmId, @SessionAttribute("userID") Long userId) {
        var cartItem = new CartEntity();
        cartItem.setCrtUser(userId);
        cartItem.setCrtFilm(filmId);

        cartService.save(cartItem);

        logger.info("Adding " + filmId + " to " + userId + " cart");

        return "redirect:/User/Cart";
    }

    @PostMapping("Feedback")
    public String leaveFeedback(@ModelAttribute("feedback") FeedbackEntity feedback, @SessionAttribute("userID") Long userId) {
        var author = new UserEntity();
        author.setUsrId(userId);

        feedback.setFbkAuthor(author);

        try {
            feedbackService.save(feedback);
        } catch (ServiceException e) {
            logger.error("Failed adding feedback " + feedback);
            return "redirect:/Error";
        }

        return "redirect:/Film?filmId=" + feedback.getFbkFilm();
    }
}
