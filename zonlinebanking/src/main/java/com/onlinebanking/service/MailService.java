package com.onlinebanking.service;

public interface MailService {

	public void sendEmail(String to, String subject, String text);

	void sendEmailWithAttachment(String to, String subject, String text);

}
