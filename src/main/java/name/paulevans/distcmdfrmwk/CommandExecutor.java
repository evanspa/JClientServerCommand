package name.paulevans.distcmdfrmwk;

/**
 * <p>Mechanism to execute commands and handle any sort of exception one might
 * throw.  Any exception caught is simply re-thrown as a runtime exception.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class CommandExecutor {
	
	/**
	 * <p>Executes the command object and returns the result of the
	 * command's {@link name.paulevans.distcmdfrmwk.Command#execute()}
	 * method.</p>
	 * 
	 * @param pCommand command object
	 * 
	 * @return the result of the 
	 * command's {@link name.paulevans.distcmdfrmwk.Command#execute()}
	 * method
	 */
	public Object executeCommand(Command pCommand) {
		try {
			return pCommand.execute();
		} catch (Exception pAny) {
			
			// only re-throw checked exceptions as command processing
			// exceptions...
			if (!(pAny instanceof CommandProcessingException)) {
				throw new CommandProcessingException(pAny);
			} else {
				throw (CommandProcessingException)pAny;
			}
		}
	}
}
