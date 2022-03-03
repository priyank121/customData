package com.offDesk.offdeskproject.Service.ServiceImpl;


import com.offDesk.offdeskproject.Model.UserMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailCommunicationService {

    @Autowired
    private JavaMailSender javaMailSender;

    public  UserMail mailSend(UserMail userMail)
    {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(userMail.getFromMail());
        simpleMailMessage.setTo(userMail.getToMail());
        simpleMailMessage.setText(userMail.getBodyMail());
        simpleMailMessage.setSubject(userMail.getSubjectMail());
        javaMailSender.send(simpleMailMessage);

        System.out.println("mail Sent SuccessFully");
        return userMail;
    }
}
