package name.paulevans.distcmdfrmwk.server.cmdendpoint.ws;


import name.paulevans.distcmdfrmwk.Command;
import name.paulevans.distcmdfrmwk.CommandExecutor;
import name.paulevans.distcmdfrmwk.server.cmdendpoint.ws.SpringMarshallingPayloadEndpoint;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>Test for {@link name.paulevans.distcmdfrmwk.server.cmdendpoint.ws.SpringMarshallingPayloadEndpoint}
 * </p>
 * 
 * @author Paul R Evans
 *
 */
public class SpringMarshallingPayloadEndpointTest {
	
	/**
	 * Object that we'll use within our test methods
	 */
	private SpringMarshallingPayloadEndpoint endpoint;

	@Before
	public void setUp() throws Exception {
		
		// create our object...
		endpoint = new SpringMarshallingPayloadEndpoint();
	}
	
	/**
	 * <p>Test for
	 * {@link name.paulevans.distcmdfrmwk.server.cmdendpoint.ws.SpringMarshallingPayloadEndpoint#setCommandExecutor(CommandExecutor)}
	 * </p>
	 */
	@Test
	public void testSetCommandExecutor() {

		// sanity check...
		Assert.assertNotNull(endpoint);
		
		// invoke the method...
		endpoint.setCommandExecutor(new CommandExecutor());
		
		// nothing to assert...there is nothing to observe with respect
		// to the method-under-test...
	}
	
	/**
	 * <p>Test for
	 * {@link name.paulevans.distcmdfrmwk.server.cmdendpoint.ws.SpringMarshallingPayloadEndpoint#invokeInternal(Object)}
	 * </p>
	 * 
	 * <p>Will be leveraging a behavior-based test using mock objects
	 * (EasyMock).  The behavior that will be testing is that the 'execute'
	 * method of the endpoint's command executor will indeed be invoked, and,
	 * the object returned by the command executor is indeed returned by the
	 * invokeInternal() method.</p> 
	 */
	@Test public void testInvokeInternal() {
		
		CommandExecutor mockCmdExecutor;
		Command mockCmd;
		Object configuredExpectedResults;
		Object results;
		
		// sanity check...
		Assert.assertNotNull(endpoint);	
		
		// create mock command executor...
		mockCmdExecutor = EasyMock.createStrictMock(CommandExecutor.class);
		
		// create mock command...
		mockCmd = EasyMock.createStrictMock(Command.class);
		
		// create to-be result object of command execution...
		configuredExpectedResults = new Object();
		
		// both mocks are in 'record' mode...so now we're going to 
		// create the behavioral expectations...
		EasyMock.expect(mockCmdExecutor.executeCommand(mockCmd)).
			andReturn(configuredExpectedResults);
		
		// replay the mocks...
		EasyMock.replay(mockCmdExecutor);
		
		// set the command executor onto the endpoint...
		endpoint.setCommandExecutor(mockCmdExecutor);
		
		// perform testing...
		try {
			results = endpoint.invokeInternal(mockCmd);
			
			// assert the results is not null...
			Assert.assertNotNull(results);
			
			// assert the object returned is equal to the one configured to be
			// returned by our command executor mock...
			Assert.assertEquals(configuredExpectedResults, results);
			
			// verify...
			EasyMock.verify(mockCmdExecutor);
			
		} catch (Throwable any) {
			// intentionally left blank...
		}
	}
}
