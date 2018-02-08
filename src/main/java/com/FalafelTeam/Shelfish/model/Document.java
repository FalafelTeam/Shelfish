package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

/**
 * class that represents a document
 */
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter private Integer id;
    @Getter @Setter private String name;
    @Getter @Setter private String description;
    @Getter @Setter private Integer copies;
    @Getter @Setter private Integer edition;
    @Getter @Setter private Date publicationDate;
    @Getter @Setter private Boolean isBestseller;
    @ElementCollection
    @Getter private List<DocumentUser> queue;
    @ManyToOne
    @Getter @Setter private Publisher publisher;
    @ManyToOne
    @Getter @Setter private Editor editor;
    @ElementCollection
    @Getter private List<Author> authors;
    @ElementCollection
    @Getter private List<DocumentUser> takenBy;
    @Getter @Setter private Integer price;
    @Getter @Setter private Boolean isReference;
    @Getter @Setter private String type;
    @Getter @Setter private String image;

    public Document() {
        this.queue = new LinkedList<DocumentUser>();
        this.authors = new LinkedList<Author>();
        this.takenBy = new LinkedList<DocumentUser>();
    }

    public Document(String name, int price, boolean isReference, boolean isBestseller, int edition, Publisher publisher, ArrayList<Author> authors, String image){
        this.queue = new LinkedList<DocumentUser>();
        this.authors = new LinkedList<Author>();            // Book (type 0)
        this.takenBy = new LinkedList<DocumentUser>();
        this.name=name;
        this.price=price;
        this.isReference=isReference;
        this.isBestseller=isBestseller;
        this.publisher = publisher;
        for (int i = 0; i < authors.size(); i++) {
            this.addAuthor(authors.get(i));
        }
        this.edition = edition;
        this.type = "book";
        this.image = image;
    }

    public Document(String name, int price, boolean isReference, boolean isBestseller, int edition, Publisher publisher, Author author, String image) {
        this(name, price, isReference, isBestseller, edition, publisher, new ArrayList<Author>(), image);
        this.addAuthor(author);
    }

    public Document(String name, int price, boolean isReference, boolean isBestseller, Publisher publisher, Editor editor, String image){
        this.queue = new LinkedList<DocumentUser>();
        this.authors = new LinkedList<Author>();
        this.takenBy = new LinkedList<DocumentUser>();            // Article (Type 1)
        this.name=name;
        this.price=price;
        this.isReference=isReference;
        this.isBestseller=isBestseller;
        this.publisher = publisher;
        this.editor = editor;
        this.type = "article";
        this.image = image;
    }

    public Document(String name, int price, boolean isReference, boolean isBestseller, ArrayList<Author> authors, String image){
        this.queue = new LinkedList<DocumentUser>();
        this.authors = new LinkedList<Author>();
        this.takenBy = new LinkedList<DocumentUser>();            // AV Material (type 2)
        this.name=name;
        this.price=price;
        this.isReference=isReference;
        this.isBestseller=isBestseller;
        for (int i = 0; i < authors.size(); i++) {
            this.addAuthor(authors.get(i));
        }
        this.type = "avmaterial";
        this.image = image;
    }

    public void incrementcopies(){
        this.copies++;
    }

    public void decrementcopies() throws Exception {
        if (copies > 0) {
            this.copies--;
        } else throw new Exception("Cannot be less than one");
    }
    public void addToQueue(DocumentUser documentUser){
        queue.add(documentUser);
    }

    public Integer getQueueSize(){
        return queue.size();
    }

    public Boolean queueContains(DocumentUser documentUser) {
        ListIterator<DocumentUser> iterator = queue.listIterator();
        DocumentUser found;
        while (iterator.hasNext()) {
            found = iterator.next();
            if (found.getUser().getId().equals(documentUser.getUser().getId()) && found.getDocument().getId().equals(documentUser.getDocument().getId())) {
                return true;
            }
        }
        return false;
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }

    public Boolean athorsContain(Author author) {
        ListIterator<Author> iterator = authors.listIterator();
        Author found;
        while (iterator.hasNext()) {
            found = iterator.next();
            if (found.getId().equals(author.getId())) {
                return true;
            }
        }
        return false;
    }

    public Boolean takenByContains(DocumentUser documentUser) {
        ListIterator<DocumentUser> iterator = takenBy.listIterator();
        DocumentUser found;
        while (iterator.hasNext()) {
            found = iterator.next();
            if (found.getDocument().getId().equals(documentUser.getDocument().getId()) && found.getUser().getId().equals(documentUser.getUser().getId())) {
                return true;
            }
        }
        return false;
    }

    public Integer availableCopies() {
        return copies - takenBy.size();
    }
}

