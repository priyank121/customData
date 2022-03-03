package com.offDesk.offdeskproject.Controller;

import com.offDesk.offdeskproject.Model.UserMail;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@CrossOrigin("*")
@RequestMapping("/offdesk")
public interface IUserMail {

    @PostMapping("/sendmail")
    public UserMail sendMailByUser(@RequestBody UserMail userMail);
}
