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
import sun.jvm.hotspot.ui.Editor;

import java.util.List;

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

    @Test
    public void userStory2() throws Exception {
        User user = modelManager.addUser("TestUser1", "faculty", "123", "123", "1", "1");
        Publisher pub = modelManager.addPublisher("publisher");
        Author author = modelManager.addAuthor("author");
        Document b1 = modelManager.addBook("testbook", 2, 0,false, false, 0, pub, author, "");
        bookingManager.bookDocument(b1, user, 1,false);
    }

    @Test
    public void userStory3(){
        User user1 = modelManager.addUser("TestUser1", "student", "234", "234", "", "");
    }

    @Test
    public void userStory4() throws Exception {
        User user = modelManager.addUser("TestUser1", "faculty", "123", "123", "1", "1");
        User userlibrarian = modelManager.addUser("TestUser2", "librarian", "123", "123", "1", "1");
        List<User> users = userRepository.findAll();
    }

    @Test
    public void userStory5() throws Exception {
        User userlibrarian = modelManager.addUser("TestUser2", "librarian", "123", "123", "1", "1");
        Publisher pub = modelManager.addPublisher("publisher");
        Author author = modelManager.addAuthor("author");
        Document b1 = modelManager.addBook("testbook", 2, 0,false, false, 0, pub, author, "");
        modelManager.editDocumentName(b1, "newname");
        modelManager.editDocumentCopies(b1, 10);
        modelManager.editDocumentDescription(b1, "newdescription");
        modelManager.editDocumentEdition(b1, 1);
        modelManager.editDocumentIsBestseller(b1, true);
        modelManager.editDocumentIsReference(b1, true);
        modelManager.editDocumentPrice(b1, 123);
        modelManager.editDocumentPublisher(b1, pub);

    }

    @Test
    public void userStory7() throws Exception {
        User userlibrarian = modelManager.addUser("TestUser2", "librarian", "123", "123", "1", "1");
        Publisher pub = modelManager.addPublisher("publisher");
        Author author = modelManager.addAuthor("author");
        Document b1 = modelManager.addBook("testbook", 2, 0,false, false, 0, pub, author, "");
    }

    @Test
    public void userStory8() throws Exception {
        User userlibrarian = modelManager.addUser("TestUser2", "librarian", "123", "123", "1", "1");
        User user = modelManager.addUser("TestUser1", "faculty", "123", "123", "1", "1");
        modelManager.editUserPhoneNumber(user, "123123123");
    }
}