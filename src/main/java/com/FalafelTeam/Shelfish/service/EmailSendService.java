package com.FalafelTeam.Shelfish.service;

import com.FalafelTeam.Shelfish.model.User;
import lombok.extern.java.Log;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

@Log
public class EmailSendService {
    private String EMAIL;
    private String PASSWORD;
    private int PORT = 465;
    private String HOST = "smtp.gmail.com";
    private void sendEmail(User user, String subject, String message) {
        try {
            Email email = new SimpleEmail();
            email.setHostName(HOST);
            email.setSmtpPort(PORT);
            email.setAuthenticator(new DefaultAuthenticator(EMAIL, PASSWORD));
            email.setSSLOnConnect(true);
            email.setFrom(EMAIL);
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(user.getLogin());
            email.send();
        } catch (Exception e) {
            log.warning("Unable to send email");
        }
    }
}
