package hsenid.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TranslatorController {
    @RequestMapping(value = "/translate", method = RequestMethod.GET)
    public String sendTranslateView(ModelMap model) {

        return "translate";
    }

    @RequestMapping(value = "/getTranslate", method = RequestMethod.GET)
    @ResponseBody
    public String getTranslated(HttpServletRequest request){
        String from = (String)request.getAttribute("from");
        String to = (String)request.getAttribute("to");
        String text = (String)request.getAttribute("text");
        return text;
    }
}
