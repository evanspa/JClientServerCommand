package name.paulevans.distcmdfrmwk.client.cmddelgt.local;

import name.paulevans.distcmdfrmwk.client.ClientCommandStub;
import name.paulevans.distcmdfrmwk.client.cmddelgt.local.LocalDelegate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>Test for 
 * {@link name.paulevans.distcmdfrmwk.client.cmddelgt.local.LocalDelegate}</p>
 * 
 * @author Paul R Evans
 *
 */
public class LocalDelegateTest {
	
	/**
	 * We'll use this object in our testing.
	 */
	private LocalDelegate localDelegate;
	
	/**
	 * <p>Used to perform initializations before each test method execution</p>
	 */
	@Before
	public void setUp() {
		
		// instantiate our 'system-under-test' object...
		localDelegate = new LocalDelegate();
	}
	
	/**
	 * <p>Test for {@link name.paulevans.distcmdfrmwk.client.cmddelgt.local.LocalDelegate#addMapping(Class, Class)}
	 * in which a proper mapping is created and results in the proper
	 * functioning of the 'execute' method.
	 * </p>
	 * 
	 */
	@Test
	public void testProperMapping() {
		
		Object object;
	
		// sanity check...
		Assert.assertNotNull(localDelegate);
		
		// create mapping between some stub classes...
		localDelegate.addMapping(ClientCommandStub.class, 
				CommandStub.class);
		
		// invoke client command (which will result in server command
		// 'counterpart' from being instantiated and its 'execute' method
		// being invoked)...
		object = localDelegate.execute(new ClientCommandStub());
		
		// assert the object returned is not null - this is effectively our
		// way of knowing the mapping is correct...
		Assert.assertNotNull(object);
	}
	
	/**
	 * <p>Test for {@link name.paulevans.distcmdfrmwk.client.cmddelgt.local.LocalDelegate#addMapping(Class, Class)}
	 * in which there is no mapping for the class of the client command
	 * object.</p>
	 */
	@Test
	public void testAbsentMapping() {
	
		// sanity check...
		Assert.assertNotNull(localDelegate);
		
		// invoke client command (which will result in server command
		// 'counterpart' from being instantiated and its 'execute' method
		// being invoked)...
		try {
			
			// we never created a mapping, so, this call should result in
			// an exception being thrown...
			localDelegate.execute(new ClientCommandStub());
			Assert.fail("should not have reached this point");
		
		} catch (Exception any) {
			// intentionally left blank...
		}
	}
}
