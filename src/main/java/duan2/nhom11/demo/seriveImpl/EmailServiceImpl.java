package duan2.nhom11.demo.seriveImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import duan2.nhom11.demo.service.Emailservice;

@Service
public class EmailServiceImpl implements Emailservice{

	@Autowired
	private JavaMailSender mailSender;

	@Async
	public void SendMail(SimpleMailMessage mailMessage) {
		// TODO Auto-generated method stub
		mailSender.send(mailMessage);
		
	
	}
	
	
}
