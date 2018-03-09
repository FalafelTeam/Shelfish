package com.FalafelTeam.Shelfish.model;

import lombok.Getter;
import lombok.Setter;

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
    @Getter private Integer id;
    @ManyToOne
    @JoinColumn(name = "document_id")
    @Getter private Document document;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter private User user;
    @Getter @Setter private Date date;
    @Getter @Setter private Integer weekNum;
    @Getter private String status; // "new" (not taken) / "taken" / "outstanding" / "renewed"

    public DocumentUser() {
    }

    public DocumentUser(Document document, User user, Date date, Boolean isOutstanding) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
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

    @PreRemove
    private void preRemove() {
        this.document.getUsers().remove(this);
        this.document = null;
        this.user.getDocuments().remove(this);
        this.user = null;
    }
}
