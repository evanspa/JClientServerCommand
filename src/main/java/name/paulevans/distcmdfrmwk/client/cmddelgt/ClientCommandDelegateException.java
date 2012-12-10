package name.paulevans.distcmdfrmwk.client.cmddelgt;

/**
 * <p>Root of client command delegate exceptions.</p>
 * 
 * @author Paul R Evans
 *
 */
@SuppressWarnings("serial")
public class ClientCommandDelegateException extends RuntimeException {

	/**
	 * <p>Creates a new client command delegate processing exception - allows
	 * for an exception to be wrapped.</p>
	 * 
	 * @param pCause causation exception to wrap
	 */
	public ClientCommandDelegateException(Throwable pCause) {
		super (pCause);
	}
	
	/**
	 * <p>Creates a new client command delegate processing exception - allows
	 * for a custom message to be supplied and the cause-exception to be
	 * wrapped</p>
	 * 
	 * @param pMessage exception message
	 * @param pCause causation exception to wrap
	 */
	public ClientCommandDelegateException(String pMessage, Throwable pCause) {
		super (pMessage, pCause);
	}
}
