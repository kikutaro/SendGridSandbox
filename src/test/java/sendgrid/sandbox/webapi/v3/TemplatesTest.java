package sendgrid.sandbox.webapi.v3;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.ClassRule;
import sendgrid.sandbox.rule.SendGridResource;

/**
 * SendGrid Web API v3 Templates Test.
 * 
 * https://sendgrid.com/docs/API_Reference/Web_API_v3/Transactional_Templates/templates.html
 * 
 * @author kikuta
 */
public class TemplatesTest {
    
    @ClassRule
    public static SendGridResource sgResource = new SendGridResource();

    @Before
    public void setUp() {
    }
    
    @Test
    public void GetTemplatesTest() throws UnirestException {
        HttpResponse<JsonNode> result = Unirest.get("https://api.sendgrid.com/v3/templates")
                .header("Authorization", "Bearer " + sgResource.apiKey())
                .header("Content-Type", "application/json")
                .asJson();
        assertThat(result.getStatus(), is(200));
        System.out.println(result.getBody().toString());
    }
}
