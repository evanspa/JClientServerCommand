package name.paulevans.distcmdfrmwk;


import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>Test for {@link name.paulevans.distcmdfrmwk.CommandExecutor}
 * </p>
 * 
 * @author Paul Evans
 *
 */
public class CommandExecutorTest {
	
	/**
	 * We'll use this instance within our test methods
	 */
	private CommandExecutor commandExecutor;

	/**
	 * <p>Used to perform initializations before each test method execution</p>
	 */
	@Before
	public void setUp() {
		
		// create our instance that we'll be testing with...
		commandExecutor = new CommandExecutor();
	}
	
	/**
	 * <p>Test to ensure a command object's 'execute' method gets
	 * invoked.</p>
	 */
	@Test
	public void testExecuteCommand() {
		
		// result of command execution...
		Object cmdResults = new Object();
		
		// create our command mock (it's now in 'record' mode)...
		Command mockCmd = EasyMock.createStrictMock(Command.class);
		
		// record the expectations...
		EasyMock.expect(mockCmd.execute()).andReturn(cmdResults);
		
		// put the mock into 'replay' mode...
		EasyMock.replay(mockCmd);
		
		// execute our system-under-test (SUT)...
		commandExecutor.executeCommand(mockCmd);
		
		// verify that the mock experienced the behaviors we defined
		// just above...
		EasyMock.verify(mockCmd);
	}
	
	/**
	 * <p>Test to ensure CommandProcessingException thrown
	 * at runtime by a command will be caught and re-thrown.</p>
	 */
	@Test public void testCmdProccessingException() {
		
		// create our command mock...
		Command mockCmd = EasyMock.createStrictMock(Command.class);
		
		// expectations...configure the mock to thrown an illegal
		// state exception...
		EasyMock.expect(mockCmd.execute()).andThrow(
				new CommandProcessingException(new RuntimeException()));
		
		// replay the mock...
		EasyMock.replay(mockCmd);
		
		try {
			// execute our system-under-test (SUT)...
			commandExecutor.executeCommand(mockCmd);
			Assert.fail("should have thrown a RuntimeException");
		} catch (CommandProcessingException exc) {
			// nothing to do...
		}
	}
	
	/**
	 * <p>Test to ensure exceptions other-than CommandProcessingException thrown
	 * at runtime will be caught and re-thrown as a CommandProcessingException.</p>
	 */
	@Test public void testException() {
		
		// create our command mock...
		Command mockCmd = EasyMock.createStrictMock(Command.class);
		
		// expectations...configure the mock to thrown an illegal
		// state exception...
		EasyMock.expect(mockCmd.execute()).andThrow(
				new IllegalStateException());
		
		// replay the mock...
		EasyMock.replay(mockCmd);
		
		try {
			// execute our system-under-test (SUT)...
			commandExecutor.executeCommand(mockCmd);
			Assert.fail("should have thrown a RuntimeException");
		} catch (CommandProcessingException exc) {
			// nothing to do...
		}
	}
}
