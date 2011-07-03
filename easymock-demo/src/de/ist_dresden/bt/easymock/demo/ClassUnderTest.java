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
	 * Setter für Objekt, an welches delegiert werden soll. ACHTUNG: Dieser
	 * Setter ist nur für die Tests und für die Dependency Injection da. Er
	 * sollte im Production Scope nicht aufgerufen werden.
	 * 
	 * @param theClassToDelegateTo
	 *            Die {@link ClassToDelegateTo}.
	 */
	public void setClassToDelegate(ClassToDelegateTo theClassToDelegateTo) {
		this.classToDelegateTo = theClassToDelegateTo;
	}
}
