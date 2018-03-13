package com.FalafelTeam.Shelfish.controller;

import com.FalafelTeam.Shelfish.model.Author;
import com.FalafelTeam.Shelfish.model.Document;
import com.FalafelTeam.Shelfish.service.BookingSystemManager;
import com.FalafelTeam.Shelfish.service.ModelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;




@Controller
public class LibraryController {
    @Autowired
    ModelManager modelManager;
    @Autowired
    BookingSystemManager bookingManager;



    @RequestMapping(value = {"/library"}, method = RequestMethod.GET)
    public String library(Model model) throws Exception {

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


        List<doc> documents = new ArrayList<doc>();
        Document document = modelManager.getDocumentById(1);
        doc doc1 = new doc();
        doc1.name = document.getName();
        doc1.dat = document.getPublicationDate().toString();
        List<Author> authorNames3 = document.getAuthors();
        String str = "";
        for (int i = 0; i < authorNames3.size(); i++) {
            if(i == authorNames3.size() - 1){
                str = str + authorNames3.get(i).getName();
            }
            else{
                str = str + authorNames3.get(i).getName() + ", ";
            }
        }
        doc1.authorNames = str;
        doc1.publisherName = document.getPublisher().getName();
        doc1.type = document.getType();
        doc1.copies = document.getCopies();

        documents.add(doc1);

        Document document2 = b2;
        doc doc2 = new doc();
        doc2.name = document2.getName();
        doc2.dat = document2.getPublicationDate().toString();
        List<Author> authorNames4 = document2.getAuthors();
        str = "";
        for (int i = 0; i < authorNames4.size(); i++) {
            if(i == authorNames4.size() - 1){
                str = str + authorNames4.get(i).getName();
            }
            else{
                str = str + authorNames4.get(i).getName() + ", ";
            }
        }
        doc2.authorNames = str;
        doc2.publisherName = document2.getPublisher().getName();
        doc2.type = document2.getType();
        doc2.copies = document2.getCopies();

        documents.add(doc2);
        model.addAttribute("documents", documents);
        return "library";
    }
}
class doc {
    public String name = "";
    public String type = "";
    public String authorNames = "";
    public String publisherName = "";
    public String editorName = "";
    public int edition = 1;
    public String dat = "";
    public int copies = 0;
}




