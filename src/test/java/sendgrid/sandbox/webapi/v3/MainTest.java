package sendgrid.sandbox.webapi.v3;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import sendgrid.sandbox.rule.SendGridResource;

/**
 * SendGrid Web API v3 Mail Test.
 * 
 * https://sendgrid.com/docs/API_Reference/Web_API_v3/Mail/index.html
 * 
 * @author kikuta
 */
public class MainTest {
    
    @ClassRule
    public static SendGridResource sgResource = new SendGridResource();

    @Before
    public void setUp() throws IOException {
        
    }

    @Test
    public void SendingSimpleMailTest() throws UnirestException {
        
        HttpResponse<JsonNode> result = Unirest.post("https://api.sendgrid.com/v3/mail/send")
                .header("Authorization", "Bearer " + sgResource.apiKey())
                .header("Content-Type", "application/json")
                .body("{\"personalizations\": [{\"to\": [{\"email\": \"" + sgResource.to() + "\"}]}],\"from\": {\"email\": \"" + sgResource.from() + "\"},\"subject\": \"テスト\",\"content\": [{\"type\": \"text/plain\", \"value\": \"Unirestで送信するテスト\"}]}")
                .asJson();
        assertThat(result.getStatus(), is(202));
    }
    
    @Test
    public void SendingSimpleMailWithCategoriesTest() throws UnirestException {
        
        HttpResponse<JsonNode> result = Unirest.post("https://api.sendgrid.com/v3/mail/send")
                .header("Authorization", "Bearer " + sgResource.apiKey())
                .header("Content-Type", "application/json")
                .body("{\"personalizations\": [{\"to\": [{\"email\": \"" + sgResource.to() + "\"}]}],\"from\": {\"email\": \"" + sgResource.from() + "\"},\"subject\": \"テスト\",\"content\": [{\"type\": \"text/plain\", \"value\": \"Unirestで送信するテスト\"}], \"categories\": [\"Java\", \"Sample\"]}")
                .asJson();
        assertThat(result.getStatus(), is(202));
    }
}
