package com.project.configuration;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.jws.WebService;

/**
 * Created by Ivan V. on 21-Jun-18
 */
public class SoapConnector extends WebServiceGatewaySupport {

    public Object callWebService(String url, Object request){
        return getWebServiceTemplate().marshalSendAndReceive(url, request);
    }
}
