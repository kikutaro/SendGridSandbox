package sendgrid.sandbox.webapi.v3;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    public void GetAlerts() throws UnirestException {
        HttpResponse<JsonNode> result = Unirest.get("https://api.sendgrid.com/v3/alerts")
                .header("Authorization", "Bearer " + sgResource.apiKey())
                .header("Content-Type", "application/json")
                .asJson();
        assertThat(result.getStatus(), is(200));
        System.out.println(result.getBody().toString());
    }
    
    @Test
    public void CreateAlert() throws UnirestException {
        HttpResponse<JsonNode> result = Unirest.post("https://api.sendgrid.com/v3/alerts")
                .header("Authorization", "Bearer " + sgResource.apiKey())
                .header("Content-Type", "application/json")
                .body("{\"type\": \"stats_notification\", \"email_to\": \"test@example.com\", \"frequency\": \"daily\"}")
                .asJson();
        assertThat(result.getStatus(), is(201));
        System.out.println(result.getBody().toString());
    }
    
    @Test
    public void DeleteAlert() throws UnirestException, IOException {
        HttpResponse<JsonNode> result = Unirest.get("https://api.sendgrid.com/v3/alerts")
                .header("Authorization", "Bearer " + sgResource.apiKey())
                .header("Content-Type", "application/json")
                .asJson();
        System.out.println(result.getBody());
        
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser parser = jsonFactory.createParser(result.getBody().toString());
        
        List<String> ids = new ArrayList();
        while(parser.nextToken() != JsonToken.END_OBJECT) {
            if(parser.getCurrentName() != null && parser.getCurrentName().equals("id")) {
                parser.nextToken();
                ids.add(parser.getText());
            }
        }
        
        ids.stream().forEach(id -> {
            try {
                HttpResponse<JsonNode> deleteResult = Unirest.delete("https://api.sendgrid.com/v3/alerts/{alert_id}")
                        .header("Authorization", "Bearer " + sgResource.apiKey())
                        .header("Content-Type", "application/json")
                        .routeParam("alert_id", id)
                        .asJson();
                assertThat(deleteResult.getStatus(), is(204));
            } catch (UnirestException ex) {
            }
        });
        
        
        
    }
}
