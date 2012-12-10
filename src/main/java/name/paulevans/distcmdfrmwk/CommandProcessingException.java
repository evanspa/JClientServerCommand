package name.paulevans.distcmdfrmwk;

/**
 * <p>Root of command processing exceptions.</p>
 * 
 * @author Paul R Evans
 *
 */
@SuppressWarnings("serial")
public class CommandProcessingException extends RuntimeException {

	/**
	 * <p>Creates a new command processing exception - allows for an
	 * exception to be wrapped.</p>
	 * 
	 * @param pCause causation exception to wrap
	 */
	public CommandProcessingException(Throwable pCause) {
		super (pCause);
	}
	
	/**
	 * <p>Creates a new command processing exception - allows for a custom
	 * message to be supplied and the cause-exception to be wrapped</p>
	 * 
	 * @param pMessage exception message
	 * @param pCause causation exception to wrap
	 */
	public CommandProcessingException(String pMessage, Throwable pCause) {
		super (pMessage, pCause);
	}
	
	/**
	 * <p>Creates a new command processing exception - allows for a custom
	 * message to be supplied</p>
	 * 
	 * @param pMessage exception message
	 */
	public CommandProcessingException(String pMessage) {
		super (pMessage);
	}
}
