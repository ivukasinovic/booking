package com.project.configuration;

import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

/**
 * Created by Ivan V. on 21-Jun-18
 */
public class SoapInterceptor implements ClientInterceptor {
    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        System.out.println("lola");
        return false;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        System.out.println("lola");
        return false;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        System.out.println("lola");
        return false;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception e) throws WebServiceClientException {
        System.out.println("lola");

    }
}
