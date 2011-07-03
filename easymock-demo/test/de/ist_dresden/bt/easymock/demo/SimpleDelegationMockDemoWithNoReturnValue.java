package de.ist_dresden.bt.easymock.demo;

import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Klasse, welche demonstrieren soll, wie man EasyMock nutzt, wenn die Methode,
 * an welche Delegiert wird, keinen RŸckgabewert hat.
 * 
 * @author bjoern
 * 
 */
public class SimpleDelegationMockDemoWithNoReturnValue extends
		AbstractDelegationMockDemo {

	/**
	 * Zeige die Nutzung von {@link EasyMock#expectLastCall()} beim Mocken von
	 * Methoden, welche keinen RŸckgabewert haben.
	 */
	@Test
	public void testDelegation() {
		classToDelegateTo.methodToDelegateToWithNoReturnValue();
		EasyMock.expectLastCall();
		EasyMock.replay(classToDelegateTo);
		classUnderTest.methodUnderTestWithVoidDelegateMethod();
		EasyMock.verify(classToDelegateTo);
	}
}
