package com.FalafelTeam.Shelfish;

import com.FalafelTeam.Shelfish.model.Document;
import com.FalafelTeam.Shelfish.model.User;
import com.FalafelTeam.Shelfish.repository.UserRepository;
import com.FalafelTeam.Shelfish.service.BookingSystemManager;
import com.FalafelTeam.Shelfish.service.ModelManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Delivery2Tests {

    @Autowired
    ModelManager modelManager;
    @Autowired
    BookingSystemManager bookingManager;

    @Test
    public void testCase1() throws Exception {
        User librarian = modelManager.addUser("User", "librarian", "123", "123",
                "123", "123");
        List<String> authorNames = new LinkedList<>();
        authorNames.add("Thomas H. Cormen");
        authorNames.add("Charles E. Leiserson");
        authorNames.add("Ronald L. Rivest");
        authorNames.add("Clifford Stein");
        Document b1 = modelManager.addDocument("Introduction to Algorithms", "book", authorNames, "MIT Press",
                "", 3, new Date(2009, 1, 1), "", "", false, 0,
                3, false);
        List<String> authorNames2 = new LinkedList<>();
        authorNames2.add("Erich Gamma");
        authorNames2.add("Ralph Johnson");
        authorNames2.add("John Vlissides");
        authorNames2.add("Richard Helm");
        Document b2 = modelManager.addDocument("Design Patterns: Elements of Reusable Object-Oriented Software", "book", authorNames2, "Addison-Wesley Professional",
                "", 1, new Date(2003, 1, 1), "", "", true, 0, 2, false);
        List<String> authorNames3 = new LinkedList<>();
        authorNames3.add("Brooks,Jr.");
        authorNames3.add("Frederick P.");
        Document b3 = modelManager.addDocument("The Mythical Man-month", "book", authorNames3, ": Addison-Wesley Longman Publishing Co., Inc.",
                "", 2, new Date(1995, 1, 1), "", "", false, 0, 1, true);
        List<String> avAuthor = new LinkedList<>();
        avAuthor.add("Tony Hoare");
        Document av1 = modelManager.addDocument("Null References: The Billion Dollar Mistake", "avmaterial", avAuthor, null,
                null, null, null, "", "", false, 0, 1, false);
        List<String> avAuthor1 = new LinkedList<>();
        avAuthor1.add("Claude Shannon");
        Document av2 = modelManager.addDocument("Information Entropy", "avmaterial", avAuthor1, null,
                null, null, null, null, "", false, 0, 1, false);
        User p1 = modelManager.addUser("Sergey Afonso", "faculty", "123", "123", "30001", "Via Margutta, 3");
        User p2 = modelManager.addUser("Nadia Teixeira", "student", "234", "123", "30002", "Via Sacra, 13");
        User p3 = modelManager.addUser("Elvira Espindola", "student", "345", "123", "30003", "Via del Corso, 22");
        /*modelManager.getUserById(1).setId(1010);
        modelManager.getUserById(2).setId(1011);
        modelManager.getUserById(3).setId(1100);*/
    }

    @Test
    public void testCase2() throws Exception {
        // initial state
        Document b1 = modelManager.getDocumentById(1);
        Document b3 = modelManager.getDocumentById(3);
        User p2 = modelManager.getUserById(3);

        modelManager.modifyDocument(b1, null, null, b1.getCopies() - 2, null,
                null, null, null, null, null, null,
                null, null);
        modelManager.modifyDocument(b3, null, null, b3.getCopies() - 1, null,
                null, null, null, null, null, null,
                null, null);
        modelManager.deleteUser(p2);

        // checking conditions
        if (getDocumentsNumber() == 5) {
            if (modelManager.getAllUsers().size() == 3) {
                System.out.println("OK");
            } else throw new Exception("Wrong number of users in the database");
        } else throw new Exception("Wrong number of documents in the database");
    }

    @Test
    public void testCase3() throws Exception {
        User p1 = modelManager.getUserById(2);
        User p3 = modelManager.getUserById(4);

        if(!p1.getName().equals("Sergey Afonso")) {
            throw new Exception("Wrong name of p1");
        }
        if(!p1.getAddress().equals("Via Margutta, 3")) {
            throw new Exception("Wrong address of p1");
        }
        if(!p1.getPhoneNumber().equals("30001")) {
            throw new Exception("Wrong phone number of p1");
        }
        /*if(p1.getId() != 1010) {
            throw new Exception("Wrong id of p1");
        }*/
        if(!p1.getType().equals("faculty")) {
            throw new Exception("Wrong type of p1");
        }
        /*if(p1.getDocuments() != null) {
            throw new Exception("Wrong documents of p3");
        }*/

        if(!p3.getName().equals("Elvira Espindola")) {
            throw new Exception("Wrong name of p3");
        }
        if(!p3.getAddress().equals("Via del Corso, 22")) {
            throw new Exception("Wrong address of p3");
        }
        if(!p3.getPhoneNumber().equals("30003")) {
            throw new Exception("Wrong phone number of p3");
        }
        /*if(p3.getId() != 1100) {
            throw new Exception("Wrong id of p3");
        }*/
        if(!p3.getType().equals("student")) {
            throw new Exception("Wrong type of p3");
        }
        /*if(p3.getDocuments() != null) {
            throw new Exception("Wrong documents of p3");
        }*/

    }

    @Test
    public void testCase4() throws Exception {
        try {
            User p2 = modelManager.getUserByName("p2");
            p2.getId();
            p2.getType();
            p2.getDocuments();
            p2.getName();
            p2.getLogin();
        }
        catch(Exception e){
            throw new Exception("Trying to access a user that is deleted or doesn't exist");
        }
        User p3 = modelManager.getUserByName("p3");
        p3.getId();
        p3.getType();
        p3.getDocuments();
        p3.getName();
        p3.getLogin();
    }

    @Test
    public void testCase6() throws Exception {
//        User librarian = modelManager.getUserById(1);
//        User p1 = modelManager.getUserByName("p1");
//        Document b1 = modelManager.getDocumentByName("b1");
//        User p3 = modelManager.getUserByName("p3");
//        Document b2 = modelManager.getDocumentByName("b2");
//        bookingManager.bookDocument(b1, p1, false);
//        bookingManager.bookDocument(b1, p3, false);
//        bookingManager.bookDocument(b2, p1, false);
//        bookingManager.checkOutDocument(b1, p1, librarian);
//        bookingManager.checkOutDocument(b1, p3, librarian);
//        bookingManager.checkOutDocument(b2, p1, librarian);
//        if(p1.getDocuments().contains(b1) &&
//                p3.getDocuments().contains(b1) &&
//                p1.getDocuments().)
    }

    private int getDocumentsNumber() {
        int copies = 0;
        Document document;
        ListIterator<Document> iterator = modelManager.getAllDocuments().listIterator();
        while (iterator.hasNext()) {
            document = iterator.next();
            copies = copies + document.getCopies();
        }
        return copies;
    }
}