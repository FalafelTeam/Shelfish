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
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @Getter @Setter private Publisher publisher;
    @ManyToOne
    @JoinColumn(name = "editor_id")
    @Getter @Setter private Editor editor;
    @ManyToMany
    @JoinTable(name = "document_author", joinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    @Getter private List<Author> authors;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "document", cascade = {CascadeType.ALL})
    @Getter private List<DocumentUser> users;
    @Getter @Setter private Integer price;
    @Getter @Setter private Boolean isReference;
    @Getter @Setter private String type;
    @Getter @Setter private String image;

    public Document() {
        this.copies = 0;
        this.edition = 0;
        this.isBestseller = false;
        this.price = 0;
        this.isReference = false;
        this.authors = new LinkedList<Author>();
        this.users = new LinkedList<DocumentUser>();
    }

    public Document(String name, String description, int copies, int price, boolean isReference, boolean isBestseller,
                    int edition, Date publicationDate, Publisher publisher, List<Author> authors, String image){
        this.authors = new LinkedList<Author>();            // Book (type 0)
        this.users = new LinkedList<DocumentUser>();
        this.name=name;
        this.description = description;
        this.copies = copies;
        this.price=price;
        this.isReference=isReference;
        this.isBestseller=isBestseller;
        this.publisher = publisher;
        for (int i = 0; i < authors.size(); i++) {
            this.addAuthor(authors.get(i));
        }
        this.edition = edition;
        this.publicationDate = publicationDate;
        this.type = "book";
        this.image = image;
    }

    public Document(String name, String description, int copies, int price, boolean isReference, boolean isBestseller,
                    int edition, Date publicationDate, Publisher publisher, Author author, String image) {
        this(name, description, copies, price, isReference, isBestseller, edition, publicationDate, publisher,
                new ArrayList<Author>(), image);
        this.addAuthor(author);
    }

    public Document(String name, String description, int copies, int price, boolean isReference, boolean isBestseller,
                    Date publicationDate, Publisher publisher, Editor editor, String image){
        this.authors = new LinkedList<Author>();
        this.users = new LinkedList<DocumentUser>();            // Article (Type 1)
        this.name=name;
        this.description = description;
        this.copies = copies;
        this.price=price;
        this.isReference=isReference;
        this.isBestseller=isBestseller;
        this.publisher = publisher;
        this.editor = editor;
        this.publicationDate = publicationDate;
        this.type = "article";
        this.image = image;
    }

    public Document(String name, String description, int copies, int price, boolean isReference, boolean isBestseller,
                    Date publicationDate, List<Author> authors, String image){
        this.authors = new LinkedList<Author>();
        this.users = new LinkedList<DocumentUser>();            // AV Material (type 2)
        this.name=name;
        this.description = description;
        this.copies = copies;
        this.price=price;
        this.isReference=isReference;
        this.isBestseller=isBestseller;
        for (int i = 0; i < authors.size(); i++) {
            this.addAuthor(authors.get(i));
        }
        this.publicationDate = publicationDate;
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
        users.add(documentUser);
    }

    public Boolean queueContains(DocumentUser documentUser) {
        ListIterator<DocumentUser> iterator = users.listIterator();
        DocumentUser found;
        while (iterator.hasNext()) {
            found = iterator.next();
            if (found.getStatus().equals("new")) {
                if (found.getUser().getId().equals(documentUser.getUser().getId()) &&
                        found.getDocument().getId().equals(documentUser.getDocument().getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }

    public Boolean authorsContain(Author author) {
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
        ListIterator<DocumentUser> iterator = users.listIterator();
        DocumentUser found;
        while (iterator.hasNext()) {
            found = iterator.next();
            if (found.getStatus().equals("taken")) {
                if (found.getDocument().getId().equals(documentUser.getDocument().getId()) &&
                        found.getUser().getId().equals(documentUser.getUser().getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Integer takenBySize() {
        int num = 0;
        ListIterator<DocumentUser> iterator = users.listIterator();
        DocumentUser found;
        while (iterator.hasNext()) {
            found = iterator.next();
            if (found.getStatus().equals("taken")) {
                num++;
            }
        }
        return num;
    }

    public Integer availableCopies() {
        return copies - this.takenBySize();
    }
}

