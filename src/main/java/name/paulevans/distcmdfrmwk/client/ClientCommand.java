package name.paulevans.distcmdfrmwk.client;

import name.paulevans.distcmdfrmwk.Command;
import name.paulevans.distcmdfrmwk.CommandProcessingException;
import name.paulevans.distcmdfrmwk.client.cmddelgt.ClientCommandDelegate;

/**
 * <p>Marker interface to indicate the command object is intended for the
 * client (i.e., the implementation of its 'execute()' method will result
 * in a server-side counterpart command object.</p>
 * 
 * @author Paul R Evans
 *
 */
public interface ClientCommand extends Command {

	/**
	 * <p>Client command objects need to accept a client command delegate.  The
	 * implementation of a client command's 'execute()' method should be to
	 * invoke the 'execute()' method of the delegate, supplying itself as the
	 * argument.</p>
	 * 
	 * @param pClientCommandDelegate the command delegate to set
	 * 
	 * @throws CommandProcessingException if there is a problem executing
	 * the command
	 */
	void setClientCommandDelegate(final ClientCommandDelegate
			pClientCommandDelegate) throws CommandProcessingException;
}
