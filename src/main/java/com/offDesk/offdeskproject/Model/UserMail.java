package com.offDesk.offdeskproject.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMail {

    private String fromMail;
    private String toMail;
    private String subjectMail;
    private String bodyMail;

}
