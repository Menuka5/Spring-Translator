package hsenid.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ModifiedUrlGenerator {
    private static final Logger logger = LogManager.getLogger(ModifiedUrlGenerator.class);

    /**
     * This class replace the spaces of given string with %20 (space character in html)
     *
     * @param x This is the original string from translate.jsp which we want to translate
     * @return It return the modified string.
     */
    public String modifiedStr(String x) {

        StringBuilder test = new StringBuilder();

        String[] array = x.split("\\s+", -1);

        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                test.append("%20");
            }
            test.append(array[i]);
        }
        logger.trace("Empty string removing and formatting completed");
        return test.toString();

    }

    public String modifiedUrl(String fromText, String from, String to) {
        String urlWithKey = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20160607T111835Z.f64d4276fb712ae3.ed0f150b6046b34df73301472d5e576d32fe3c8b";

        StringBuilder apiCall = new StringBuilder();

        apiCall.append(urlWithKey);
        apiCall.append("&text=");
        apiCall.append(this.modifiedStr(fromText));
        apiCall.append("&lang=");
        apiCall.append(from);
        apiCall.append("-");
        apiCall.append(to);

        logger.info("API String creation completed");
        logger.info("Generated URL = "+ apiCall.toString());
        return apiCall.toString();
    }
}
