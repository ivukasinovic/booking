//package com.project.utility;
//
///**
// * Created by Dejan Stojkic (Smek) on 6/15/2018.
// */
//
//import java.io.IOException;
//import java.io.InputStream;
//import javax.security.auth.callback.Callback;
//import javax.security.auth.callback.CallbackHandler;
//import javax.security.auth.callback.UnsupportedCallbackException;
//import javax.xml.soap.SOAPMessage;
//
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.core.io.Resource;
//import org.springframework.util.Assert;
//import org.springframework.ws.context.MessageContext;
//import org.springframework.ws.soap.SoapMessage;
//import org.springframework.ws.soap.saaj.SaajSoapMessage;
//
//
//public class XwsSecurityInterceptor extends AbstractWsSecurityInterceptor implements InitializingBean {
//
//    private XWSSProcessor processor;
//
//    private CallbackHandler callbackHandler;
//
//    private Resource policyConfiguration;
//
//    /**
//     * Sets the handler to resolve XWSS callbacks. Setting either this propery, or {@code callbackHandlers}, is
//     * required.
//     *
//     * @see com.sun.xml.wss.impl.callback.XWSSCallback
//     * @see #setCallbackHandlers(javax.security.auth.callback.CallbackHandler[])
//     */
//    public void setCallbackHandler(CallbackHandler callbackHandler) {
//        this.callbackHandler = callbackHandler;
//    }
//
//    /**
//     * Sets the handlers to resolve XWSS callbacks. Setting either this propery, or {@code callbackHandlers}, is
//     * required.
//     *
//     * @see com.sun.xml.wss.impl.callback.XWSSCallback
//     * @see #setCallbackHandler(javax.security.auth.callback.CallbackHandler)
//     */
//    public void setCallbackHandlers(CallbackHandler[] callbackHandler) {
//        this.callbackHandler = new XwssCallbackHandlerChain(callbackHandler);
//    }
//
//    /** Sets the policy configuration to use for XWSS. Required. */
//    public void setPolicyConfiguration(Resource policyConfiguration) {
//        this.policyConfiguration = policyConfiguration;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        Assert.notNull(policyConfiguration, "policyConfiguration is required");
//        Assert.isTrue(policyConfiguration.exists(), "policyConfiguration [" + policyConfiguration + "] does not exist");
//        Assert.notNull(callbackHandler, "callbackHandler is required");
//        XWSSProcessorFactory processorFactory = XWSSProcessorFactory.newInstance();
//        InputStream is = null;
//        try {
//            if (logger.isInfoEnabled()) {
//                logger.info("Loading policy configuration from from '" + policyConfiguration + "'");
//            }
//            is = policyConfiguration.getInputStream();
//            processor = processorFactory.createProcessorForSecurityConfiguration(is, callbackHandler);
//        }
//        finally {
//            if (is != null) {
//                is.close();
//            }
//        }
//    }
//
//    /**
//     * Secures the given SoapMessage message in accordance with the defined security policy.
//     *
//     * @param soapMessage the message to be secured
//     * @throws XwsSecuritySecurementException in case of errors
//     * @throws IllegalArgumentException    when soapMessage is not a {@code SaajSoapMessage}
//     */
//    @Override
//    protected void secureMessage(SoapMessage soapMessage, MessageContext messageContext)
//            throws XwsSecuritySecurementException {
//        Assert.isTrue(soapMessage instanceof SaajSoapMessage, "XwsSecurityInterceptor requires a SaajSoapMessage. " +
//                "Use a SaajSoapMessageFactory to create the SOAP messages.");
//        SaajSoapMessage saajSoapMessage = (SaajSoapMessage) soapMessage;
//        try {
//            ProcessingContext context = processor.createProcessingContext(saajSoapMessage.getSaajMessage());
//            SOAPMessage result = processor.secureOutboundMessage(context);
//            saajSoapMessage.setSaajMessage(result);
//        }
//        catch (XWSSecurityException ex) {
//            throw new XwsSecuritySecurementException(ex.getMessage(), ex);
//        }
//        catch (WssSoapFaultException ex) {
//            throw new XwsSecurityFaultException(ex.getFaultCode(), ex.getFaultString(), ex.getFaultActor());
//        }
//    }
//
//    /**
//     * Validates the given SoapMessage message in accordance with the defined security policy.
//     *
//     * @param soapMessage the message to be validated
//     * @throws XwsSecurityValidationException in case of errors
//     * @throws IllegalArgumentException    when soapMessage is not a {@code SaajSoapMessage}
//     */
//    @Override
//    protected void validateMessage(SoapMessage soapMessage, MessageContext messageContext)
//            throws WsSecurityValidationException {
//        Assert.isTrue(soapMessage instanceof SaajSoapMessage, "XwsSecurityInterceptor requires a SaajSoapMessage. " +
//                "Use a SaajSoapMessageFactory to create the SOAP messages.");
//        SaajSoapMessage saajSoapMessage = (SaajSoapMessage) soapMessage;
//        try {
//            ProcessingContext context = processor.createProcessingContext(saajSoapMessage.getSaajMessage());
//            SOAPMessage result = processor.verifyInboundMessage(context);
//            saajSoapMessage.setSaajMessage(result);
//        }
//        catch (XWSSecurityException ex) {
//            throw new XwsSecurityValidationException(ex.getMessage(), ex);
//        }
//        catch (WssSoapFaultException ex) {
//            throw new XwsSecurityFaultException(ex.getFaultCode(), ex.getFaultString(), ex.getFaultActor());
//        }
//    }
//
//    @Override
//    protected void cleanUp() {
//        if (callbackHandler != null) {
//            try {
//                CleanupCallback cleanupCallback = new CleanupCallback();
//                callbackHandler.handle(new Callback[]{cleanupCallback});
//            }
//            catch (IOException ex) {
//                logger.warn("Cleanup callback resulted in IOException", ex);
//            }
//            catch (UnsupportedCallbackException ex) {
//                // ignore
//            }
//        }
//    }
//}