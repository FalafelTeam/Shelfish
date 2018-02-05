package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

/**
 * class that represents a publisher
 */
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter private Integer id;
    @Setter @Getter private String name;
    ArrayList<Document> documents;

    public Publisher(String name){
        this.name=name;
    }
}
