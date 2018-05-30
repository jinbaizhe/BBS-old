package service;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public interface MailService {
    public JavaMailSenderImpl createMailSender();
    public void sendMail(String receiver, String subject, String content);
}
