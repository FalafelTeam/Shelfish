package com.FalafelTeam.Shelfish;

import com.FalafelTeam.Shelfish.controller.BookingController;
import com.FalafelTeam.Shelfish.model.Author;
import com.FalafelTeam.Shelfish.model.Document;
import com.FalafelTeam.Shelfish.model.Publisher;
import com.FalafelTeam.Shelfish.model.User;
import com.FalafelTeam.Shelfish.repository.*;
import com.FalafelTeam.Shelfish.service.BookingSystemManager;
import com.FalafelTeam.Shelfish.service.ModelManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShelfishUserStories {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    DocumentUserRepository documentUserRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookingSystemManager bookingManager;
    @Autowired
    ModelManager modelManager;

    @Test
    public void userStory1() throws Exception {
        User user = modelManager.addUser("TestUser1", "student", "123", "123", "1", "1");
        Publisher pub = modelManager.addPublisher("publisher");
        Author author = modelManager.addAuthor("author");
        Document b1 = modelManager.addBook("testbook", 2, 0,false, false, 0, pub, author, "");
        bookingManager.bookDocument(b1, user, 2,false);
    }
}
