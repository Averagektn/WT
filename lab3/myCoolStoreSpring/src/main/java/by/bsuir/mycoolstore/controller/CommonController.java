package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.entity.UserEntity;
import by.bsuir.mycoolstore.entity.enums.Role;
import by.bsuir.mycoolstore.service.exception.ServiceException;
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

import java.util.Map;

@Controller
@RequestMapping("/")
public class CommonController {
    private final FilmService filmService;
    private final UserService userService;

    @Autowired
    public CommonController(FilmService filmService, UserService userService) {
        this.filmService = filmService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView mainPage() throws ServiceException {
        var mav = new ModelAndView("index");

        var films = filmService.getFilms();
        mav.addObject("films", films);

        return mav;
    }

    @GetMapping("Register")
    public String registrationPage(Map<String, Object> model) throws ServiceException {
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
}
