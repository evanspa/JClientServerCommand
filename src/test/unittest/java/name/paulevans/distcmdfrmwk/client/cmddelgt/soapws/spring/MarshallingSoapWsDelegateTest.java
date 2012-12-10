package name.paulevans.distcmdfrmwk.client.cmddelgt.soapws.spring;

import static org.junit.Assert.fail;

import javax.xml.soap.MessageFactory;

import name.paulevans.distcmdfrmwk.client.ClientCommand;
import name.paulevans.distcmdfrmwk.client.ClientCommandStub;
import name.paulevans.distcmdfrmwk.client.cmddelgt.ClientCommandDelegateException;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceOperations;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

/**
 * <p>Test case for 
 * {@link name.paulevans.distcmdfrmwk.client.cmddelgt.soapws.spring.MarshallingSoapWsDelegate.MarshallingSoapWebServiceDelegate}
 * </p>
 * 
 * @author Paul R Evans
 *
 */
public class MarshallingSoapWsDelegateTest {
	
	/**
	 * Instance that we'll use within our test - i.e. our system-under-test
	 * (SUT) object
	 */
	private MarshallingSoapWsDelegate wsDelegate;

	@Before
	public void setUp() throws Exception {
		
		// create our system-under-test (SUT) object...
		wsDelegate = new MarshallingSoapWsDelegate();
	}
	
	/**
	 * <p>Test for 
	 * {@link name.paulevans.distcmdfrmwk.client.cmddelgt.soapws.spring.MarshallingSoapWsDelegate.MarshallingSoapWebServiceDelegate#execute(name.paulevans.distcmdfrmwk.client.ClientCommand)}
	 * </p>
	 * 
	 * <p>Because Spring's WebServiceOperations interface is not a trivial
	 * collaboration (it's an awkward one); we're going to use mocking and
	 * behavior-based verification (using EasyMock).</p>
	 * 
	 * <p>This test will test an 'unhappy path' - i.e., the mocked web
	 * service operations object will be configured such that its
	 * 'marshalSendAndReceive()' method will throw a soap client fault
	 * exception.</p>
	 */
	@Test public final void testExecute_soapClientFault() throws Exception {
		
		WebServiceOperations wsOpsMock; // mock
		
		// sanity check to ensure our SUT is not null...
		Assert.assertNotNull(wsDelegate);
		
		// create the ws operations mock (the mock is immediately in 
		// a 'record' mode after creation) and record the expectations...
		wsOpsMock = EasyMock.createMock(WebServiceOperations.class);
		
		// configure the mock such that when the 'marshalSendAndReceive' method
		// is invoked on it, that it will receive as input some instance of
		// type: ClientCommand as well as some instance of type:
		// WebServiceMessageCallback and, when invoked throw a soap client
		// fault exception...
		EasyMock.expect(wsOpsMock.marshalSendAndReceive(
				EasyMock.isA(ClientCommand.class), 
				EasyMock.isA(WebServiceMessageCallback.class))).andThrow(
						new SoapFaultClientException(new SaajSoapMessage(
								MessageFactory.newInstance().createMessage())));
		
		// put the mock into 'replay' mode...
		EasyMock.replay(wsOpsMock);
		
		// set the web service operations instance onto our SUT...
		wsDelegate.setWebServiceOperations(wsOpsMock);
		
		// do assertions...
		try {
			wsDelegate.execute(new ClientCommandStub());
			fail("should not have gotten this far...'execute()' should have " +
					"thrown a client command delegate exception");
		} catch (ClientCommandDelegateException pClientCmdDelegateException) {
			// intentionally empty...
		}

		
		// verify that the mock experienced the behavior that we said it
		// would (i.e. when we setup the expectations)...
		EasyMock.verify(wsOpsMock);		
	}	

	/**
	 * <p>Test for 
	 * {@link name.paulevans.distcmdfrmwk.client.cmddelgt.soapws.spring.MarshallingSoapWsDelegate.MarshallingSoapWebServiceDelegate#execute(name.paulevans.distcmdfrmwk.client.ClientCommand)}
	 * </p>
	 * 
	 * <p>Because Spring's WebServiceOperations interface is not a trivial
	 * collaboration (it's an awkward one); we're going to use mocking and
	 * behavior-based verification (using EasyMock).</p>
	 * 
	 * <p>This test will test the 'happy path' - i.e., the mocked web
	 * service operations object will be configured such that its
	 * 'marshalSendAndReceive()' method will return normally.</p>
	 */
	@Test public final void testExecute() throws Exception {
		
		WebServiceOperations wsOpsMock; // mock
		Object configuredResult;
		
		// sanity check to ensure our SUT is not null...
		Assert.assertNotNull(wsDelegate);
		
		// create the ws operations mock (the mock is immediately in 
		// a 'record' mode after creation) and record the expectations...
		wsOpsMock = EasyMock.createMock(WebServiceOperations.class);
		
		// configure the mock such that when the 'marshalSendAndReceive' method
		// is invoked on it, that it will receive as input some instance of
		// type: ClientCommand as well as some instance of type:
		// WebServiceMessageCallback and, when invoked will return the
		// 'configuredResult' object instance...
		EasyMock.expect(wsOpsMock.marshalSendAndReceive(
				EasyMock.isA(ClientCommand.class), 
				EasyMock.isA(WebServiceMessageCallback.class))).andReturn(
						configuredResult = new Object());
		
		// put the mock into 'replay' mode...
		EasyMock.replay(wsOpsMock);
		
		// set the web service operations instance onto our SUT...
		wsDelegate.setWebServiceOperations(wsOpsMock);
		
		// do assertions...in this case, the motivation behind our assertion
		// is different compared to non-mock based testing.  In non-mock
		// testing, we'd be asserting against the observable state of the
		// SUT.  In this case though, we're really asserting against the
		// knowledge of the implementation of the command.  here we're asserting
		// that: "yes, we know that 'execute()' returns the result of the
		// invocation of the marshalSendAndReceive, so, we're asserting that
		// this is actually happening...so, bottom-line is that this assert
		// has nothing to do with the contract of the 'execute' method...instead,
		// the "contract" that we're asserting is that the command's 'execute()'
		// method will return the same object returned by the call to
		// marshalSendAndReceive()...
		Object result = wsDelegate.execute(new ClientCommandStub());
		Assert.assertEquals(configuredResult, result);
		
		// verify that the mock experienced the behavior that we said it
		// would (i.e. when we setup the expectations)...
		EasyMock.verify(wsOpsMock);		
	}

	/**
	 * <p>Test for 
	 * {@link name.paulevans.distcmdfrmwk.client.cmddelgt.soapws.spring.MarshallingSoapWsDelegate.MarshallingSoapWebServiceDelegate#setWebServiceOperations(org.springframework.ws.client.core.WebServiceOperations)}
	 * </p>
	 */
	@Test public final void testSetWebServiceOperations() {
		
		// sanity check to ensure our SUT is not null...
		Assert.assertNotNull(wsDelegate);
		
		// set a ws operations object...if we don't get an exception, that's it, 
		// we're good.  Beyond this there is no external, observable behavior
		// in which to assert against...
		try {
			wsDelegate.setWebServiceOperations(new WebServiceTemplate());		
		} catch (Exception any) {
			fail("exception caught");
		}
	}

	/**
	 * <p>Test for 
	 * {@link name.paulevans.distcmdfrmwk.client.cmddelgt.soapws.spring.MarshallingSoapWsDelegate.MarshallingSoapWebServiceDelegate#setSoapAction(String)}
	 * </p>
	 */
	@Test public final void testSetSoapAction() {
		
		// sanity check to ensure our SUT is not null...
		Assert.assertNotNull(wsDelegate);
		
		// set the soap action...if we don't get an exception, that's it, 
		// we're good.  Beyond this there is no external, observable behavior
		// in which to assert against...
		try {
			wsDelegate.setSoapAction("soap action value");			
		} catch (Exception any) {
			fail("exception caught");
		}
	}
}
