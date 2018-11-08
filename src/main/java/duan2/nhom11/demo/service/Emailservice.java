package duan2.nhom11.demo.service;

import org.springframework.mail.SimpleMailMessage;

public interface Emailservice {
	public void SendMail(SimpleMailMessage mailMessage);
}
