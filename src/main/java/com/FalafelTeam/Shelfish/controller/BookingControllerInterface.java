package com.FalafelTeam.Shelfish.controller;

import org.springframework.ui.Model;

/**
 * interface for the BookingController (the Booking System)
 */
public interface BookingControllerInterface {

    /**
     * method that handles get requests for documents
     * @param model is a model used to represent the page and its attributes
     * @return html page with the book preview
     */
    String documentPreview(Model model);

    /**
     * method that handles post requests for checking out a document
     * @param data is an object that contains all information needed to check out a document
     * @return redirect to the user's profile page with the list of documents that are checked out or on hands
     */
    String documentCheckOut(BookingData data);

}
