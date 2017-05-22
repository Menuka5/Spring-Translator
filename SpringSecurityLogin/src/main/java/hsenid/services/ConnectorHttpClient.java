package hsenid.services;

import hsenid.interfaces.IConnector;
import hsenid.models.JsonStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



@PropertySource("classpath:config.properties")
public class ConnectorHttpClient implements IConnector {

    private static final Logger logger = LogManager.getLogger(ConnectorHttpClient.class);

    PropertyCaller propertyCaller = new PropertyCaller();
    private String UrlForGetAllLanguages = propertyCaller.sendProperty("alllanguageurl");


    ModifiedUrlGenerator modifiedUrlGenerator = new ModifiedUrlGenerator();

    JsonStore jsonStore = new JsonStore();
    JsonStore jsonStoreReply = new JsonStore();


    ExceptionCloser exceptionCloser = new ExceptionCloser();

    public JSONObject getAllLanguagesList() {
//        logger.info("test");
        CloseableHttpClient client = HttpClientBuilder.create().build();
        InputStream input = null;
        CloseableHttpResponse response = null;

        try {
            HttpPost request = new HttpPost(UrlForGetAllLanguages);
            response = client.execute(request);
            input = response.getEntity().getContent();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(input, "UTF-8"));

            jsonStore.setJsonObject(jsonObject);
            return (JSONObject) jsonStore.getJsonObject().get("langs");
        } catch (IOException | ParseException e) {
            logger.error(e);
        } finally {
            exceptionCloser.closeException(input);
            exceptionCloser.closeException(response);
        }

        return (JSONObject) jsonStore.getJsonObject().get("langs");
    }

    public String getTranslate(String textToTranslate, String fromLanguage, String toLanguage) {

        HttpPost request = new HttpPost(modifiedUrlGenerator.modifiedUrl(textToTranslate, fromLanguage, toLanguage));
        InputStream input = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {


            response = client.execute(request);
            input = response.getEntity().getContent();

            JSONParser jsonParser = new JSONParser();
            jsonStoreReply.setJsonObject((JSONObject)jsonParser.parse(new InputStreamReader(input, "UTF-8")));

        } catch (IOException | ParseException e) {
            logger.error(e);
        } finally {
            exceptionCloser.closeException(input);
            exceptionCloser.closeException(response);
        }
//        String test = jsonStoreReply.getJsonObject().toString();
        return jsonStoreReply.getJsonObject().toString();
    }

    public static void main(String[] args) {
        ConnectorHttpClient connectorHttpClient = new ConnectorHttpClient();
        System.out.println(connectorHttpClient.getTranslate("Dog", "en", "ja"));;
    }
}
