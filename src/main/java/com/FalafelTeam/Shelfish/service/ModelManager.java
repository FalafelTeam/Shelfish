package com.FalafelTeam.Shelfish.service;

import com.FalafelTeam.Shelfish.model.*;
import com.FalafelTeam.Shelfish.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ModelManager {


    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentUserRepository documentUserRepository;
    @Autowired
    private EditorRepository editorRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
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
}
