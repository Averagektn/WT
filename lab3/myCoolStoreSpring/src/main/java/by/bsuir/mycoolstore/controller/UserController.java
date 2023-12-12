package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.entity.CartEntity;
import by.bsuir.mycoolstore.entity.FilmEntity;
import by.bsuir.mycoolstore.service.impl.CartService;
import by.bsuir.mycoolstore.service.impl.LibraryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("/User/")
public class UserController {
    private final LibraryService libraryService;
    private final CartService cartService;

    public UserController(LibraryService libraryService, CartService cartService) {
        this.libraryService = libraryService;
        this.cartService = cartService;
    }

    @GetMapping("Library")
    public ModelAndView libraryPage(HttpServletRequest request) {
        var session = request.getSession();
        Long userId = (Long) session.getAttribute("userID");

        var mav = new ModelAndView("library");

        var films = libraryService.getUserFilms(userId);
        mav.addObject("films", films);

        return mav;
    }

    @GetMapping("Cart")
    public ModelAndView cartPage(HttpServletRequest request) {
        var session = request.getSession();
        Long userId = (Long) session.getAttribute("userID");

        BigDecimal total = BigDecimal.ZERO;

        var mav = new ModelAndView("cart");

        var films = cartService.getCartFilms(userId);

        for (FilmEntity film : films) {
            total = total.add(film.getRealPrice());
        }

        mav.addObject("films", films);
        mav.addObject("total", total);

        return mav;
    }

    @PostMapping("Cart/Remove")
    public void remove(HttpServletRequest request) {

    }

    @PostMapping("Cart/Buy")
    public void buy(HttpServletRequest request) {

    }

    @PostMapping("Cart/Add")
    public String addToCart(HttpServletRequest request) {
        var session = request.getSession();
        Long userId = (Long) session.getAttribute("userID");
        Long filmId = Long.valueOf(request.getParameter("filmID"));
        var cartItem = new CartEntity();
        cartItem.setCrtUser(userId);
        cartItem.setCrtFilm(filmId);

        cartService.save(cartItem);

        return "redirect:/User/Cart/";
    }

    @PostMapping("Feedback")
    public void leaveFeedback() {

    }
}
