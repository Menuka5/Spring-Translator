package hsenid.controllers;

import hsenid.services.ConnectorHttpClient;
import hsenid.services.ConnectorRestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class TranslatorController {
    private static final Logger logger = LogManager.getLogger(TranslatorController.class);

    JSONParser parser = new JSONParser();

    @Autowired
    ConnectorRestTemplate connectorRestTemplate;

    @Autowired
    ConnectorHttpClient connectorHttpClient;


    @RequestMapping(value = "/translate", method = RequestMethod.GET)
    public String sendTranslateView(ModelMap model) {
        return "translate";
    }

    @RequestMapping(value = "/sendAllLanguages", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTranslated() throws IOException, ParseException {
        return connectorHttpClient.getAllLanguagesList();
    }

    @RequestMapping(value = "/getTranslate", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTranslate(HttpServletRequest request) throws ParseException, IOException {

        String fromLanguage = request.getParameter("fromLang");
        String toLanguage = request.getParameter("toLang");
        String textToTranslate = request.getParameter("text");

        logger.info(fromLanguage+toLanguage+textToTranslate);

        JSONObject reply = (JSONObject) parser.parse(connectorHttpClient.getTranslate(textToTranslate, fromLanguage, toLanguage));

        return reply;
    }
}
