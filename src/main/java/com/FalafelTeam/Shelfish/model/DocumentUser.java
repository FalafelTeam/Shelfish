package com.FalafelTeam.Shelfish.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class DocumentUser {
    private int userId;
    private int documentId;
    private Date date;
    private String status;

    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId=userId;
    }
    public int getDocumentId(){
        return documentId;
    }
    public void setDocumentId(int documentId){
        this.documentId=documentId;
    }
    public Date getDate(){
        return date;
    }
    public void setDate(Date date){
        this.date=date;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
}
