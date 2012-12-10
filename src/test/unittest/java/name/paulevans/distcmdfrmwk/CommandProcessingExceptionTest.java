package name.paulevans.distcmdfrmwk;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>Simple test fixture for {@link name.paulevans.distcmdfrmwk.CommandProcessingException
 * </p>
 * 
 * @author Paul R Evans
 *
 */
public class CommandProcessingExceptionTest {

	@Test
	public final void testCommandProcessingException() {
		
		CommandProcessingException cmdProcessingException;
		RuntimeException cause;
		
		// instantiate the exception object...
		cmdProcessingException = new CommandProcessingException(
				cause = new RuntimeException("test"));
		
		// sanity check...
		Assert.assertNotNull(cmdProcessingException);
		
		// simply assert our exception wrapped the runtime exception
		// correctly...
		Assert.assertNotNull(cmdProcessingException.getCause());
		Assert.assertTrue(cause == cmdProcessingException.getCause());
	}
}
