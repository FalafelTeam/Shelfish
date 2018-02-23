package com.FalafelTeam.Shelfish;

import com.FalafelTeam.Shelfish.model.Document;;
import com.FalafelTeam.Shelfish.model.User;
import com.FalafelTeam.Shelfish.service.BookingSystemManager;
import com.FalafelTeam.Shelfish.service.ModelManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShelfishUserStories {

    @Autowired
    BookingSystemManager bookingManager;
    @Autowired
    ModelManager modelManager;

    @Test
    public void userStory1() throws Exception {
        modelManager.clearDB();
        User user = modelManager.addUser("TestUser1", "student", "123", "123", "1", "1");
        List<String> authorNames = new LinkedList<>();
        authorNames.add("author");
        Document b1 = modelManager.addDocument("testbook", "book", authorNames, "publisher",
                "", 1, null, "", "", false, 0, 2, false);
        bookingManager.bookDocument(b1, user, 2,false);
    }

    @Test
    public void userStory2() throws Exception {
        modelManager.clearDB();
        User librarian = modelManager.addUser("lib", "librarian", "345", "345","", "");
        User user = modelManager.addUser("TestUser1", "faculty", "123", "123", "1", "1");
        List<String> authorNames = new LinkedList<>();
        authorNames.add("author");
        Document b1 = modelManager.addDocument("testbook", "book", authorNames, "publisher",
                "", 1, null, "", "", false, 0, 2, false);
        bookingManager.bookDocument(b1, user, 1,false);
        bookingManager.checkOutDocument(b1, user, librarian);
    }

    @Test
    public void userStory3(){
        modelManager.clearDB();
        User user1 = modelManager.addUser("TestUser1", "student", "234", "234", "", "");
    }

    @Test
    public void userStory4() throws Exception {
        modelManager.clearDB();
        User user = modelManager.addUser("TestUser1", "faculty", "123", "123", "1", "1");
        User userlibrarian = modelManager.addUser("TestUser2", "librarian", "123", "123", "1", "1");
        List<User> users = modelManager.getAllPatrons();
    }

    /*@Test
    public void userStory5() throws Exception {
        modelManager.clearDB();
        User userlibrarian = modelManager.addUser("TestUser2", "librarian", "123", "123", "1", "1");
        Publisher pub = modelManager.addPublisher("publisher");
        Author author = modelManager.addAuthor("author");
        Document b1 = modelManager.addBook("testbook", 2, 0,false, false, 0, pub, author, "");
        b1.setName("newname");
        b1.setCopies(10);
        b1.setDescription("newdescription");
        b1.setEdition(1);
        b1.setIsBestseller(true);
        b1.setIsReference(true);
        b1.setPrice(123);
        b1.setPublisher(pub);
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
    public void userStory6(){
        modelManager.clearDB();
        Publisher pub = modelManager.addPublisher("publisher");
        Author author = modelManager.addAuthor("author");
        Document b1 = modelManager.addBook("testbook", 2, 0,false, false, 0, pub, author, "");
        modelManager.addCopiesToDocument(b1, 1);
    }

    @Test
    public void userStory7() throws Exception {
        modelManager.clearDB();
        User userlibrarian = modelManager.addUser("TestUser2", "librarian", "123", "123", "1", "1");
        Publisher pub = modelManager.addPublisher("publisher");
        Author author = modelManager.addAuthor("author");
        Document b1 = modelManager.addBook("testbook", 2, 0,false, false, 0, pub, author, "");
    }

    @Test
    public void userStory8() throws Exception {
        modelManager.clearDB();
        User userlibrarian = modelManager.addUser("TestUser2", "librarian", "123", "123", "1", "1");
        User user = modelManager.addUser("TestUser1", "faculty", "123", "123", "1", "1");
        modelManager.editUserPhoneNumber(user, "123123123");
    }
    @Test
    public void userStory9() throws Exception {
        modelManager.clearDB();
        User librarian = modelManager.addUser("lib", "librarian", "345", "345","", "");
        User user = modelManager.addUser("TestUser1", "faculty", "123", "123", "1", "1");
        Publisher pub = modelManager.addPublisher("publisher");
        Author author = modelManager.addAuthor("author");
        Document b1 = modelManager.addBook("testbook", 2, 0,false, false, 0, pub, author, "");
        bookingManager.bookDocument(b1, user, 1,false);
        bookingManager.checkOutDocument(b1, user, librarian);
        List<Document> documents = (List<Document>) documentRepository.findAll();
        List<User> users = (List<User>) userRepository.findAll();
        List<DocumentUser> relations = (List<DocumentUser>) documentUserRepository.findAll();
    }

    @Test
    public void userStory10(){
        User user = modelManager.addUser("shelfish1", "faculty", "123", "123", "1", "1");
        User user1 = modelManager.addUser("shelfish2", "student", "123", "123", "1", "1");
        modelManager.deleteUserById(userRepository.findByName("shelfish1").getId());
    }*/

    @Test
    public void userStory11(){
        //As a librarian, I would like to modify some information about a
        //particular user such as a student has been upgraded to faculty
    }

    /*@Test
    public void userStory12() throws Exception {
        modelManager.clearDB();
        User librarian = modelManager.addUser("lib", "librarian", "345", "345","", "");
        User user = modelManager.addUser("TestUser1", "faculty", "123", "123", "1", "1");
        Publisher pub = modelManager.addPublisher("publisher");
        Author author = modelManager.addAuthor("author");
        Document b1 = modelManager.addBook("testbook", 2, 0,false, false, 0, pub, author, "");
        bookingManager.bookDocument(b1, user, 1,false);
        bookingManager.checkOutDocument(b1, user, librarian);

        documentUserRepository.findByUserAndDocument(user, b1).getDueDate();
    }*/

    @Test
    public void userStory13(){
        //As a patron, I would like to return a book.
    }

    @Test
    public void userStory14(){
        //As a librarian, I would like to delete a book from the storage
        //system (e.g. because it has been lost).
    }

    @Test
    public void userStory15(){
        //As a librarian I want to add a new document and specify the
        //amount of copies of the document
    }

    @Test
    public void userStory16(){
        //As a librarian I want to see the list of checked out documents
        //(as in US9) and request the return of some specific document.
    }

    @Test
    public void userStory17(){
        //As a librarian I would like to accept the documents returned by
        //patrons
    }
}