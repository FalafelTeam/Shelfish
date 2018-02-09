package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * class that represents a publisher
 */
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter private Integer id;
    @Setter @Getter private String name;
    @OneToMany
    @Getter private List<Document> documents;

    public Publisher() {
        this.documents = new ArrayList<Document>();
    }

    public Publisher(String name){
        documents = new ArrayList<Document>();
        this.name=name;
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
