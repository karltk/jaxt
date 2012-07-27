package jaxt.java.lang;

import static jaxt.framework.Assert.succeed;
import static junit.framework.Assert.assertEquals;

/**
 * Project specific recommendations as axioms for the java interface
 * java.lang.Object related to <a
 * url="http://java.sun.com/j2se/1.5.0/docs/api/">Java 5.0 API</a>
 * 
 * @see java.lang.Object
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 */

abstract public class ObjectAxiomsProject implements
		jaxt.framework.OptionalAxioms<Object> {

	/**
	 * Sees the equals method as a congruence relation (smallest equivalence
	 * relation that yields full abstraction). This should generate congruence
	 * axioms for every declared interface and class.
	 * <p>
	 * The method hashCode() is required to satisfy this axiom. The method
	 * toString() should also satisfy it.
	 * 
	 * @see jaxt.java.lang.ObjectAxioms#hashCodeProperty2congruenceEquals(java.lang.Object,
	 *      java.lang.Object)
	 */
	public static void congruenceToString(Object a, Object b) {
		try {
			if (a.equals(b))
				assertEquals("Compares (" + a.getClass() + ")" + a.toString()
						+ " (" + b.getClass() + ")" + b.toString(), a
						.toString(), b.toString());
		} catch (RuntimeException e) {
			succeed("some run-time exception occurred");
		}
	}

}