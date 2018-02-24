package com.FalafelTeam.Shelfish.service;

import com.FalafelTeam.Shelfish.model.Document;
import com.FalafelTeam.Shelfish.model.DocumentUser;
import com.FalafelTeam.Shelfish.model.User;
import com.FalafelTeam.Shelfish.repository.DocumentRepository;
import com.FalafelTeam.Shelfish.repository.DocumentUserRepository;
import com.FalafelTeam.Shelfish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BookingSystemManager {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentUserRepository documentUserRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * method for booking documents in the library when preferred number of booking weeks is stated
     * @param document document that needs to be booked
     * @param user user that wants to book the document
     * @param weeksNum preferred number of booking weeks
     * @param isOutstanding if the request for the document is outstanding
     * @throws Exception "The preferred number of booking weeks is too big" when the preferred number of booking weeks
     *                          is bigger than allowed
     *                   "The document is a reference material"
     */
    public void bookDocument(Document document, User user, Integer weeksNum, Boolean isOutstanding) throws Exception {
        if (document.getIsReference().equals(true)) {
            throw new Exception("The document is a reference material");
        }
        DocumentUser documentUser = new DocumentUser(document, user, new Date(), isOutstanding);
        Integer maxWeeksNum;
        if (document.getType().equals("book")) {
            if (user.getType().equals("faculty")) {
                maxWeeksNum = 4;
            } else if (document.getIsBestseller()) {
                maxWeeksNum = 2;
            } else {
                maxWeeksNum = 3;
            }
        } else {
            maxWeeksNum = 2;
        }
        if (weeksNum == null) {
            documentUser.setDueDate(addWeeks(documentUser.getDate(), maxWeeksNum));
        } else if (weeksNum > maxWeeksNum) {
            throw new Exception("The preferred number of booking weeks is too big");
        } else {
            documentUser.setDueDate(addWeeks(documentUser.getDate(), weeksNum));
        }
        documentUserRepository.save(documentUser);

        document.addToQueue(documentUser);
        documentRepository.save(document);

        user.getDocuments().add(documentUser);
        userRepository.save(user);
    }

    /**
     * method for booking documents in the library when preferred number of booking weeks is not stated
     * @param document document that needs to be booked
     * @param user user that wants to book the document
     * @param isOutstanding if the request for the document is outstanding
     */
    public void bookDocument(Document document, User user, Boolean isOutstanding) throws Exception {
        bookDocument(document, user, null, isOutstanding);
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
     * @param patron patron that wants to check out the document
     * @param librarian librarian that fulfills the check out requst
     * @throws Exception "Permission denied" if the user who tries to fulfill the request is not a librarian
     *                   "The user is not in the queue for the book"
     *                   "The document wasn't booked by the user"
     *                   "There are no copies of the document available"
     *                   "The document is a reference material"
     */
    public void checkOutDocument(Document document, User patron, User librarian) throws Exception {
        if (document.getIsReference().equals(true)) {
            throw new Exception("The document is a reference material");
        }
        checkIfIsLibararian(librarian);
        if (document.availableCopies() == 0) {
            throw new Exception("There are no copies of the document available");
        }
        DocumentUser found = documentUserRepository.findByUserAndDocument(patron, document);
        if (found == null) {
            throw new Exception("The document wasn't booked by the user");
        }
        if (document.queueContains(found)) {
            found.setStatus("taken");
            documentUserRepository.save(found);
        } else throw new Exception("The user is not in the queue for the book");

    }

    /**
     * method for returning the document to the library
     * @param document the document that is being returned
     * @param user the user that is returning the document
     * @throws Exception "The document wasn't booked by the user"
     *                   "The document wasn't checked out by the user"
     */
    public void returnDocument(Document document, User user, User librarian) throws Exception {
        checkIfIsLibararian(librarian);
        DocumentUser found = documentUserRepository.findByUserAndDocument(user, document);
        if (found == null) {
            throw new Exception("The document wasn't booked by the user");
        } else if (found.getStatus().equals("new")) {
            throw new Exception("The document wasn't checked out by the user");
        } else {
            document.getUsers().remove(found);
            documentRepository.save(document);
            user.getDocuments().remove(found);
            userRepository.save(user);
            documentUserRepository.delete(found);
        }
    }

    /**
     * method that sends the return request for the document to the user
     * @param document the document that has to be returned
     * @param user the user who has to return the document
     * @param librarian the user who sends the return request
     * @throws Exception "Permission denied" if the user who tries to send the return request is not a librarian
     */
    public void requestReturn(Document document, User user, User librarian) throws Exception {
        checkIfIsLibararian(librarian);
        // the request sending
    }

    private void checkIfIsLibararian(User user) throws Exception {
        if (user.getType().equals("librarian")) {
            throw new Exception("Permission denied");
        }
    }
}
