package sendgrid.sandbox.java;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.ClassRule;
import sendgrid.sandbox.rule.SendGridResource;

/**
 *
 * @author kikuta
 */
public class SendMail {
    
    @ClassRule
    public static SendGridResource sgResource = new SendGridResource();
    
    @Before
    public void setUp() {
    }

    @Test
    public void SendingMail() {
        Email from = new Email(sgResource.from());
        Email to = new Email(sgResource.to());
        Content content = new Content("text/html", "<p>メールのコンテンツ</p>");
        Mail mail = new Mail(from, "テスト", to, content);

        SendGrid sg = new SendGrid(sgResource.apiKey());
        Request request = new Request();
        
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            assertThat(response.statusCode, is(202));
        } catch (IOException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void SendingMultipleMail() {
        Email from = new Email(sgResource.from());
        Email to = new Email(sgResource.to());
        Email to2 = new Email(sgResource.to2());
        Content content = new Content("text/html", "<p>%name%さんへ メールのコンテンツ</p>");
        Mail mail = new Mail();
        
        Personalization personalization1 = new Personalization();
        personalization1.addTo(to);
        personalization1.addSubstitution("%name%", to.getEmail());
        mail.addPersonalization(personalization1);

        Personalization personalization2 = new Personalization();
        personalization2.addTo(to2);
        personalization2.addSubstitution("%name%", to2.getEmail());
        mail.addPersonalization(personalization2);
        
        mail.setFrom(from);
        mail.setSubject("テスト");
        mail.addContent(content);
        
        SendGrid sg = new SendGrid(sgResource.apiKey());
        Request request = new Request();
        
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            assertThat(response.statusCode, is(202));
        } catch (IOException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
