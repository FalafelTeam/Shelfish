package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

/**
 * class that represents author
 */
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter private Integer id;
    @Getter @Setter private String name;
    private ArrayList<Document> documents;

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
