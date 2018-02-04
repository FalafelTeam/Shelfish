package com.FalafelTeam.Shelfish.controller;

import com.FalafelTeam.Shelfish.model.User;
import com.FalafelTeam.Shelfish.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    private AuthorRepository authorRepository;
    private DocumentRepository documentRepository;
    private DocumentUserRepository documentUserRepository;
    private EditorRepository editorRepository;
    private PublisherRepository publisherRepository;
    private TypeRepository typeRepository;
    private UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUser(String login, String password) {
        return userRepository.findUserByLoginAndPassword(login, password);
    }
}
