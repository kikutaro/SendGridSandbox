package sendgrid.sandbox.webapi.v3;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.ClassRule;
import sendgrid.sandbox.rule.SendGridResource;
import static org.hamcrest.CoreMatchers.is;

/**
 * SendGrid Web API v3 Alerts Test.
 * 
 * @author kikuta
 */
public class AlertsAPITest {
    
    @ClassRule
    public static SendGridResource sgResource = new SendGridResource();
    
    /**
     * https://sendgrid.com/docs/API_Reference/Web_API_v3/alerts.html
     * 
     * @throws UnirestException 
     */
    @Test
    public void AlertsTest() throws UnirestException {
        HttpResponse<JsonNode> result = Unirest.get("https://api.sendgrid.com/v3/alerts")
                .header("Authorization", "Bearer " + sgResource.apiKey())
                .header("Content-Type", "application/json")
                .asJson();
        assertThat(result.getStatus(), is(200));
        System.out.println(result.getBody().toString());
    }
}
