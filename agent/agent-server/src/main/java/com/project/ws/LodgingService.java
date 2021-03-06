package com.project.ws;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebServiceClient(name = "LodgingService", targetNamespace = "http://bookingxml.com/soap-example", wsdlLocation = "https://localhost:8443/api/ws/lodging.wsdl")
public class LodgingService
        extends Service {

    private final static URL LODGINGSERVICE_WSDL_LOCATION;
    private final static WebServiceException LODGINGSERVICE_EXCEPTION;
    private final static QName LODGINGSERVICE_QNAME = new QName("http://bookingxml.com/soap-example", "LodgingService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://localhost:8443/api/ws/lodging.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LODGINGSERVICE_WSDL_LOCATION = url;
        LODGINGSERVICE_EXCEPTION = e;
    }

    public LodgingService() {
        super(__getWsdlLocation(), LODGINGSERVICE_QNAME);
    }

    public LodgingService(WebServiceFeature... features) {
        super(__getWsdlLocation(), LODGINGSERVICE_QNAME, features);
    }

    public LodgingService(URL wsdlLocation) {
        super(wsdlLocation, LODGINGSERVICE_QNAME);
    }

    public LodgingService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LODGINGSERVICE_QNAME, features);
    }

    public LodgingService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LodgingService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    private static URL __getWsdlLocation() {
        if (LODGINGSERVICE_EXCEPTION != null) {
            throw LODGINGSERVICE_EXCEPTION;
        }
        return LODGINGSERVICE_WSDL_LOCATION;
    }

    /**
     * @return returns LodgingServicePort
     */
    @WebEndpoint(name = "LodgingServicePortSoap11")
    public LodgingServicePort getLodgingServicePortSoap11() {
        return super.getPort(new QName("http://bookingxml.com/soap-example", "LodgingServicePortSoap11"), LodgingServicePort.class);
    }

    /**
     * @param features A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns LodgingServicePort
     */
    @WebEndpoint(name = "LodgingServicePortSoap11")
    public LodgingServicePort getLodgingServicePortSoap11(WebServiceFeature... features) {
        return super.getPort(new QName("http://bookingxml.com/soap-example", "LodgingServicePortSoap11"), LodgingServicePort.class, features);
    }

}
