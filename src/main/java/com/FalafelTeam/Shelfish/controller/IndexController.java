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

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String indexForm(Model model) {
        model.addAttribute("signin", new SignInData());
        return "index";
    }

    @PostMapping
    public String indexSubmit(@ModelAttribute SignInData signin, Model model) {
        System.out.println(signin.getLogin());
        User found = userRepository.findUserByLoginAndPassword(signin.getLogin(), signin.getPassword());
        if (found == null) {
            model.addAttribute("signin", new SignInData());
            return "index";
        } else {
            if (found.getType().equals("librarian")) {
                return "library";
            }
            else {
                model.addAttribute("signin", new SignInData());
                return "index";
            }
        }
    }

}

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
