package sendgrid.sandbox.webapi.v3;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.ClassRule;
import sendgrid.sandbox.rule.SendGridResource;

/**
 * https://sendgrid.com/docs/API_Reference/Web_API_v3/Suppression_Management/groups.html
 * https://sendgrid.com/docs/User_Guide/Suppressions/advanced_suppression_manager.html
 * 
 * https://sendgrid.kke.co.jp/docs/User_Manual_JP/Suppressions/advanced_suppression_manager.html
 * https://sendgrid.kke.co.jp/docs/User_Manual_JP/Suppressions/group_unsubscribes.html
 * 
 * @author kikuta
 */
public class SuppressionManagementTest {
    
    @ClassRule
    public static SendGridResource sgResource = new SendGridResource();
    
    @Test
    public void GetUnsubscribeGroups() throws UnirestException {
        HttpResponse<JsonNode> result = Unirest.get("https://api.sendgrid.com/v3/asm/groups")
                .header("Authorization", "Bearer " + sgResource.apiKey())
                .header("Content-Type", "application/json")
                .asJson();
        assertThat(result.getStatus(), is(200));
        //System.out.println(result.getBody().getObject().toString());
    }
}
