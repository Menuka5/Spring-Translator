package hsenid.models;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by hsenid on 5/17/17.
 */
@Service
public class JsonStore {

    JSONObject jsonObject;
    String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }



}
