package sendgrid.sandbox.rule;

import java.util.Properties;
import org.junit.rules.ExternalResource;

/**
 * SendGridResource.
 * 
 * Setting common initialization for SendGrid by using JUnit Rule.
 * 
 * @author kikuta
 */
public class SendGridResource extends ExternalResource {
    
    private Properties sendGridProp;

    @Override
    protected void before() throws Throwable {
        sendGridProp = new Properties();
        sendGridProp.load(this.getClass().getResourceAsStream("/sendgrid.properties"));
    }
    
    public String apiKey() {
        if(sendGridProp == null) {
            System.out.println("sendgrid.properties is not existed.");
        }
        return sendGridProp.getProperty("APIKeys");
    }
    
    public String to() {
        if(sendGridProp == null) {
            System.out.println("sendgrid.properties is not existed.");
        }
        return sendGridProp.getProperty("To");
    }
    
    public String from() {
        if(sendGridProp == null) {
            System.out.println("sendgrid.properties is not existed.");
        }
        return sendGridProp.getProperty("From");
    }
}
