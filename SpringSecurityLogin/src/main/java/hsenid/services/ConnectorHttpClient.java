package hsenid.services;

import hsenid.interfaces.IConnector;
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

    public JSONObject getAllLanguagesList() throws IOException, ParseException {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(UrlForGetAllLanguages);
        CloseableHttpResponse response = client.execute(request);
        InputStream input = response.getEntity().getContent();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(input, "UTF-8"));

        return (JSONObject) jsonObject.get("langs");
    }

    public String getTranslate(String textToTranslate, String fromLanguage, String toLanguage) throws IOException, ParseException {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(modifiedUrlGenerator.modifiedUrl(textToTranslate, fromLanguage, toLanguage));
        CloseableHttpResponse response = client.execute(request);
        InputStream input = response.getEntity().getContent();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(input, "UTF-8"));

        return jsonObject.toString();
    }
}
