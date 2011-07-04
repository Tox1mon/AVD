package de.ist_dresden.bt.easymock.demo;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * Methode, welche einen einfachen Anwendungsfall für einen Partial Mock
 * aufzeigt.
 * 
 * @author bjoern
 */
public class PartialMockWithNonDefaultConstructorDemo {

	/** Willkürlich gewählter Wert. */
	private static final String HELLO_WORLD = "Hello World";

	/**
	 * Die Class under Test. Wichtig ist, dass die Methode, welche getestet
	 * wird, an eine Methode derselben Klasse delegiert. Diese Methode, an
	 * welche delegiert wird, soll dann mit EasyMock weggemocked werden.
	 */
	private PartialMockClassUnderTestWithNonDefaultConstructor partialMockClassUnderTest;

	/**
	 * Initialisiere die Class under Test. In diesem Falle muss sie als Partial
	 * Mock initialisiert werden, damit beim Testen von
	 * {@link PartialMockClassUnderTest#methodUnderTest()} der Aufruf von
	 * {@link PartialMockClassUnderTest#methodToDelegateTo()} weggemocked wird.
	 */
	@Before
	public void init() {
		partialMockClassUnderTest = EasyMock
				.createMockBuilder(
						PartialMockClassUnderTestWithNonDefaultConstructor.class)
				.withConstructor(HELLO_WORLD)
				.addMockedMethod("methodToDelegateTo").createMock();
		Assert.assertEquals(HELLO_WORLD,
				partialMockClassUnderTest.getMyString());
	}

	/**
	 * Setze die Expectation auf dem Partial Mock und prüfe, dass das hinhaut.
	 */
	@Test
	public void test() {
		EasyMock.expect(partialMockClassUnderTest.methodToDelegateTo())
				.andReturn(HELLO_WORLD);
		EasyMock.replay(partialMockClassUnderTest);
		partialMockClassUnderTest.methodUnderTest();
		EasyMock.verify(partialMockClassUnderTest);
	}

}
