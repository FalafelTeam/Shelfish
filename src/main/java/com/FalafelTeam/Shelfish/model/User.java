package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Objects;

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
    @Getter private String phoneNumber;
    ArrayList<Document> documents;

    public User(String name, String type, String login, String password, String phoneNumber, String address){
        this.name=name;
        this.type=type;
        this.login=login;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.address=address;
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
}