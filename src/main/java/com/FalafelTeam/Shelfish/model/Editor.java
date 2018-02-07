package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * class that represents an editor
 */
@Entity
public class Editor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter private Integer id;
    @Getter @Setter private String name;
    ArrayList<Document> documents;

    public Editor() {
        documents = new ArrayList<Document>();
    }

    public Editor(String name){
        documents = new ArrayList<Document>();
        this.name=name;
    }
}
