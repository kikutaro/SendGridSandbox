package sendgrid.sandbox.webapi.v3;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.ClassRule;
import org.junit.Test;
import sendgrid.sandbox.rule.SendGridResource;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * SendGrid Web API v3 Stats Test.
 * 
 * https://sendgrid.com/docs/API_Reference/Web_API_v3/Stats/index.html
 * 
 * @author kikuta
 */
public class StatsTest {
    
    @ClassRule
    public static SendGridResource sgResource = new SendGridResource();
    
    /**
     * https://sendgrid.com/docs/API_Reference/Web_API_v3/Stats/global.html
     * 
     * @throws UnirestException 
     */
    @Test
    public void GlobalStatsTest() throws UnirestException {
        HttpResponse<JsonNode> result = Unirest.get("https://api.sendgrid.com/v3/stats")
                .header("Authorization", "Bearer " + sgResource.apiKey())
                .header("Content-Type", "application/json")
                .queryString("start_date", "2016-07-01")
                .asJson();
        assertThat(result.getStatus(), is(200));
        System.out.println(result.getBody().toString());
    }
    
    /**
     * https://sendgrid.com/docs/API_Reference/Web_API_v3/Stats/categories.html
     * 
     * @throws UnirestException 
     */
    @Test
    public void CategoryStatsTest() throws UnirestException {
        HttpResponse<JsonNode> result = Unirest.get("https://api.sendgrid.com/v3/categories/stats")
                .header("Authorization", "Bearer " + sgResource.apiKey())
                .header("Content-Type", "application/json")
                .queryString("start_date", "2016-07-01")
                .queryString("categories", "Java")
                .asJson();
        assertThat(result.getStatus(), is(200));
    }
}
