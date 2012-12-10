package name.paulevans.distcmdfrmwk.client.cmddelgt;

import name.paulevans.distcmdfrmwk.client.ClientCommand;

/**
 * <p>Defines abstraction that client command objects will use to actually
 * perform their execution.  Client command objects can be considered
 * to be proxies to a server command object counterpart; it is this
 * delegate object abstraction that encapsulates the proxying 
 * strategy.</p>
 * 
 * <p>Providing this layer of indirection allows the implementation of how
 * a client command performs its own execution to be pluggable.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public interface ClientCommandDelegate {

	/**
	 * <p>Handles the proxying to the server command object counterpart.</p>
	 * 
	 * @param pCommand the client command object that serves as a proxy
	 * to a server command object counterpart
	 * 
	 * @return the result of the command processing
	 * 
	 * @throws ClientCommandDelegateException if there is an error invoking
	 * the server-side command "counterpart" of the supplied client command
	 */
	Object execute(ClientCommand pCommand) throws 
	ClientCommandDelegateException;
}
