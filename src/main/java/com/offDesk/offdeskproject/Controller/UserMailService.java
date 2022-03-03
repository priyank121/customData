package com.offDesk.offdeskproject.Controller;

import com.offDesk.offdeskproject.Model.UserMail;
import com.offDesk.offdeskproject.Service.ServiceImpl.MailCommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserMailService implements IUserMail{

    @Autowired
    MailCommunicationService mailCommunicationService;


    @Override
    public UserMail sendMailByUser(UserMail userMail) {
    return mailCommunicationService.mailSend(userMail);
    }
}
