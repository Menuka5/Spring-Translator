package hsenid.interfaces;


import org.json.simple.JSONObject;

public interface IConnector {

    public JSONObject getAllLanguagesList();
    public String  getTranslate(String textToTranslate, String fromLanguage, String toLanguage);
}

