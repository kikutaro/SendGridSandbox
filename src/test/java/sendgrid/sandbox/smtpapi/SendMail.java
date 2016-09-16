package sendgrid.sandbox.smtpapi;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.ClassRule;
import sendgrid.sandbox.SendGridAuthenticator;
import sendgrid.sandbox.rule.SendGridResource;

/**
 * SendGridのSMTPを利用したメール送信.
 * 
 * @author kikuta
 */
public class SendMail {
    
    @ClassRule
    public static SendGridResource sgResource = new SendGridResource();
    
    private Properties prop;
    private SendGridAuthenticator auth;
    
    @Before
    public void setUp() {
        prop = new Properties();
        prop.put("mail.smtp.host", "smtp.sendgrid.net");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.debug", "true");
        prop.put("mail.smtp.auth", "true");
        auth = new SendGridAuthenticator(sgResource.loginId(), sgResource.loginPass());
    }

    @Test
    public void SendMail() throws MessagingException {
        Session session = Session.getDefaultInstance(prop, auth);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(sgResource.from());
        message.setRecipients(Message.RecipientType.TO, sgResource.to());
        message.setSubject("Sending from Smtp by Java.");
        message.setText("hello");
        Transport.send(message);
    }
}
