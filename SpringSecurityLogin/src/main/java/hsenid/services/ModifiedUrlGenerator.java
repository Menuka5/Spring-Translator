package hsenid.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:config.properties")
public class ModifiedUrlGenerator {
    private static final Logger logger = LogManager.getLogger(ModifiedUrlGenerator.class);

    PropertyCaller propertyCaller = new PropertyCaller();
    private String urlWithKey = propertyCaller.sendProperty("urlWithKey");

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
