package sendgrid.sandbox;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.Properties;
import static sendgrid.sandbox.SendGridPropertiesIds.*;

/**
 * 
 * @author kikuta
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws com.mashape.unirest.http.exceptions.UnirestException
     */
    public static void main(String[] args) throws IOException, UnirestException {
        Properties sendGridProp = new Properties();
        sendGridProp.load(ClassLoader.getSystemResourceAsStream(SG_PROP_FILE_NAME));
        
        //API Scopeの確認（利用可能なAPIの確認）
        HttpResponse<JsonNode> result = Unirest.get("https://api.sendgrid.com/v3/scopes")
                .header("Authorization", "Bearer " + sendGridProp.getProperty(SG_API_KEY))
                .header("Content-Type", "application/json")
                .asJson();
        
        System.out.println(result.getBody().toString());
    }
}
