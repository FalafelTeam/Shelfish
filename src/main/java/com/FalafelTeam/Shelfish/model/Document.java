package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

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
    @Getter @Setter private int copies;
    @Getter @Setter private int edition;
    @Getter @Setter private Date publicationDate;
    @Getter @Setter private boolean isBestseller;
    private LinkedList<DocumentUser> queue;
    @ManyToOne
    @Getter @Setter private Publisher publisher;
    @ManyToOne
    @Getter @Setter private Editor editor;
    private LinkedList<Author> authors;
    public LinkedList<DocumentUser> takenBy;
    @Getter @Setter private int price;
    @Getter @Setter private boolean isReference;
    @Getter @Setter String type;

    public Document() {
        this.queue = new LinkedList<>();
        this.authors = new LinkedList<>();
        this.takenBy = new LinkedList<>();
    }

    public Document(String name, int price, boolean isReference, boolean isBestseller, int edition, Publisher publisher, ArrayList<Author> authors){
        this.queue = new LinkedList<>();
        this.authors = new LinkedList<>();            // Book (type 0)
        this.takenBy = new LinkedList<>();
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
    }

    public Document(String name, int price, boolean isReference, boolean isBestseller, int edition, Publisher publisher, Author author) {
        this(name, price, isReference, isBestseller, edition, publisher, new ArrayList<Author>());
        this.addAuthor(author);
    }

    public Document(String name, int price, boolean isReference, boolean isBestseller, Publisher publisher, Editor editor){
        this.queue = new LinkedList<>();
        this.authors = new LinkedList<>();
        this.takenBy = new LinkedList<>();            // Article (Type 1)
        this.name=name;
        this.price=price;
        this.isReference=isReference;
        this.isBestseller=isBestseller;
        this.publisher = publisher;
        this.editor = editor;
        this.type = "article";
    }

    public Document(String name, int price, boolean isReference, boolean isBestseller, ArrayList<Author> authors){
        this.queue = new LinkedList<>();
        this.authors = new LinkedList<>();
        this.takenBy = new LinkedList<>();            // AV Material (type 2)
        this.name=name;
        this.price=price;
        this.isReference=isReference;
        this.isBestseller=isBestseller;
        for (int i = 0; i < authors.size(); i++) {
            this.addAuthor(authors.get(i));
        }
        this.type = "avmaterial";
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

    public LinkedList<DocumentUser> getQueue() {
        return queue;
    }

    public DocumentUser returnFromQueue() throws Exception {
        if(queue.size()>0) {
            return queue.remove();
        }
        else throw new Exception("No users in a queue");
    }

    public DocumentUser peekQueue(){
        return queue.peek();
    }

    public int getQueueSize(){
        return queue.size();
    }

    public LinkedList<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }

    public int availableCopies() {
        return copies - takenBy.size();
    }
}

