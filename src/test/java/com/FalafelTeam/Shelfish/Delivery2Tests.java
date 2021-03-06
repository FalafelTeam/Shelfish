package com.FalafelTeam.Shelfish;

import com.FalafelTeam.Shelfish.model.Document;
import com.FalafelTeam.Shelfish.model.DocumentUser;
import com.FalafelTeam.Shelfish.model.User;
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

    public void initialState() throws Exception {
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
    }

    @Test
    public void testCase1() throws Exception {
        initialState();
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

        if (!p1.getName().equals("Sergey Afonso")) {
            throw new Exception("Wrong name of p1");
        }
        if (!p1.getAddress().equals("Via Margutta, 3")) {
            throw new Exception("Wrong address of p1");
        }
        if (!p1.getPhoneNumber().equals("30001")) {
            throw new Exception("Wrong phone number of p1");
        }
        if (p1.getId() != 2) {
            throw new Exception("Wrong id of p1");
        }
        if (!p1.getType().equals("faculty")) {
            throw new Exception("Wrong type of p1");
        }
        if (p1.getDocuments().size() != 0) {
            throw new Exception("Wrong documents of p1");
        }

        if (!p3.getName().equals("Elvira Espindola")) {
            throw new Exception("Wrong name of p3");
        }
        if (!p3.getAddress().equals("Via del Corso, 22")) {
            throw new Exception("Wrong address of p3");
        }
        if (!p3.getPhoneNumber().equals("30003")) {
            throw new Exception("Wrong phone number of p3");
        }
        if (p3.getId() != 4) {
            throw new Exception("Wrong id of p3");
        }
        if (!p3.getType().equals("student")) {
            throw new Exception("Wrong type of p3");
        }
        if (p3.getDocuments().size() != 0) {
            throw new Exception("Wrong documents of p3");
        }
    }

    @Test
    public void testCase4() throws Exception {
        try {
            User p2 = modelManager.getUserById(3);
        } catch (Exception e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
        User p3 = modelManager.getUserById(4);
        if (!p3.getName().equals("Elvira Espindola")) {
            throw new Exception("Wrong name of p3");
        }
        if (!p3.getAddress().equals("Via del Corso, 22")) {
            throw new Exception("Wrong address of p3");
        }
        if (!p3.getPhoneNumber().equals("30003")) {
            throw new Exception("Wrong phone number of p3");
        }
        if (p3.getId() != 4) {
            throw new Exception("Wrong id of p3");
        }
        if (!p3.getType().equals("student")) {
            throw new Exception("Wrong type of p3");
        }
        if (p3.getDocuments().size() != 0) {
            throw new Exception("Wrong documents of p3");
        }
    }

    @Test
    public void testCase5() throws Exception {
        Document b1 = modelManager.getDocumentById(1);
        try {
            User p2 = modelManager.getUserById(3);
            bookingManager.bookDocument(b1, p2, 2, false);
            bookingManager.checkOutDocument(b1, p2, modelManager.getUserById(1));
        } catch (Exception e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    @Test
    public void testCase6() throws Exception {
        initialState();

        User librarian = modelManager.getUserById(1);
        User p1 = modelManager.getUserById(2);
        Document b1 = modelManager.getDocumentById(1);
        User p3 = modelManager.getUserById(4);
        Document b2 = modelManager.getDocumentById(2);
        bookingManager.bookDocument(b1, p1, false);
        bookingManager.bookDocument(b1, p3, false);
        bookingManager.bookDocument(b2, p1, false);
        bookingManager.checkOutDocument(b1, p1, librarian);
        bookingManager.checkOutDocument(b1, p3, librarian);
        bookingManager.checkOutDocument(b2, p1, librarian);
    }

    @Test
    public void testCase7() throws Exception {
        initialState();

        User librarian = modelManager.getUserById(5);
        User p1 = modelManager.getUserById(6);
        Document b1 = modelManager.getDocumentById(6);
        Document b2 = modelManager.getDocumentById(7);
        Document b3 = modelManager.getDocumentById(8);
        Document av1 = modelManager.getDocumentById(9);
        bookingManager.bookDocument(b1, p1, false);
        bookingManager.checkOutDocument(b1, p1, librarian);
        bookingManager.bookDocument(b2, p1, false);
        bookingManager.checkOutDocument(b2, p1, librarian);
        try {
            bookingManager.bookDocument(b3, p1, false);
            bookingManager.checkOutDocument(b3, p1, librarian);
        } catch (Exception e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
        bookingManager.bookDocument(av1, p1, false);
        bookingManager.checkOutDocument(av1, p1, librarian);

        User p2 = modelManager.getUserById(7);
        Document av2 = modelManager.getDocumentById(10);
        bookingManager.bookDocument(b1, p2, false);
        bookingManager.checkOutDocument(b1, p2, librarian);
        bookingManager.bookDocument(b2, p2, false);
        bookingManager.checkOutDocument(b2, p2, librarian);
        bookingManager.bookDocument(av2, p2, false);
        bookingManager.checkOutDocument(av2, p2, librarian);
        boolean ok = true;
        List<DocumentUser> documentUsers = p1.getDocuments();
        for (int i = 0; i < documentUsers.size(); i++) {
            if (!documentUsers.get(i).getDocument().getName().equals(b1.getName())) {
                ok = false;
            }
        }
    }

    // the following test uses old functionality
    /*@Test
    public void testCase8() throws Exception {
        initialState();

        User librarian = modelManager.getUserById(9);
        User p1 = modelManager.getUserById(10);
        Document b1 = modelManager.getDocumentById(11);
        User p2 = modelManager.getUserById(11);
        Document b2 =  modelManager.getDocumentById(12);
        Document av1 = modelManager.getDocumentById(14);

        bookingManager.bookDocument(b1, p1, null, false, new Date(2018, 2, 9));
        bookingManager.checkOutDocument(b1, p1, librarian);
        bookingManager.bookDocument(b2, p1, null, false, new Date(2018, 2, 2));
        bookingManager.checkOutDocument(b2, p1, librarian);
        bookingManager.bookDocument(b1, p2, null, false, new Date(2018, 2, 5));
        bookingManager.checkOutDocument(b1, p2, librarian);
        bookingManager.bookDocument(av1, p2, null, false, new Date(2018, 2, 17));
        bookingManager.checkOutDocument(av1, p2, librarian);
    }*/

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