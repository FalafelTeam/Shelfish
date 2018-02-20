package com.FalafelTeam.Shelfish.service;

import com.FalafelTeam.Shelfish.model.*;
import com.FalafelTeam.Shelfish.repository.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class ModelManager {


    @Autowired
    @Getter
    private AuthorRepository authorRepository;
    @Autowired
    @Getter
    private DocumentRepository documentRepository;
    @Autowired
    @Getter
    private DocumentUserRepository documentUserRepository;
    @Autowired
    @Getter
    private EditorRepository editorRepository;
    @Autowired
    @Getter
    private PublisherRepository publisherRepository;
    @Autowired
    @Getter
    private UserRepository userRepository;

    public Author addAuthor(String name) {
        Author author = new Author(name);
        authorRepository.save(author);
        return author;
    }

    public Editor addEditor(String name) {
        Editor editor = new Editor(name);
        editorRepository.save(editor);
        return editor;
    }

    public Publisher addPublisher(String name) {
        Publisher publisher = new Publisher(name);
        publisherRepository.save(publisher);
        return publisher;
    }

    public User addUser(String name, String type, String login, String password, String phoneNumber, String address) {
        User user = new User(name, type, login, password, phoneNumber, address);
        userRepository.save(user);
        return user;
    }

    public Document addBook(String name, int copies, int price, boolean isReference, boolean isBestseller,
                            int edition, Publisher publisher, ArrayList<Author> authors, String image) {
        Document document = new Document(name, copies, price, isReference, isBestseller, edition, publisher, authors, image);
        documentRepository.save(document);
        publisher.getDocuments().add(document);
        publisherRepository.save(publisher);
        for (int i = 0; i < authors.size(); i++) {
            authors.get(i).getDocuments().add(document);
            authorRepository.save(authors.get(i));
        }
        return document;
    }

    public Document addBook(String name, int copies, int price, boolean isReference, boolean isBestseller,
                            int edition, Publisher publisher, Author author, String image) {
        Document document = new Document(name, copies, price, isReference, isBestseller, edition, publisher, author, image);
        documentRepository.save(document);
        publisher.getDocuments().add(document);
        publisherRepository.save(publisher);
        author.getDocuments().add(document);
        authorRepository.save(author);
        return document;
    }

    public Document addArticle(String name, int copies, int price, boolean isReference, boolean isBestseller,
                               Publisher publisher, Editor editor, String image) {
        Document document = new Document(name, copies, price, isReference, isBestseller, publisher, editor, image);
        documentRepository.save(document);
        publisher.getDocuments().add(document);
        publisherRepository.save(publisher);
        editor.getDocuments().add(document);
        editorRepository.save(editor);
        return document;
    }

    public Document addAVMaterial(String name, int copies, int price, boolean isReference, boolean isBestseller,
                                  ArrayList<Author> authors, String image) {
        Document document = new Document(name, copies, price, isReference, isBestseller, authors, image);
        documentRepository.save(document);
        for (int i = 0; i < authors.size(); i++) {
            authors.get(i).getDocuments().add(document);
            authorRepository.save(authors.get(i));
        }
        return document;
    }

    public void setCopies(Document document, int copies) {
        document.setCopies(copies);
        documentRepository.save(document);
    }

    public void editDocumentName(Document document, String newName){
        document.setName(newName);
        documentRepository.save(document);
    }

    public void editDocumentDescription(Document document, String newDescripton){
        document.setDescription(newDescripton);
        documentRepository.save(document);
    }

    public void editDocumentCopies(Document document, Integer newCopies){
        document.setCopies(newCopies);
        documentRepository.save(document);
    }

    public void editDocumentEdition(Document document, Integer newEdition){
        document.setEdition(newEdition);
        documentRepository.save(document);
    }

    public void editDocumentPublicationDate(Document document, Date newPublicationDate){
        document.setPublicationDate(newPublicationDate);
        documentRepository.save(document);
    }

    public void editDocumentIsBestseller(Document document, Boolean newIsBestseller){
        document.setIsBestseller(newIsBestseller);
        documentRepository.save(document);
    }

    public void editDocumentPublisher(Document document, Publisher newPublisher){
        document.setPublisher(newPublisher);
        documentRepository.save(document);
    }

    public void editDocumentEditor(Document document, Editor newEditor){
        document.setEditor(newEditor);
        documentRepository.save(document);
    }

    public void editDocumentPrice(Document document, Integer newPrice){
        document.setPrice(newPrice);
        documentRepository.save(document);
    }

    public void editDocumentIsReference(Document document, Boolean newIsReference){
        document.setIsReference(newIsReference);
        documentRepository.save(document);
    }

    public void editDocumentType(Document document, String newType){
        document.setType(newType);
        documentRepository.save(document);
    }

    public void editUserName(User user, String newName){
        user.setName(newName);
        userRepository.save(user);
    }

    public void editUserLogin(User user, String newLogin){
        user.setLogin(newLogin);
        userRepository.save(user);
    }

    public void editUserPassword(User user, String newPassword) throws Exception {
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    public void editUserAdress(User user, String newAdress){
        user.setAddress(newAdress);
        userRepository.save(user);
    }

    public void editUserPhoneNumber(User user, String newPhoneNumber){
        user.setPhoneNumber(newPhoneNumber);
        userRepository.save(user);
    }

    public void addCopiesToDocument(Document document, int plus){
        document.setCopies(document.getCopies()+plus);
        documentRepository.save(document);
    }

    public void deleteAllAuthors() {
        authorRepository.deleteAll();
    }

    public void deleteAllDocuments() {
        documentRepository.deleteAll();
    }

    public void deleteAllDocumentUsers() {
        documentUserRepository.deleteAll();
    }

    public void deleteAllEditors() {
        editorRepository.deleteAll();
    }

    public void deleteAllPublishers() {
        publisherRepository.deleteAll();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
