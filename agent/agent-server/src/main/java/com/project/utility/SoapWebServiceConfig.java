package com.project.utility;

import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;

import java.util.List;

/**
 * Created by Ivan V. on 04-Jun-18
 */
@EnableWs
@Configuration
public class SoapWebServiceConfig extends WsConfigurerAdapter {

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        // register global interceptor
        interceptors.add(new GlobalEndpointInterceptor());
    }

}
