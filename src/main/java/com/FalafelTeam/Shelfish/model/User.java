package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 * class that represents a user
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter private Integer id;
    @Getter @Setter private String name;
    @Getter private String type;
    @Getter @Setter private String login;
    @Getter private String password;
    @Getter @Setter private String address;
    @Getter @Setter private String phoneNumber;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = {CascadeType.ALL})
    @Getter private List<DocumentUser> documents;

    public User() {
        this.documents = new ArrayList<DocumentUser>();
    }

    public User(String name, String type, String login, String password, String phoneNumber, String address){
        this.name=name;
        this.type=type;
        this.login=login;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.documents = new ArrayList<DocumentUser>();
    }

    public void setType(String type) throws Exception {
        if(Objects.equals(type, "student") || Objects.equals(type, "faculty") || Objects.equals(type, "librarian")){
            this.type=type;
        }
        else{
            throw new Exception("Invalid status of user");
        }
    }

    public void setPassword(String password) throws Exception {
        if(password.length()>=6){
            this.password=password;
        }
        else{
            throw new Exception("Too short password");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber; //надо бы добавить проверочку на то, что введенная строка состоит только из цифр и возможно плюса
    }

    public Boolean documentsContain(DocumentUser documentUser) {
        ListIterator<DocumentUser> iterator = documents.listIterator();
        DocumentUser found;
        while (iterator.hasNext()) {
            found = iterator.next();
            if (found.getDocument().getId().equals(documentUser.getDocument().getId()) && found.getUser().getId().equals(documentUser.getUser().getId())) {
                return true;
            }
        }
        return false;
    }
}