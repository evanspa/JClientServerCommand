package name.paulevans.distcmdfrmwk.client.cmddelgt.local;

import name.paulevans.distcmdfrmwk.Command;

/**
 * <p>Stub command implementation to be used for unit testing purposes - 
 * specifically for unit testing the 
 * {@link name.paulevans.distcmdfrmwk.client.cmddelgt.local.LocalDelegate}
 * client command delegate implementation.</p>
 * 
 * @author Paul Evans
 *
 */
public class CommandStub implements Command {

	/**
	 * Returns an empty object
	 */
	@Override public Object execute() {
		return new Object();
	}
}
