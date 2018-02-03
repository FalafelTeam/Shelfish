package com.FalafelTeam.Shelfish.controller;

import com.FalafelTeam.Shelfish.model.*;

import java.util.ArrayList;
import java.util.Date;

public interface BookingSystemInterface {

    // sign up a user
    void signUp(String name, String login, String password, String phoneNumber, String address);

    // add document
    void addDocument(String name, String description, Integer copies, Integer edition, Date publicationDate, boolean isBestseller, Publisher publisher, Editor editor, ArrayList<Author> authors, Integer price, Type type, boolean isReference);

    // get all authors
    ArrayList<Author> getAllAuthors();

    // get all documents
    ArrayList<Document> getAllDocuments();

    // get all editors
    ArrayList<Editor> getAllEditors();

    // get all publishers
    ArrayList<Publisher> getAllPublishers();

    // get all Types
    ArrayList<Type> getAllTypes();

    // get all users
    ArrayList<User> getAllUsers();

    // modify user
    void modifyUser(User user, String login, String phoneNumber, String address);

    // modify a document
    void modifyDocument(Document document, String name);

    // delete user
    void deleteUser(User user);
    void deleteUser(Integer id);

    // delete document
    void deleteDocument(Document document);
    void deleteDocument(Integer id);

    // check out a document
    void checkOutDocument(Document document, User user);

    // search for a document by its name
    ArrayList<Document> searchDocumentByName(String name);

    // search for a document by its type
    ArrayList<Document> searchDocumentByType(String type);

    // search for a document by its edition
    ArrayList<Document> searchDocumentByEdition(Integer edition);

    // search for a document by its publisher
    ArrayList<Document> searchDocumentByPublisher(Publisher publisher);

    // search for a document by its editor
    ArrayList<Document> searchDocumentByEditor(Editor editor);

    // search for a document by its publication date
    ArrayList<Document> searchDocumentByPublicationDate(Date date);

    // search for a document by its author
    ArrayList<Document> searchDocumentByAuthor(Author author);

    // search for an author by its name
    Author searchAuthorByName();

    // search for an editor by its name
    Editor searshEditorByName();

    // search for a publisher by its name
    Publisher searchPublisherByName();

    // refine search

}
