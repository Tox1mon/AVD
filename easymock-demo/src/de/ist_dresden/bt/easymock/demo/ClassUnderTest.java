package de.ist_dresden.bt.easymock.demo;

/**
 * Klasse, welche getestet wird.
 * 
 * @author bjoern
 */
public class ClassUnderTest {

	/**
	 * Klasse, an welche manche Dinge delegiert werden.
	 */
	private ClassToDelegateTo classToDelegateTo;
	
	/**
	 * Methode, welche getestet werden soll.
	 */
	public void methodUnderTest() {
		classToDelegateTo.methodToDelegateTo();
	}

	/**
	 * Setter f�r Objekt, an welches delegiert werden soll. ACHTUNG: Dieser
	 * Setter ist nur f�r die Tests und f�r die Dependency Injection da. Er
	 * sollte im Production Scope nicht aufgerufen werden.
	 * 
	 * @param theClassToDelegateTo
	 *            Die {@link ClassToDelegateTo}.
	 */
	public void setClassToDelegate(ClassToDelegateTo theClassToDelegateTo) {
		this.classToDelegateTo = theClassToDelegateTo;
	}
}
