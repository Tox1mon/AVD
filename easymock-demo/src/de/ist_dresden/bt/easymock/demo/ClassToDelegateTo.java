package de.ist_dresden.bt.easymock.demo;

/**
 * Intderface, an welches die Class under Test delegiert.
 * 
 * @author bjoern
 */
public interface ClassToDelegateTo {

	/**
	 * Methode ohne R�ckgabewert und keinen Parametern.
	 */
	public void methodToDelegateToWithNoReturnValue();

	/**
	 * Methode mit einem R�ckgabewert und keinen Parametern.
	 * 
	 * @return Irgendein String.
	 */
	public String methodToDelegateTo();

}
