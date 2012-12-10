package name.paulevans.distcmdfrmwk.client.cmddelgt;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>Simple test fixture for 
 * {@link name.paulevans.distcmdfrmwk.client.cmddelgt.ClientCommandDelegateException}
 * </p>
 * 
 * @author Paul R Evans
 *
 */
public class ClientCommandDelegateExceptionTest {

	@Test
	public final void testCommandProcessingException() {
		
		ClientCommandDelegateException clientCmdDelegateException;
		RuntimeException cause;
		
		// instantiate the exception object...
		clientCmdDelegateException = new ClientCommandDelegateException(
				cause = new RuntimeException("test"));
		
		// sanity check...
		Assert.assertNotNull(clientCmdDelegateException);
		
		// simply assert our exception wrapped the runtime exception
		// correctly...
		Assert.assertNotNull(clientCmdDelegateException.getCause());
		Assert.assertTrue(cause == clientCmdDelegateException.getCause());
	}
}
