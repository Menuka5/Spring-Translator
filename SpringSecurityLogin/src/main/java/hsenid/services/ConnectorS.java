package hsenid.services;

import hsenid.interfaces.IConnector;
import hsenid.services.apicallers.ConnectorHttpClient;
import hsenid.services.apicallers.ConnectorRestTemplate;
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
            this.iConnector = connectorRestTemplate;
        }else if (checkWhichIconnector.equals("http")){
            this.iConnector = connectorHttpClient;
        }

    }

    public IConnector getiConnector() {
        return iConnector;
    }

}
