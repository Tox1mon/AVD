package de.ist_dresden.bt.easymock.demo;

import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Demo Test Case, welche die einfachst mögliche Nutzung von {@link EasyMock}
 * demonstrieren soll.
 * 
 * @author bjoern
 */
public class SimpleDelegationMockDemo extends AbstractDelegationMockDemo {

	/** Ein einfacher String. */
	private static final String HELLO_WORLD = "Hello, World";

	/**
	 * Prüfe, dass die Methode {@link ClassToDelegateTo#methodToDelegateTo()}
	 * genau einmal aufgerufen wird, wenn man
	 * {@link ClassUnderTest#methodUnderTest()} aufruft.
	 */
	@Test
	public void testDelegation() {
		EasyMock.expect(classToDelegateTo.methodToDelegateTo()).andReturn(
				HELLO_WORLD);
		EasyMock.replay(classToDelegateTo);
		classUnderTest.methodUnderTest();
		EasyMock.verify(classToDelegateTo);
	}
}
