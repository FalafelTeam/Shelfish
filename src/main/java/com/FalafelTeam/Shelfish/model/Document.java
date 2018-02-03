package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;


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
    private LinkedList<User> queue;
    @ManyToOne
    @Getter @Setter private Publisher publisher;
    @ManyToOne
    @Getter @Setter private Editor editor;
    private ArrayList<Author> authors;
    public ArrayList<User> takenBy;
    @Getter @Setter private int price;
    @Getter @Setter private boolean isReference;
    @ManyToOne
    @Getter @Setter Type type;

    public Document(String name, int price, boolean isReference, boolean isBestseller, int edition, Author author){
        queue = new LinkedList<>();
        authors = new ArrayList<>();            // Книга (type 0)
        takenBy = new ArrayList<>();
        this.name=name;
        this.price=price;
        this.isReference=isReference;
        this.isBestseller=isBestseller;
        this.authors.add(author);
        this.edition = edition;
        this.type=new Type("book");
    }
    public Document(String name, int price, boolean isReference, boolean isBestseller, Publisher publisher){
        queue = new LinkedList<>();
        authors = new ArrayList<>();
        takenBy = new ArrayList<>();            // Статья (Type 1)
        this.name=name;
        this.price=price;
        this.isReference=isReference;
        this.isBestseller=isBestseller;
        this.publisher = publisher;
        this.type=new Type("article");
    }
    public Document(String name, int price, boolean isReference, boolean isBestseller, Editor editor){
        queue = new LinkedList<>();
        authors = new ArrayList<>();
        takenBy = new ArrayList<>();            // Журнал (type 2)
        this.name=name;
        this.price=price;
        this.isReference=isReference;
        this.isBestseller=isBestseller;
        this.editor = editor;
        this.type=new Type("journal");
    }

    public void incrementcopies(){
        this.copies++;
    }

    public void decrementcopies() throws Exception {
        if (copies > 0) {
            this.copies--;
        } else throw new Exception("Cannot be less than one");
    }

    public void addToQueue(User user){
        queue.add(user);
    }

    public void removeFromQueue() throws Exception { //удаляет без возвращения юзера
        if(queue.size()>0) {
            queue.remove();
        }
        else throw new Exception("No users in a queue");
    }

    public User returnFromQueue() throws Exception { //удаляет с возвращением юзера
        if(queue.size()>0) {
            return queue.remove();
        }
        else throw new Exception("No users in a queue");
    }

    public User peekQueue(){
        return queue.peek();
    }

    public int getQueueSize(){
        return queue.size();
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }

}

