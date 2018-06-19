package serviceImpl;

import org.apache.struts2.ServletActionContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import service.MailService;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Component("mailService")
public class MailServiceImpl implements MailService {
    @Override
    public JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        ServletContext servletContext =ServletActionContext.getServletContext();
        String host =servletContext.getInitParameter("mail_host");
        int port =Integer.valueOf(servletContext.getInitParameter("mail_port"));
        String username =servletContext.getInitParameter("mail_username");
        String password =servletContext.getInitParameter("mail_password");
        String defaultEncoding =servletContext.getInitParameter("mail_defaultEncoding");
        String timeout =servletContext.getInitParameter("mail_timeout");
        String auth =servletContext.getInitParameter("mail_auth");
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(username);
        sender.setPassword(password);
        sender.setDefaultEncoding(defaultEncoding);
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", timeout);
        p.setProperty("mail.smtp.auth", auth);
        sender.setJavaMailProperties(p);
        return sender;

    }

    @Override
    public void sendMail(String receiver, String subject, String content){
        JavaMailSenderImpl mailSender = createMailSender();
        MimeMessage mimeMessage = createMailSender().createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = null;
        ServletContext servletContext =ServletActionContext.getServletContext();
        String from =servletContext.getInitParameter("mail_from");
        String personal =servletContext.getInitParameter("mail_personal");
        String defaultEncoding =servletContext.getInitParameter("mail_defaultEncoding");
        try {
            messageHelper = new MimeMessageHelper(mimeMessage, true, defaultEncoding);
            messageHelper.setFrom(from, personal);
            messageHelper.setTo(receiver);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }
}
