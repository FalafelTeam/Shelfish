package com.FalafelTeam.Shelfish.service;

import com.FalafelTeam.Shelfish.model.*;
import com.FalafelTeam.Shelfish.repository.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    // Add methods:

    public Document addDocument(String name, String type, List<String> authorNames, String publisherName,
                                String editorName, Integer edition, Date publicationDate, String image,
                                String description, Boolean isBestseller, Integer price, Integer copies,
                                Boolean isReference) throws Exception {
        Document document = null;
        switch (type) {
            case "book" :
                document = addBook(name, authorNames, publisherName, edition, publicationDate, image, description,
                        isBestseller, price, copies, isReference);
                break;
            case "article" :
                document = addArticle(name, publisherName, editorName, publicationDate, image, description,
                        isBestseller, price, copies, isReference);
                break;
            case "avmaterial" :
                document = addAVMaterial(name, authorNames, publicationDate, image, description, isBestseller, price,
                        copies, isReference);
                break;
            default:
                throw new Exception("Wrong document type");
        }
        return document;
    }

    public User addUser(String name, String type, String login, String password, String phoneNumber, String address) {
        User user = new User(name, type, login, password, phoneNumber, address);
        userRepository.save(user);
        return user;
    }

    private Author addAuthor(String name) {
        Author author = new Author(name);
        authorRepository.save(author);
        return author;
    }

    private Document addBook(String name, List<String> authorNames, String publisherName, Integer edition, Date publicationDate,
                             String image, String description, Boolean isBestseller, Integer price, Integer copies,
                             Boolean isReference) {
        Publisher publisher = publisherRepository.findByName(publisherName);
        if (publisher == null) {
            publisher = addPublisher(publisherName);
        }
        List<Author> authors = new LinkedList<>();
        ListIterator<String> authorNamesIterator = authorNames.listIterator();
        String currA;
        Author author;
        while (authorNamesIterator.hasNext()) {
            currA = authorNamesIterator.next();
            author = authorRepository.findByName(currA);
            if (author == null) {
                author = addAuthor(currA);
            }
            authors.add(author);
        }
        Document document = new Document(name, description, copies, price, isReference, isBestseller, edition,
                publicationDate, publisher, authors, image);
        documentRepository.save(document);
        publisher.getDocuments().add(document);
        publisherRepository.save(publisher);
        ListIterator<Author> authorsIterator = authors.listIterator();
        while (authorsIterator.hasNext()) {
            author = authorsIterator.next();
            author.getDocuments().add(document);
            authorRepository.save(author);
        }
        return document;
    }

    private Document addArticle(String name, String publisherName, String editorName, Date publicationDate, String image,
                                String description, Boolean isBestseller, Integer price, Integer copies,
                                Boolean isReference) {
        Publisher publisher = publisherRepository.findByName(publisherName);
        if (publisher == null) {
            publisher = addPublisher(publisherName);
        }
        Editor editor = editorRepository.findByName(editorName);
        if (editor == null) {
            editor = addEditor(editorName);
        }
        Document document = new Document(name, description, copies, price, isReference, isBestseller, publicationDate,
                publisher, editor, image);
        documentRepository.save(document);
        publisher.getDocuments().add(document);
        publisherRepository.save(publisher);
        editor.getDocuments().add(document);
        editorRepository.save(editor);
        return document;
    }

    private Document addAVMaterial(String name, List<String> authorNames, Date publicationDate, String image,
                                   String description, Boolean isBestseller, Integer price, Integer copies,
                                   Boolean isReference) {
        List<Author> authors = new LinkedList<>();
        ListIterator<String> authorNamesIterator = authorNames.listIterator();
        String currA;
        Author author;
        while (authorNamesIterator.hasNext()) {
            currA = authorNamesIterator.next();
            author = authorRepository.findByName(currA);
            if (author == null) {
                author = addAuthor(currA);
            }
            authors.add(author);
        }
        Document document = new Document(name, description, copies, price, isReference, isBestseller, publicationDate,
                authors, image);
        documentRepository.save(document);
        ListIterator<Author> authorsIterator = authors.listIterator();
        while (authorsIterator.hasNext()) {
            author = authorsIterator.next();
            author.getDocuments().add(document);
            authorRepository.save(author);
        }
        return document;
    }

    private Editor addEditor(String name) {
        Editor editor = new Editor(name);
        editorRepository.save(editor);
        return editor;
    }

    private Publisher addPublisher(String name) {
        Publisher publisher = new Publisher(name);
        publisherRepository.save(publisher);
        return publisher;
    }

    // Modify methods:
    // modify stated attributes, do not modify attributes associated with null parameters

    public void modifyDocument(Document document, String name, String description, Integer copies, Integer edition,
                               Date publicationDate, Boolean isBestseller, String publisherName, String editorName,
                               List<String> authorNames, Integer price, Boolean isReference, String image) {
        switch (document.getType()) {
            case "book" :
                document = modifyBook(document, name, description, copies, edition, publicationDate, isBestseller,
                        publisherName, authorNames, price, isReference, image);
                break;
            case "article" :
                document = modifyArticle(document, name, description, copies, publicationDate, isBestseller,
                        publisherName, editorName, price, isReference, image);
                break;
            case "avmaterial" :
                document = modifyAVMaterial(document, name, description, copies, publicationDate, isBestseller,
                        authorNames, price, isReference, image);
        }
        documentRepository.save(document);
    }

    private Document modifyBook(Document document, String name, String description, Integer copies, Integer edition,
                                Date publicationDate, Boolean isBestseller, String publisherName,
                                List<String> authorNames, Integer price, Boolean isReference, String image) {
        if (name != null) {
            document.setName(name);
        }
        if (description != null) {
            document.setDescription(description);
        }
        if (copies != null) {
            document.setCopies(copies);
        }
        if (edition != null) {
            document.setEdition(edition);
        }
        if (publicationDate != null) {
            document.setPublicationDate(publicationDate);
        }
        if (isBestseller != null) {
            document.setIsBestseller(isBestseller);
        }
        setPublisher(document, publisherName);
        setAuthors(document, authorNames);
        if (price != null) {
            document.setPrice(price);
        }
        if (isReference != null) {
            document.setIsReference(isReference);
        }
        if (image != null) {
            document.setImage(image);
        }
        return document;
    }

    private Document modifyArticle(Document document, String name, String description, Integer copies,
                                   Date publicationDate, Boolean isBestseller, String publisherName, String editorName,
                                   Integer price, Boolean isReference, String image) {
        if (name != null) {
            document.setName(name);
        }
        if (description != null) {
            document.setDescription(description);
        }
        if (copies != null) {
            document.setCopies(copies);
        }
        if (publicationDate != null) {
            document.setPublicationDate(publicationDate);
        }
        if (isBestseller != null) {
            document.setIsBestseller(isBestseller);
        }
        setPublisher(document, publisherName);
        setEditor(document, editorName);
        if (price != null) {
            document.setPrice(price);
        }
        if (isReference != null) {
            document.setIsReference(isReference);
        }
        if (image != null) {
            document.setImage(image);
        }
        return document;
    }

    private Document modifyAVMaterial(Document document, String name, String description, Integer copies,
                                      Date publicationDate, Boolean isBestseller, List<String> authorNames,
                                      Integer price, Boolean isReference, String image) {
        if (name != null) {
            document.setName(name);
        }
        if (description != null) {
            document.setDescription(description);
        }
        if (copies != null) {
            document.setCopies(copies);
        }
        if (publicationDate != null) {
            document.setPublicationDate(publicationDate);
        }
        if (isBestseller != null) {
            document.setIsBestseller(isBestseller);
        }
        setAuthors(document, authorNames);
        if (price != null) {
            document.setPrice(price);
        }
        if (isReference != null) {
            document.setIsReference(isReference);
        }
        if (image != null) {
            document.setImage(image);
        }
        return document;
    }

    private void setAuthors(Document document, List<String> authorNames) {
        if (authorNames != null) {
            // remove authors
            ListIterator<Author> originalAuthorsIterator = document.getAuthors().listIterator();
            Author currOriginalA;
            while (originalAuthorsIterator.hasNext()) {
                currOriginalA = originalAuthorsIterator.next();
                if (!authorNames.contains(currOriginalA.getName())) {
                    currOriginalA.getDocuments().remove(document);
                    document.getAuthors().remove(currOriginalA);
                    authorRepository.save(currOriginalA);
                }
            }
            // add authors
            ListIterator<String> authorIterator = authorNames.listIterator();
            String currA;
            Author author;
            while (authorIterator.hasNext()) {
                currA = authorIterator.next();
                author = authorRepository.findByName(currA);
                if (author == null) {
                    author = addAuthor(currA);
                }
                if (!document.authorsContain(author)) {
                    document.getAuthors().add(author);
                }
            }
        }
    }

    private void setPublisher(Document document, String publisherName) {
        if (publisherName != null) {
            Publisher publisher;
            document.getPublisher().getDocuments().remove(document);
            if (document.getPublisher().getDocuments().size() == 0) {
                publisher = document.getPublisher();
                document.setPublisher(null);
                documentRepository.save(document);
                publisherRepository.delete(publisher);
            } else {
                publisherRepository.save(document.getPublisher());
            }
            publisher = publisherRepository.findByName(publisherName);
            if (publisher == null) {
                publisher = addPublisher(publisherName);
            }
            document.setPublisher(publisher);
        }
    }

    private void setEditor(Document document, String editorName) {
        if (editorName != null) {
            Editor editor;
            document.getEditor().getDocuments().remove(document);
            if (document.getEditor().getDocuments().size() == 0) {
                editor = document.getEditor();
                document.setEditor(null);
                documentRepository.save(document);
                editorRepository.delete(editor);
            } else {
                editorRepository.save(document.getEditor());
            }
            editor = editorRepository.findByName(editorName);
            if (editor == null) {
                editor = addEditor(editorName);
            }
            document.setEditor(editor);
        }
    }

    public void modifyUser(User user, String name, String type, String login, String password, String address,
                           String phoneNumber) throws Exception {
        if (name != null) {
            user.setName(name);
        }
        if (type != null) {
            user.setType(type);
        }
        if (login != null) {
            user.setLogin(login);
        }
        if (password != null) {
            user.setPassword(password);
        }
        if (address != null) {
            user.setAddress(address);
        }
        if (phoneNumber != null) {
            user.setPhoneNumber(phoneNumber);
        }
        userRepository.save(user);
    }

    // Delete methods:

    public void clearDB() {
        documentUserRepository.deleteAll();
        userRepository.deleteAll();
        documentRepository.deleteAll();
        authorRepository.deleteAll();
        editorRepository.deleteAll();
        publisherRepository.deleteAll();
    }

    public void deleteDocument(Document document) {
        documentRepository.delete(document);
    }

    public void deleteDocumentById(Integer id) {
        documentRepository.delete(id);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void deleteUserById(int id){
        userRepository.deleteById(id);
    }
}
