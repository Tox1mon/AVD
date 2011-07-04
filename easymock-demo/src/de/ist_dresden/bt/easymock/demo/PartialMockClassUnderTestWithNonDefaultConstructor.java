package de.ist_dresden.bt.easymock.demo;

/**
 * Klasse, in welcher die Methode, welche getestet werden soll, an eine Methode
 * innerhalb derselben Klasse delegiert. Derlei Dinge sieht man oft, aber meist
 * in der Form, dass man eine geerbte Methode aufruft.
 * 
 * @author bjoern
 */
public class PartialMockClassUnderTestWithNonDefaultConstructor {

	/** Instanziierung mit Default Konstruktor verhindern. */
	public PartialMockClassUnderTestWithNonDefaultConstructor(
			String instanceString) {
		this.myString = instanceString;
	}

	/**
	 * Eine Instanzvariable, die die Nutzung des Defaultkonstruktors unmöglich
	 * macht.
	 */
	private final String myString;

	/**
	 * Methode, an welche delegiert werden soll. Soll in Testcases gemocked
	 * werden.
	 * 
	 * @return Irgendeinen String.
	 */
	protected String methodToDelegateTo() {
		// Interna dieser Methode sind irrelevant für die Tests anderer Methoden
		throw new RuntimeException("Method is off scope");
	}

	/**
	 * Methode, welche einige Sachen an eine andere Methode delegiert, welche
	 * sich aber innerhalb derselben Klasse befindet. In der Praxis ist das oft
	 * der Fall, wenn geerbte Methoden den erbenden Klassen allgemeine
	 * Funktionalitäten bereit stellen. M.E. verstößt diese Praxis gegen das
	 * Mantra "Composition over inheritance" und ist damit meist unnötig
	 * kompliziert zu Testen. Viele Menschen jedoch finden diesen Ansatz
	 * natürlich, ergo hat er schon eine Berechtigung.
	 * 
	 * @return Das Ergebnis von {@link #methodToDelegateTo()}.
	 */
	public String methodUnderTest() {
		return methodToDelegateTo();
	}

	/**
	 * Liefert den Wert, welcher im Konstruktor übergeben wurde.
	 * 
	 * @return Ein String.
	 */
	public String getMyString() {
		return myString;
	}
}
