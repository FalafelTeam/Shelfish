package com.FalafelTeam.Shelfish.controller;

import com.FalafelTeam.Shelfish.repository.DocumentRepository;
import com.FalafelTeam.Shelfish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * class that handles http requests at "/document"
 */
@Controller
public class BookingController {

    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    UserRepository userRepository;

}

/**
 * class that contains data needed to check out documents
 */
class BookingData {

}
