package com.FalafelTeam.Shelfish.controller;

import com.FalafelTeam.Shelfish.model.Document;
import com.FalafelTeam.Shelfish.model.User;

import java.util.ArrayList;
import java.util.Date;

public interface ReturnSystemInterface {

    // return document
    void returnDocument(Document document);

    // get all checked out documents of the user
    ArrayList<Document> getAllCheckedOutDocuments(User user);

    // get the date when the document is due
    Date getDueDate(Document document);

    // get all overdue documents of the user
    ArrayList<Document> getAllOverdueDocuments(User user);
}
