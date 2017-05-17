package hsenid.services;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.io.IOException;

@Service
public class ExceptionCloser {

    private static final Logger logger = LogManager.getLogger(ExceptionCloser.class);

    public void closeException(Closeable closeMe ){
        if (closeMe == null){
            return;
        }

        try {
            closeMe.close();
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
