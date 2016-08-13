package sendgrid.sandbox.rule;

import java.util.Properties;
import org.junit.rules.ExternalResource;
import static sendgrid.sandbox.SendGridPropertiesIds.*;

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
        sendGridProp.load(this.getClass().getResourceAsStream("/" + SG_PROP_FILE_NAME));
    }
    
    public String apiKey() {
        if(sendGridProp == null) {
            System.out.println("sendgrid.properties is not existed.");
        }
        return sendGridProp.getProperty(SG_API_KEY);
    }
    
    public String apiKeyId() {
        if(sendGridProp == null) {
            System.out.println("sendgrid.properties is not existed.");
        }
        return sendGridProp.getProperty(SG_API_KEY_ID);
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
