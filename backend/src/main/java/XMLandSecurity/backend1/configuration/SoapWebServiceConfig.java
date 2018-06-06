package XMLandSecurity.backend1.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Created by Ivan V. on 04-Jun-18
 */
@EnableWs
@Configuration
public class SoapWebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean
    public XsdSchema lodgingSchema(){
       return new SimpleXsdSchema(new ClassPathResource("booking.xsd"));
    }

    @Bean("lodging")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema lodgingSchema){
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setSchema(lodgingSchema);
        definition.setLocationUri("/ws");
        definition.setPortTypeName("LodgingServicePort");
        definition.setServiceName("LodgingService");
        return definition;
    }

//    @Bean
//    public XsdSchema bookingSchema(){
//        return new SimpleXsdSchema(new ClassPathResource("booking.xsd"));
//    }
//
//    @Bean("booking")
//    public DefaultWsdl11Definition defaultWsdl11DefinitionBooking(XsdSchema bookingSchema){
//        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
//        definition.setSchema(bookingSchema);
//        definition.setLocationUri("/ws");
//        definition.setPortTypeName("BookingServicePort");
//        definition.setServiceName("BookingService");
//        return definition;
//    }
}
