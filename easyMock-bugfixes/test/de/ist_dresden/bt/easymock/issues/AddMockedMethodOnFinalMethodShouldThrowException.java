package de.ist_dresden.bt.easymock.issues;

import org.easymock.EasyMock;
import org.easymock.internal.MockBuilder;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Testcase to demonstrate
 * "MockBuilder.addMockedMethod should fail for final methods - ID: 3052896"
 * (http://sourceforge.net/tracker/?func=detail&aid=3052896&group_id=82958&atid=
 * 567837).
 * 
 * @author bjoern
 */
public class AddMockedMethodOnFinalMethodShouldThrowException {

	/**
	 * Inner Class holding exactly one final Method.
	 * 
	 * @author bjoern
	 * 
	 */
	private static class Inner {

		/**
		 * Simple Method which cannot be overriden.
		 * 
		 * @return true.
		 */
		public final boolean finalMethod() {
			return true;
		}
	}

	/**
	 * Create an instance of {@link Inner}, use
	 * {@link MockBuilder#addMockedMethod(String)} to override the final method
	 * of that class and check if that an {@link IllegalArgumentException} is
	 * thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAPosteriori() {
		EasyMock.createMockBuilder(Inner.class).addMockedMethod("finalMethod")
				.createMock();
	}

	/**
	 * Demonstrate the behaviour of the bug. Here, we mock a method which is
	 * final and then set an expectation for this method.
	 */
	@Test
	@Ignore("After the Bugfix, this test cannot succeed")
	public void testAPriori() {
		Inner instance = EasyMock.createMockBuilder(Inner.class)
				.addMockedMethod("finalMethod").createMock();
		EasyMock.expect(instance.finalMethod()).andReturn(false);
	}

}
