package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DocumentUser {
    @Getter Document document;
    @Getter User user;
    @Getter Date date;
    @Getter @Setter Date dueDate;  // оставил сеттер чтобы регулировать в зависимости от того, кто берет
    public DateFormat dateFormat;
    @Getter String status; // new (not taken) / taken / outstanding / renewed

    public DocumentUser(Document document, User user, Date date, String Status){
        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.document = document;
        this.user=user;
        this.date = new Date();  // сразу ставит сегодняшнюю дату
    }

    public void setStatus(String status) throws Exception {
        if(status.equals("new") || status.equals("taken") || status.equals("renewed") || status.equals("outstanding")){
            this.status = status;
        }
        else{
            throw new Exception("Wrong status");
        }
    }
}
