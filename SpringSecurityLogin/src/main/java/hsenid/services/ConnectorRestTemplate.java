package hsenid.services;

import hsenid.interfaces.IConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Service
public class ConnectorRestTemplate implements IConnector {
    private static final Logger logger = LogManager.getLogger(ConnectorRestTemplate.class);

    static String test;
    static JSONObject sendJson;
    RestTemplate restTemplate = new RestTemplate();
    String UrlForGetAllLanguages = "https://translate.yandex.net/api/v1.5/tr.json/getLangs?ui=en&key=trnsl.1.1.20160607T111835Z.f64d4276fb712ae3.ed0f150b6046b34df73301472d5e576d32fe3c8b";
    JSONParser parser = new JSONParser();

    public JSONObject getAllLanguagesList() {

        String getAllLanguagesList = restTemplate.getForObject(UrlForGetAllLanguages, String.class);

        try {
            JSONObject json = (JSONObject) parser.parse(getAllLanguagesList);

            test = json.get("langs").toString();
            sendJson = (JSONObject) parser.parse(test);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        logger.info(test);

        return sendJson;

    }

}
