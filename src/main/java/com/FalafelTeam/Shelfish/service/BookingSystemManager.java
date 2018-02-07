package com.FalafelTeam.Shelfish.service;

import com.FalafelTeam.Shelfish.model.Document;
import com.FalafelTeam.Shelfish.model.DocumentUser;
import com.FalafelTeam.Shelfish.model.User;
import com.FalafelTeam.Shelfish.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Service
public class BookingSystemManager {

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

    /**
     * method for booking documents in the library
     * @param document document that needs to be booked
     * @param user user that wants to book the document
     * @param isOutstanding if the request for the document is outstanding
     */
    public void bookDocument(Document document, User user, Boolean isOutstanding) throws Exception {

        DocumentUser documentUser = new DocumentUser(document, user, new Date(), isOutstanding);
        if (document.getType().equals("book")) {
            if (user.getType().equals("faculty")) {
                documentUser.setDueDate(addWeeks(documentUser.getDate(), 4));
            } else if (document.isBestseller()) {
                documentUser.setDueDate(addWeeks(documentUser.getDate(), 2));
            } else {
                documentUser.setDueDate(addWeeks(documentUser.getDate(), 3));
            }
        } else {
            documentUser.setDueDate(addWeeks(documentUser.getDate(), 2));
        }
        documentUserRepository.save(documentUser);

        document.addToQueue(documentUser);
        documentRepository.save(document);

        user.documents.add(documentUser);
        userRepository.save(user);
    }

    /**
     * supporting method that adds weeks to the date
     * @param date the date which has to be increased
     * @param weekNum number of weeks by which the date should be increased
     * @return the increased date
     */
    private Date addWeeks(Date date, int weekNum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7 * weekNum);
        return cal.getTime();
    }

    /**
     * method for checking out a book from the library
     * @param document document that needs to be checked out
     * @param patron patrom that wants to check out the document
     * @param librarian librarian that fulfills the check out requst
     * @throws Exception "Permission denied" if the user that tries to fulfill the request is not a librarian
     *                   "The user is not in the queue for the book"
     */
    public void checkOutDocument(Document document, User patron, User librarian) throws Exception {

        if (librarian.getType().equals("librarian")) {
            /*DocumentUser found = documentUserRepository.findByUserAndDocument(patron, document);
            System.out.println(document.getQueue().size());
            System.out.println(document.getQueue().contains(found));*/
            ListIterator<DocumentUser> iterator = document.getQueue().listIterator();
            DocumentUser found;
            while (iterator.hasNext()) {
                found = iterator.next();
                if (found.getDocument().equals(document) && found.getUser().equals(patron)) {
                    document.getQueue().remove(found);
                    document.takenBy.add(found);
                    documentRepository.save(document);
                    found.setStatus("taken");
                    documentUserRepository.save(found);
                } else throw new Exception("The user is not in the queue for the book");
            }
            /*if (document.getQueue().contains(found)) {
                document.getQueue().remove(found);
                document.takenBy.add(found);
                documentRepository.save(document);
                found.setStatus("taken");
                documentUserRepository.save(found);
            } else throw new Exception("The user is not in the queue for the book");*/
        } else throw new Exception("Permission denied");
    }
}