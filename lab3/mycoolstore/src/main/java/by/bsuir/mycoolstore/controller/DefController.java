package by.bsuir.mycoolstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefController {
    @RequestMapping("/")
    public ModelAndView home(){
        var mav = new ModelAndView("index");

        return mav;
    }
}
