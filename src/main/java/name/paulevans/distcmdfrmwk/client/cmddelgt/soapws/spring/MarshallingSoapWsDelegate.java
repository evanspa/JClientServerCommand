package name.paulevans.distcmdfrmwk.client.cmddelgt.soapws.spring;

import name.paulevans.distcmdfrmwk.client.ClientCommand;
import name.paulevans.distcmdfrmwk.client.cmddelgt.ClientCommandDelegate;
import name.paulevans.distcmdfrmwk.client.cmddelgt.ClientCommandDelegateException;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceOperations;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.SoapFaultClientException;

/**
 * <p>Spring-WS based client command delegate.  This delegate can be used in cases
 * where the remoting strategy used to bridge client and server command
 * objects is a SOAP-based web service.</p>
 * 
 * <p>Users of this delegate would need to provide a Spring configuration
 * such that the web service template ({@link #setWebServiceOperations(WebServiceTemplate)})
 * object is configured with appropriate marshaller/unmarshallers, message
 * factory, default URI, etc.</p>
 * 
 * @author Paul R Evans
 *
 */
public class MarshallingSoapWsDelegate implements 
ClientCommandDelegate {
	
	/**
	 * Spring web service operations
	 */
	private WebServiceOperations wsOperations;
	
	/**
	 * Stores the soap action to be used on outgoing SOAP requests
	 */
	private String soapAction;

	@Override
	public Object execute(final ClientCommand pCommand) {
		
		try {
			// invoke the service and simply return the result of the invocation...
			return wsOperations.marshalSendAndReceive(pCommand,
					new WebServiceMessageCallback() {

				// set the soap action...
				public void doWithMessage(WebServiceMessage pMessage) {
					((SoapMessage)pMessage).setSoapAction(soapAction);
				}
			});
			
		} catch (SoapFaultClientException pSoapFaultClientException) {
			
			// convert client soap fault exception to generic delegate
			// exception...
			throw new ClientCommandDelegateException(
					pSoapFaultClientException.getFaultStringOrReason(),					
					pSoapFaultClientException);
		}
	}
	
	/**
	 * @param pWsOperations Spring web service operations object
	 */
	public final void setWebServiceOperations(final WebServiceOperations
			pWsOperations) {
		wsOperations = pWsOperations;
	}

	/**
	 * @param pSoapAction the SOAP action to set
	 */
	public final void setSoapAction(final String pSoapAction) {
		soapAction = pSoapAction;
	}
}
