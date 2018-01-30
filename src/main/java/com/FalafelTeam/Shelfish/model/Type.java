package com.FalafelTeam.Shelfish.model;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Type {
    String type;
    ArrayList<Document> documents;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
