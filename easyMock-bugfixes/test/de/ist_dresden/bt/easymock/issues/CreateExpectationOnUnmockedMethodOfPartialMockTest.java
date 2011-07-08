package de.ist_dresden.bt.easymock.issues;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Testcase for Issue 2796075
 * (http://sourceforge.net/tracker/?func=detail&aid=2796075
 * &group_id=82958&atid=567837)
 */
public class CreateExpectationOnUnmockedMethodOfPartialMockTest {

	/** Class which contains two methods, one to mock and one not to mock. */
	private static class Inner {

		/** Some Method which always returns true. */
		public boolean giveTrue() {
			return true;
		}

		/** Some method which always returns false. */
		public boolean giveFalse() {
			return false;
		}
	}

	/**
	 * The expect call on the unmocked method happens after a legal expect call
	 * on a really mocked method.
	 */
	@Test
	public void testMockUnmockedMethodAfterMockedMethod() {
		Inner instance = EasyMock.createMockBuilder(Inner.class)
				.addMockedMethod("giveTrue").createMock();
		EasyMock.expect(instance.giveTrue()).andReturn(true);
		EasyMock.expect(instance.giveFalse()).andReturn(true);
		EasyMock.replay(instance);
		instance.giveTrue();
		// If the mocked method succeeds, this should be true, otherwise false.
		// The bug says this leads to the real method being called, so the
		// assert should fail.
		// The goal would be to fail earlier.
		boolean shouldBeTrue = instance.giveFalse();
		Assert.assertTrue(shouldBeTrue);
	}

	/**
	 * The first expect call occurs on the unmocked method.
	 */
	@Test
	public void mockUnmockedMethodRightAway() {
		Inner instance = EasyMock.createMockBuilder(Inner.class)
				.addMockedMethod("giveTrue").createMock();
		EasyMock.expect(instance.giveFalse()).andReturn(true);
		EasyMock.replay(instance);
		// If the mocked method succeeds, this should be true, otherwise false.
		boolean shouldBeTrue = instance.giveFalse();
		Assert.assertTrue(shouldBeTrue);
	}

}
