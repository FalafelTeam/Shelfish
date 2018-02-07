package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.method.P;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class that represents a relation between user and document
 */
@Entity
public class DocumentUser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @OneToOne
    @JoinColumn
    @Getter Document document;
    @OneToOne
    @JoinColumn
    @Getter User user;
    @Getter Date date;
    @Getter @Setter Date dueDate;
    public DateFormat dateFormat;
    @Getter String status; // "new" (not taken) / "taken" / "outstanding" / "renewed"

    public DocumentUser() {
    }

    public DocumentUser(Document document, User user, Date date, Boolean isOutstanding) {
        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        this.document = document;
        this.user=user;
        this.date = new Date();
        if (isOutstanding) {
            this.status = "outstanding";
        } else {
            this.status = "new";
        }
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
