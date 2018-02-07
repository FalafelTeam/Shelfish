package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private List<Document> documents;

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
    public int numOfDocs(){
        return documents.size();
    }
}
