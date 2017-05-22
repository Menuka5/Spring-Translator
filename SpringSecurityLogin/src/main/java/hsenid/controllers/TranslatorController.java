package hsenid.controllers;

import hsenid.models.JsonStore;
import hsenid.services.ConnectorS;
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

@Controller
public class TranslatorController {
    private static final Logger logger = LogManager.getLogger(TranslatorController.class);

    JSONParser parser = new JSONParser();

    @Autowired
    ConnectorS connectorS;

    @RequestMapping(value = "/translate", method = RequestMethod.GET)
    public String sendTranslateView(ModelMap model) {
        return "translate";
    }

    @RequestMapping(value = "/sendAllLanguages", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTranslated(){
        return connectorS.getiConnector().getAllLanguagesList();
    }

    @RequestMapping(value = "/getTranslate", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTranslate(HttpServletRequest request){

        JsonStore jsonStore = new JsonStore();

        String fromLanguage = request.getParameter("fromLang");
        String toLanguage = request.getParameter("toLang");
        String textToTranslate = request.getParameter("text");

        try {
            jsonStore.setJsonObject((JSONObject) parser.parse(connectorS.getiConnector().getTranslate(textToTranslate, fromLanguage, toLanguage)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonStore.getJsonObject();
    }
}
