package de.ist_dresden.bt.easymock.demo;

import org.easymock.EasyMock;
import org.junit.Before;

/**
 * Abstrakte Superklasse für alle Tests, damit wir nicht immer und immer wieder
 * die Class under Test und die Delegates instanziieren müssen.
 * 
 * @author bjoern
 */
public abstract class AbstractDelegationMockDemo {

	/** Class under Test. */
	protected ClassUnderTest classUnderTest;

	/** Mock Delegate. */
	protected ClassToDelegateTo classToDelegateTo;

	/**
	 * Initialisiere die Class under Test und alle Mocks/Delegates.
	 */
	@Before
	public void init() {
		classToDelegateTo = EasyMock.createMock(ClassToDelegateTo.class);
		classUnderTest = new ClassUnderTest();
		classUnderTest.setClassToDelegate(classToDelegateTo);
	}
}
