package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * class that represents author
 */
@Entity
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter private Integer id;
    @Getter @Setter private String name;
    @ElementCollection
    @Getter private List<Document> documents;

    public Author() {
        documents = new ArrayList<>();
    }

    public Author(String name){
        this.name=name;
        documents = new ArrayList<>();
    }

    public void addDocument(Document document){
        documents.add(document);
    }

    public Integer numOfDocs(){
        return documents.size();
    }

    public Boolean documentsContain(Document document) {
        ListIterator<Document> iterator = documents.listIterator();
        Document found;
        while (iterator.hasNext()) {
            found = iterator.next();
            if (found.getId().equals(document.getId())) {
                return true;
            }
        }
        return false;
    }
}
