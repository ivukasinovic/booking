package com.project.utility;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.w3c.dom.Document;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.security.PrivateKey;
import java.security.cert.Certificate;

@Component
public class GlobalEndpointInterceptor implements EndpointInterceptor {

    private static final Log LOG = LogFactory.getLog(GlobalEndpointInterceptor.class);


    private KeyStoreServiceImpl keyStoreService;

    public static void sendPost(Document doc, String url) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        StringWriter writer = new StringWriter();
        StreamResult resw = new StreamResult(writer);

        transformer.transform(source, resw);

        URL obj = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/xml");
        connection.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(writer.toString());
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        System.out.println("\nSending 'POST' xml document over URL: " + url );
        System.out.println("Post parameters: " + writer.toString());
        System.out.println("Response code: " + responseCode);

        BufferedReader in = new BufferedReader (new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
    }

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Global Request Handling");
        WebServiceMessage webServiceMessageRequest = messageContext.getRequest();
        SoapMessage soapMessage = (SoapMessage) webServiceMessageRequest;
        SoapHeader soapHeader = soapMessage.getSoapHeader();
        // IssuerData data = keyStoreService.readIssuerFromStore("6476022");
        // soapHeader.addHeaderElement();

        keyStoreService = new KeyStoreServiceImpl();

        PrivateKey privateKey = keyStoreService.readPrivateKey("6476022");
        Certificate certificate = keyStoreService.readCertificate("6476022");

        XMLSigningUtility xmlSigningUtility = new XMLSigningUtility();
       Document document = xmlSigningUtility.signDocument(soapMessage.getDocument(),privateKey,certificate);

        soapMessage.setDocument(document);
        sendPost(document,"https://localhost:8443/api/ws");




        //        Source bodySource = soapHeader .getSource();
    //        DOMSource bodyDomSource = (DOMSource) bodySource;
        //        Node bodyNode = bodyDomSource.getNode();
        System.out.println("Lloajajajaja");
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
        Document document = xmlSigningUtility.signDocument(soapMessage.getDocument(),privateKey,certificate);

        soapMessage.setDocument(document);
        sendPost(document,"https://localhost:8443/api/ws");


        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Global Exception Handling");
        System.out.println("Lloajajajaja");
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
        LOG.info("Execute Code After Completion");
        System.out.println("Lloajajajaja");
    }
}