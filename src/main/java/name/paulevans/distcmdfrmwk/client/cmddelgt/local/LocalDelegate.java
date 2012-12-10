package name.paulevans.distcmdfrmwk.client.cmddelgt.local;

import java.util.HashMap;
import java.util.Map;

import name.paulevans.distcmdfrmwk.Command;
import name.paulevans.distcmdfrmwk.client.ClientCommand;
import name.paulevans.distcmdfrmwk.client.cmddelgt.ClientCommandDelegate;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * <p>Simple implementation which assumes client and server command objects
 * are running within the same JVM.  These are the following steps for using
 * this implementation:
 *   <ul>
 *     <li>Instantiate this class</li>
 *     <li>Create mapping of client command classes to server command classes</li>
 *   </ul>
 * </p>
 * 
 * <p>The {@link #execute(ClientCommand)} method will first attempt to extract
 * the server command class that has been mapped to the class of the
 * supplied client command object.  The server class will be
 * instantiated (so that we can actually have a server command object 
 * to invoke).  Next the 'receiveCommand' method on the newly minted
 * server command object will be invoked - this gives the server command
 * object the opportunity to synchronize its state with the client
 * command object.  Finally the server command object will be executed.
 * </p> 
 * 
 * @author Paul R Evans
 *
 */
public class LocalDelegate implements ClientCommandDelegate {
	
	/**
	 * Holds mapping of client and server command classes.  Each client
	 * command class has a configured server-side command class.
	 */
	private Map<Class<? extends ClientCommand>,Class<? extends Command>> clientServerCommandMap = 
		new HashMap<Class<? extends ClientCommand>,Class<? extends Command>>();

	/**
	 * <p>Creates mapping between a client command class and a server
	 * command class.</p>
	 * 
	 * <p>Mappings created by this method will enable the 'proxy' behavior
	 * to succeed.</p>
	 * 
	 * @param pClientCmd client command class
	 * @param pServerCmd server command class
	 */
	public final void addMapping(final Class<? extends ClientCommand> pClientCmd, 
			final Class<? extends Command> pServerCmd) {
		clientServerCommandMap.put(pClientCmd, pServerCmd);
	}
	
	/**
	 * <p>Returns an instance of the server command class that is mapped
	 * to the class of the supplied client command object.</p>
	 * 
	 * @param pClientCmd client command object
	 * 
	 * @return instance of associated server command class
	 */
	private final Command getServerCommand(final ClientCommand pClientCmd) {
		
		Class<? extends Command> serverCmdClass;
		Command serverCmd;
		
		// get the server command class from the map..
		serverCmdClass = clientServerCommandMap.get(pClientCmd.getClass());
		
		try {
			// instantiate the server command instance...
			serverCmd = serverCmdClass.newInstance();
			
			// use reflection to populate state of server
			// command from client command...
			PropertyUtils.copyProperties(serverCmd, pClientCmd);
			
			// return it!
			return serverCmd;
			
		} catch (Exception any) {
			// no sense in having callers deal with reflection-related
			// exceptions; just re-throw anything caught as a runtime
			// exception...
			throw new RuntimeException(any);
		}
	}
	
	@Override
	public Object execute(final ClientCommand pCommand) {
		
		// get a server command object based on the supplied client command
		// object.  This server command object will have its state populated
		// with the same values as is stored within the client command object...
		Command serverCommand = getServerCommand(pCommand);
		
		// execute the server command object and return its return...
		return serverCommand.execute();
	}
}
