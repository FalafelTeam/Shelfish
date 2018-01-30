package com.FalafelTeam.Shelfish.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private String login;
    private String password;

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getType(){
        return this.type;
    }
    public void setType(String type) throws Exception {
        if(Objects.equals(type, "student") || Objects.equals(type, "faculty") || Objects.equals(type, "librarian")){
            this.type=type;
        }
        else{
            throw new Exception("Invalid status of user");
        }
    }
    public String getLogin(){
        return this.login;
    }
    public void setLogin(String login){
        this.login=login;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password) throws Exception {
        if(password.length()>=6){
            this.password=password;
        }
        else{
            throw new Exception("Too short password");
        }
    }
}
