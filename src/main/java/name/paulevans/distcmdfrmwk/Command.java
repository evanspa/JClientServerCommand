package name.paulevans.distcmdfrmwk;


/**
 * <p>Generic command definition.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 */
public interface Command {
	
	/**
	 * <p>Executes this command</p>
	 * 
	 * @return the result of executing this command
	 * 
	 * @throws CommandProcessingException if there is a problem executing
	 * the command
	 */
	Object execute() throws CommandProcessingException;

}
