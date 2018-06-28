package XMLandSecurity.backend1.configuration;

import XMLandSecurity.backend1.service.KeyStoreService;
import XMLandSecurity.backend1.service.impl.KeyStoreServiceImpl;
import XMLandSecurity.backend1.utility.XMLSigningUtility;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.w3c.dom.Document;

import java.security.PrivateKey;
import java.security.cert.Certificate;

@Component
public class GlobalEndpointInterceptor implements EndpointInterceptor {

    private static final Log LOG = LogFactory.getLog(GlobalEndpointInterceptor.class);


    private KeyStoreService keyStoreService;

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Global Request Handling");
        WebServiceMessage webServiceMessageRequest = messageContext.getRequest();
        SoapMessage soapMessage = (SoapMessage) webServiceMessageRequest;
        SoapHeader soapHeader = soapMessage.getSoapHeader();
        // IssuerData data = keyStoreService.readIssuerFromStore("6476022");
        keyStoreService = new KeyStoreServiceImpl();

        PrivateKey privateKey = keyStoreService.readPrivateKey("6476022");
        Certificate certificate = keyStoreService.readCertificate("6476022");


        XMLSigningUtility xmlSigningUtility = new XMLSigningUtility();
        Document document = xmlSigningUtility.signDocument(soapMessage.getDocument(), privateKey, certificate);

        // verifySignature

        soapMessage.setDocument(document);


        //        Source bodySource = soapHeader .getSource();
        //        DOMSource bodyDomSource = (DOMSource) bodySource;
        //        Node bodyNode = bodyDomSource.getNode();
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
        WebServiceMessage webServiceMessageRequest = messageContext.getRequest();
        SoapMessage soapMessage = (SoapMessage) webServiceMessageRequest;
        SoapHeader soapHeader = soapMessage.getSoapHeader();
        // IssuerData data = keyStoreService.readIssuerFromStore("6476022");
        keyStoreService = new KeyStoreServiceImpl();

        PrivateKey privateKey = keyStoreService.readPrivateKey("6476022");
        Certificate certificate = keyStoreService.readCertificate("6476022");

        XMLSigningUtility xmlSigningUtility = new XMLSigningUtility();
        Document document = xmlSigningUtility.signDocument(soapMessage.getDocument(), privateKey, certificate);

        soapMessage.setDocument(document);

        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Global Exception Handling");
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
        LOG.info("Execute Code After Completion");
    }
}