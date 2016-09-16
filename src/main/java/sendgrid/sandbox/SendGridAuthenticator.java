package sendgrid.sandbox;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * SMTP認証用クラス.
 * 
 * JavaのSendMailでSMTP認証する際に利用する。
 * 
 * @author kikuta
 */
public class SendGridAuthenticator extends Authenticator {
    
    private final String userName;
    private final String password;
    
    public SendGridAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
    
}
