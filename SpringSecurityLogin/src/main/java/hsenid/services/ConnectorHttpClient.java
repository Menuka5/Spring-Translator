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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@Service
@PropertySource("classpath:config.properties")
public class ConnectorHttpClient implements IConnector {

    private static final Logger logger = LogManager.getLogger(ConnectorHttpClient.class);

    @Value("${alllanguageurl}")
    private String UrlForGetAllLanguages;

    @Autowired
    ModifiedUrlGenerator modifiedUrlGenerator;

    @Autowired
    JsonStore jsonStore;

    @Autowired
    ExceptionCloser exceptionCloser;

    public JSONObject getAllLanguagesList() {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(UrlForGetAllLanguages);
        InputStream input = null;
        CloseableHttpResponse response = null;

        try {
            response = client.execute(request);
            input = response.getEntity().getContent();

            JSONParser jsonParser = new JSONParser();
            jsonStore.setJsonObject((JSONObject)jsonParser.parse(new InputStreamReader(input, "UTF-8")));
        } catch (IOException | ParseException e) {
            logger.error(e);
        } finally {
            exceptionCloser.closeException(input);
            exceptionCloser.closeException(response);
        }

        return (JSONObject) jsonStore.getJsonObject().get("langs");
    }

    public String getTranslate(String textToTranslate, String fromLanguage, String toLanguage) {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(modifiedUrlGenerator.modifiedUrl(textToTranslate, fromLanguage, toLanguage));
        InputStream input = null;
        CloseableHttpResponse response = null;

        try {
            response = client.execute(request);
            input = response.getEntity().getContent();

            JSONParser jsonParser = new JSONParser();
            jsonStore.setJsonObject((JSONObject)jsonParser.parse(new InputStreamReader(input, "UTF-8")));

        } catch (IOException | ParseException e) {
            logger.error(e);
        } finally {
            exceptionCloser.closeException(input);
            exceptionCloser.closeException(response);
        }

        return jsonStore.getJsonObject().toString();
    }
}
