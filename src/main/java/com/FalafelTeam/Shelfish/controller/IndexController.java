package com.FalafelTeam.Shelfish.controller;

import com.FalafelTeam.Shelfish.model.User;
import com.FalafelTeam.Shelfish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class that handles http requests at "/"
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    /**
     * method that handles get requests for the page
     * @param model is a model used to represent the page and its attributes
     * @return html page with the login form
     */
    @GetMapping
    public String indexForm(Model model) {
        model.addAttribute("signin", new SignInData());
        return "index";
    }

    /**
     * method that handles post requests for the page
     * @param signin is an object that contains all data needed to login user
     * @return redirect to the library page if the user is a librarian, redirect to initial page otherwise
     */
    @PostMapping
    public String indexSubmit(@ModelAttribute SignInData signin) {
        User found = userRepository.findUserByLoginAndPassword(signin.getLogin(), signin.getPassword());
        if (found == null) {
            return "redirect:/";
        } else {
            if (found.getType().equals("librarian")) {
                return "redirect:/librarian";
            }
            else if (found.getType().equals("student") || found.getType().equals("faculty")){
                return "redirect:/library";
            } else {
                return "redirect:/";
            }
        }
    }

}

/**
 * class that contains data needed to login user
 */
class SignInData {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
