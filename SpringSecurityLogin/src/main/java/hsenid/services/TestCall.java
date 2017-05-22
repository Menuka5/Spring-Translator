package hsenid.services;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestCall {
    public static void main(String[] args) throws IOException, ParseException {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        //send the request
        HttpPost request = new HttpPost("https://translate.yandex.net/api/v1.5/tr.json/getLangs?ui=en&key=trnsl.1.1.20160607T111835Z.f64d4276fb712ae3.ed0f150b6046b34df73301472d5e576d32fe3c8b");

        //excute the request to obtain the response
        CloseableHttpResponse response = client.execute(request);

        /** Get the response */
        InputStream input = response.getEntity().getContent();

//        InputStream inputStream = ... //Read from a file, or a HttpRequest, or whatever.
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(input, "UTF-8"));

        System.out.println(jsonObject.toString());
    }
}
