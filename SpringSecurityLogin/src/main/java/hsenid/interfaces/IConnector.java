package hsenid.interfaces;


import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface IConnector {

    public JSONObject getAllLanguagesList() throws IOException, SAXException, ParserConfigurationException, ParseException;
    public String  getTranslate(String textToTranslate, String fromLanguage, String toLanguage) throws IOException, ParseException;
}

