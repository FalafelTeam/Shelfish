package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

/**
 * class that represents a type
 */
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter int id;
    @Getter @Setter String type;
    ArrayList<Document> documents;

    public Type(String type){
        this.type = type;
    }
}
