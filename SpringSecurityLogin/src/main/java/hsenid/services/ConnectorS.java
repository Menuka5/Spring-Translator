package hsenid.services;

import hsenid.interfaces.IConnector;
import org.springframework.stereotype.Service;

@Service
public class ConnectorS {


    PropertyCaller propertyCaller = new PropertyCaller();

    String checkWhichIconnector = propertyCaller.sendProperty("keyforwhichapitocall");

    private IConnector iConnector;

    ConnectorHttpClient connectorHttpClient = new ConnectorHttpClient();
    ConnectorRestTemplate connectorRestTemplate = new ConnectorRestTemplate();


    public ConnectorS() {

        if (checkWhichIconnector.equals("rest")){
            this.iConnector = connectorHttpClient;
        }else if (checkWhichIconnector.equals("http")){
            this.iConnector = connectorRestTemplate;
        }

    }

    public IConnector getiConnector() {
        return iConnector;
    }

}
