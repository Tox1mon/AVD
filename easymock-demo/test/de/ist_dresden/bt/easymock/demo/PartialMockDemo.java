package de.ist_dresden.bt.easymock.demo;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * Methode, welche einen einfachen Anwendungsfall für einen Partial Mock
 * aufzeigt.
 * 
 * @author bjoern
 */
public class PartialMockDemo {

	/**
	 * Die Class under Test. Wichtig ist, dass die Methode, welche getestet
	 * wird, an eine Methode derselben Klasse delegiert. Diese Methode, an
	 * welche delegiert wird, soll dann mit EasyMock weggemocked werden.
	 */
	private PartialMockClassUnderTest partialMockClassUnderTest;

	/**
	 * Initialisiere die Class under Test. In diesem Falle muss sie als Partial
	 * Mock initialisiert werden, damit beim Testen von
	 * {@link PartialMockClassUnderTest#methodUnderTest()} der Aufruf von
	 * {@link PartialMockClassUnderTest#methodToDelegateTo()} weggemocked wird.
	 */
	@Before
	public void init() {
		partialMockClassUnderTest = EasyMock
				.createMockBuilder(PartialMockClassUnderTest.class)
				.addMockedMethod("methodToDelegateTo").createMock();
	}

	/**
	 * Setze die Expectation auf dem Partial Mock und prüfe, dass das hinhaut.
	 */
	@Test
	public void test() {
		EasyMock.expect(partialMockClassUnderTest.methodToDelegateTo())
				.andReturn("Hello World");
		EasyMock.replay(partialMockClassUnderTest);
		partialMockClassUnderTest.methodUnderTest();
		EasyMock.verify(partialMockClassUnderTest);
	}

}
