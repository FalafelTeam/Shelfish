package com.FalafelTeam.Shelfish;

import com.FalafelTeam.Shelfish.model.Author;
import com.FalafelTeam.Shelfish.model.Document;
import com.FalafelTeam.Shelfish.model.User;
import com.FalafelTeam.Shelfish.service.BookingSystemManager;
import com.FalafelTeam.Shelfish.service.ModelManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Delivery2Tests {

    @Autowired
    ModelManager modelManager;
    @Autowired
    BookingSystemManager bookingManager;
    private User librarian;

    public void initialState() {
        librarian = modelManager.addUser("User", "librarian", "123", "123", "123", "123");
    }

    @Test
    public void testCase1() throws Exception {
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
                null, null, null, "", "",false, 0, 1, false);
        List<String> avAuthor1 = new LinkedList<>();
        avAuthor1.add("Claude Shannon");
        Document av2 = modelManager.addDocument("Information Entropy", "avmaterial", avAuthor1, null,
                null, null, null, null, "",false, 0, 1, false);
        User p1 = modelManager.addUser("Sergey Afonso", "faculty", "123", "123", "30001", "Via Margutta, 3");
        User p2 = modelManager.addUser("Nadia Teixeira", "student", "234", "123", " 30002", "Via Sacra, 13");
        User p3 = modelManager.addUser("Elvira Espindola", "student", "345", "123", ": 30003", ": Via del Corso, 22");
        modelManager.findUserById(1).setId(1010);
        modelManager.findUserById(2).setId(1011);
        modelManager.findUserById(3).setId(1100);
    }
}
