package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * class that represents an editor
 */
@Entity
public class Editor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter private Integer id;
    @Getter @Setter private String name;
    @OneToMany(mappedBy = "editor", cascade = {CascadeType.ALL})
    @Getter private List<Document> documents;

    public Editor() {
        documents = new ArrayList<Document>();
    }

    public Editor(String name){
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
