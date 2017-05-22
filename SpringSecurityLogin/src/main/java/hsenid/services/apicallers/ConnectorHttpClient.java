package hsenid.services.apicallers;

import hsenid.interfaces.IConnector;
import hsenid.models.JsonStore;
import hsenid.services.ExceptionCloser;
import hsenid.services.ModifiedUrlGenerator;
import hsenid.services.PropertyCaller;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConnectorHttpClient implements IConnector {

    private static final Logger logger = LogManager.getLogger(ConnectorHttpClient.class);

    PropertyCaller propertyCaller = new PropertyCaller();
    ModifiedUrlGenerator modifiedUrlGenerator = new ModifiedUrlGenerator();
    JsonStore jsonStore = new JsonStore();
    JsonStore jsonStoreReply = new JsonStore();
    ExceptionCloser exceptionCloser = new ExceptionCloser();
    JSONParser jsonParser = new JSONParser();

    private String UrlForGetAllLanguages = propertyCaller.sendProperty("alllanguageurl");

    public JSONObject getAllLanguagesList() {

        InputStream input = null;
        CloseableHttpResponse response = null;

        try(CloseableHttpClient client = HttpClientBuilder.create().build();) {

            HttpPost request = new HttpPost(UrlForGetAllLanguages);
            response = client.execute(request);
            input = response.getEntity().getContent();

            JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(input, "UTF-8"));
            jsonStore.setJsonObject(jsonObject);

        } catch (IOException | ParseException e) {
            logger.error(e);
        } finally {
            exceptionCloser.closeException(input);
            exceptionCloser.closeException(response);
        }

        return (JSONObject) jsonStore.getJsonObject().get("langs");
    }

    public String getTranslate(String textToTranslate, String fromLanguage, String toLanguage) {

        String modifiedUrlToPost = modifiedUrlGenerator.modifiedUrl(textToTranslate, fromLanguage, toLanguage);

        InputStream input = null;
        CloseableHttpResponse response = null;

        try(CloseableHttpClient client = HttpClientBuilder.create().build();) {

            HttpPost request = new HttpPost(modifiedUrlToPost);
            response = client.execute(request);
            input = response.getEntity().getContent();

            jsonStoreReply.setJsonObject((JSONObject)jsonParser.parse(new InputStreamReader(input, "UTF-8")));

        } catch (IOException | ParseException e) {
            logger.error(e);
        } finally {
            exceptionCloser.closeException(input);
            exceptionCloser.closeException(response);
        }

        return jsonStoreReply.getJsonObject().toString();
    }

}
