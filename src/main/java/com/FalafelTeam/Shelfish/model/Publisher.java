package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class that represents a publisher
 */
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter private Integer id;
    @Setter @Getter private String name;
    @ElementCollection
    List<Document> documents;

    public Publisher() {
        this.documents = new ArrayList<Document>();
    }

    public Publisher(String name){
        documents = new ArrayList<Document>();
        this.name=name;
    }
}
