package name.paulevans.distcmdfrmwk.server.cmdendpoint.ws;

import name.paulevans.distcmdfrmwk.Command;
import name.paulevans.distcmdfrmwk.CommandExecutor;

import org.springframework.ws.server.endpoint.AbstractMarshallingPayloadEndpoint;

/**
 * <p>Simple endpoint class - all it does is 'execute' the command object that
 * was un-marshaled from the request XML payload (the marshaling/un-marshaling
 * is handled by the Spring-WS framework.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class SpringMarshallingPayloadEndpoint extends 
AbstractMarshallingPayloadEndpoint {
	
	/**
	 * Command executor will be used to actually invoke the 'exeucte()'
	 * method on unmarshaled command objects.  Why?  The command executor
	 * will gracefully handle any exceptions thrown by the command.
	 * 
	 * The command executor holds no state so we are free to use just a single
	 * reference.
	 */
	private CommandExecutor commandExecutor;
	
	/**
	 * Sets the command executor
	 * 
	 * @param pCmdExecutor the new command executor object
	 */
	public final void setCommandExecutor(CommandExecutor pCmdExecutor) {
		commandExecutor = pCmdExecutor;
	}

	@Override
	protected Object invokeInternal(Object pUnmarshaledRequest)
	throws Exception {
		
		Object commandResult;
		Command command;
		
		// cast-down for convenience...
		command = (Command)pUnmarshaledRequest;
		
		// log the class of the un-marshaled command object...
		logger.info("request un-marshaled into command object of type: [" + 
				command.getClass().getName() + "]");
		
		// execute the command!
		commandResult = commandExecutor.executeCommand(command);
		
		// log the result of the command execution...
		logger.info("command result: [" + commandResult.toString() + "]");
		
		// return the command result!
		return commandResult;
	}
}
