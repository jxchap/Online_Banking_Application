package com.onlinebanking.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendEmail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(to);

		message.setSubject(subject);

		message.setText(text);

		mailSender.send(message);

	}

	@Override
	public void sendEmailWithAttachment(String to, String subject, String text) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setTo(to);

			mimeMessageHelper.setSubject(subject);

			mimeMessageHelper.setText(text);

			FileSystemResource fileSystemResource = new FileSystemResource(
					"C:\\Users\\Jack\\Desktop\\Java Mail Test.txt");
			
			mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
			
			mailSender.send(mimeMessage);

		} catch (MessagingException e) {

			e.printStackTrace();
		}

	}

}
