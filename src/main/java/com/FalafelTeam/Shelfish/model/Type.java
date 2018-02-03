package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

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

    // тут особая стратегия. Нужно, чтобы этим классом пользовались, по сути, только когда хотят добавить новый тип документа. Где-то в программе нужно
    // создать сразу три базовых типа документа.
    // id 0 - book
    // id 1 - article
    // id 2 - journal
}
