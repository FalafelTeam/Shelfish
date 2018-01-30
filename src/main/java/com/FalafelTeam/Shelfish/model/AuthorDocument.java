package com.FalafelTeam.Shelfish.model;

import javax.persistence.Entity;

@Entity
public class AuthorDocument {
    private int authorId;
    private int documentId;

    public int getAuthorId() {
        return authorId;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }
}
