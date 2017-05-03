package hsenid.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TranslatorController {
    @RequestMapping(value = "/translate", method = RequestMethod.GET)
    public String sendTranslateView(ModelMap model) {

        return "translate";
    }

    @RequestMapping(value = "/getTranslate", method = RequestMethod.GET)
    public String getTranslated(ModelMap modelMap){

    }
}
