package com.project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by Ivan V. on 21-Jun-18
 */
@Configuration
public class Config {

    @Bean
    public WebServiceTemplate mwWebServiceTemplate() {
        WebServiceTemplate template = new WebServiceTemplate();
        template.setDefaultUri("https://osb-st2.vs.csin.cz:5001/CSMW/WS_MW_ESignatureProcessor_v02_02");
        template.setInterceptors(new SoapInterceptor[]{new SoapInterceptor()});
        return template;
    }

}
