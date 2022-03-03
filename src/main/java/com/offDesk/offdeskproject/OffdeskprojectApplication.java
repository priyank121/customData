package com.offDesk.offdeskproject;

import com.offDesk.offdeskproject.Service.ServiceImpl.MailCommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OffdeskprojectApplication {

	@Autowired
	private MailCommunicationService mailCommunicationService;

	public static void main(String[] args) {
		SpringApplication.run(OffdeskprojectApplication.class, args);

	}


}
