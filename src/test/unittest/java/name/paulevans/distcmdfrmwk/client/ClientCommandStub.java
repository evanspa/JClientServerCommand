package name.paulevans.distcmdfrmwk.client;

import name.paulevans.distcmdfrmwk.client.cmddelgt.ClientCommandDelegate;

/**
 * <p>Stub client command implementation to be used for unit testing purposes
 * </p>
 * 
 * @author Paul Evans
 *
 */
public class ClientCommandStub implements ClientCommand {

	@Override
	public Object execute() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setClientCommandDelegate(
			ClientCommandDelegate pClientCommandDelegate) {
		// intentionally left blank...
	}
}
