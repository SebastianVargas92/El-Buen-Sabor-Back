package com.example.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
public class EmailRest {
	
	@Autowired
	private EmailPort emailPort;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/send")
	@ResponseBody
	public boolean SendEmail(@RequestBody EmailBody emailBody)  {
		return emailPort.sendEmail(emailBody);
	}
	
}
