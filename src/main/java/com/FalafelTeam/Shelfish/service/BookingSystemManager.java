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
     * @param document Document that needs to be booked
     * @param user User who wants to book the document
     * @param weeksNum preferred Integer number of booking weeks (null if maximum)
     * @param isOutstanding Boolean value that shows if the request for the document is outstanding
     * @throws Exception "The preferred number of weeks is too big" when the preferred number of booking weeks
     *                          is bigger than allowed
     *                   "The document is a reference material"
     *                   "Cannot book several copies of the document"
     */
    public void bookDocument(Document document, User user, Integer weeksNum, Boolean isOutstanding) throws Exception {
        if (document.getIsReference().equals(true)) {
            throw new Exception("The document is a reference material");
        }
        if (documentUserRepository.findByUserAndDocument(user, document) != null) {
            throw new Exception("Cannot book several copies of the document");
        }
        DocumentUser documentUser = new DocumentUser(document, user, new Date(), isOutstanding);
        documentUser.setPreferredWeekNum(weeksNum, maxWeeksNum(document, user));
        documentUserRepository.save(documentUser);

        document.addToQueue(documentUser);
        documentRepository.save(document);

        user.getDocuments().add(documentUser);
        userRepository.save(user);
    }


    /**
     * method for booking documents in the library
     * @param document Document that needs to be booked
     * @param user User that wants to book the document
     * @param isOutstanding Boolean value that shows if the request for the document is outstanding
     * @throws Exception "The document is reference material"
     */
    public void bookDocument(Document document, User user, Boolean isOutstanding) throws Exception {
        bookDocument(document, user, null, isOutstanding);
    }

    /**
     * supporting method that adds weeks to the date
     * @param date the Date which has to be increased
     * @param weekNum Integer number of weeks by which the date should be increased
     * @return the increased Date
     */
    private Date addWeeks(Date date, Integer weekNum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7 * weekNum);
        return cal.getTime();
    }

    /**
     * method that finds the maximum number of weeks the user can check out the document for
     * @param document Document that is being checked out
     * @param user User who checks out the document
     * @return Integer number of weeks
     */
    private Integer maxWeeksNum(Document document, User user) {
        if (document.getType().equals("book")) {
            if (user.getType().equals("faculty")) {
                return 4;
            } else if (document.getIsBestseller()) {
                return 2;
            } else {
                return 3;
            }
        } else {
            return 2;
        }
    }

    /**
     * method for checking out a book from the library
     * @param document Document that needs to be checked out
     * @param patron User patron who wants to check out the document
     * @param librarian User librarian who fulfills the check out requst
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
        checkIfIsLibrarian(librarian);
        DocumentUser found = documentUserRepository.findByUserAndDocument(patron, document);
        if (found == null) {
            throw new Exception("The document wasn't booked by the user");
        }
        switch (found.getStatus()) {
            case "new":
                if (document.availableCopies() <= 0) {
                    throw new Exception("There are no copies of the document available");
                }
                break;
            case "outstanding":
                if (document.availableCopiesForOutstanding() <= 0) {
                    throw new Exception(("There are no copies of the document available"));
                }
                break;
            default:
                throw new Exception("The document wasn't booked by the user");
        }
        if (!document.queueContains(found)) {
            throw new Exception("The user is not in the queue for the book");
        }
        if (addWeeks(found.getDate(), 1).before(new Date())) {
            throw new Exception("The booking is overdue");
        }
        found.setDate(new Date());
        found.setStatus("taken");
        documentUserRepository.save(found);
    }

    /**
     * method for returning the document to the library
     * @param document the Document that is being returned
     * @param user the User who is returning the document
     * @throws Exception "The document wasn't booked by the user"
     *                   "The document wasn't checked out by the user"
     */
    public void returnDocument(Document document, User user, User librarian) throws Exception {
        checkIfIsLibrarian(librarian);
        DocumentUser found = documentUserRepository.findByUserAndDocument(user, document);
        if (found == null) {
            throw new Exception("The document wasn't booked by the user");
        }
        if (found.getStatus().equals("new") || found.getStatus().equals("outstanding")) {
            throw new Exception("The document wasn't checked out by the user");
        }
        document.getUsers().remove(found);
        documentRepository.save(document);
        user.getDocuments().remove(found);
        userRepository.save(user);
        documentUserRepository.delete(found);
    }

    /**
     * method for renewing documents when preferred number of weeks is stated
     * @param document Document that has to be renewed
     * @param weeksNum Integer number of preferred weeks
     * @param patron User who wants to renew the book
     * @throws Exception "The preferred number of weeks is too big"
     *                   "The document wasn't checked out by the user or it was already renewed once"
     *                   "The item must be returned immediately"
     */
    public void renewDocument(Document document, Integer weeksNum, User patron) throws Exception {
        DocumentUser documentUser = documentUserRepository.findByUserAndDocument(patron, document);
        if (documentUser == null || !documentUser.getStatus().equals("taken")) {
            throw new Exception("The document wasn't checked out by the user or it was already renewed once");
        }
        if (document.availableCopiesForOutstanding() <= 0) {
            throw new Exception("The item must be returned immediately");
        }
        documentUser.setDate(addWeeks(documentUser.getDate(), documentUser.getWeekNum()));
        documentUser.setPreferredWeekNum(weeksNum, maxWeeksNum(document, patron));
        documentUser.setStatus("renewed");
        documentUserRepository.save(documentUser);
    }

    /**
     * method for renewing documents when preferred number of weeks is not stated
     * @param document Document that has to be renewed
     * @param patron User who wants to renew the book
     * @throws Exception "The document wasn't checked out by the user or it was already renewed once"
     *                   "The item must be returned immediately"
     */
    public void renewDocument(Document document, User patron) throws Exception {
        renewDocument(document, null, patron);
    }

    public void getAllFine(User user) {

    }

    public void getFine(Document document, User user) {

    }

    public void returnFine(Document document, User patron, User librarian) {

    }

    public void returnFine(Integer amount, User patron, User librarian) {

    }

    /**
     * method that sends the return request for the document to the user
     * @param document the Document that has to be returned
     * @param user the User who has to return the document
     * @param librarian the User who sends the return request
     * @throws Exception "Permission denied" if the user who tries to send the return request is not a librarian
     */
    public void requestReturn(Document document, User user, User librarian) throws Exception {
        checkIfIsLibrarian(librarian);
        // the request sending
    }

    /**
     * method that checks if the user stated as a librarian is truly librarian
     * @param user the User that needs to be checked
     * @throws Exception "Permission denied" if teh user is not a librarian
     */
    private void checkIfIsLibrarian(User user) throws Exception {
        if (!user.getType().equals("librarian")) {
            throw new Exception("Permission denied");
        }
    }
}
