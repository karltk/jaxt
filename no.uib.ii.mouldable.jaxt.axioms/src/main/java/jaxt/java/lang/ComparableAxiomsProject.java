package jaxt.java.lang;

import static jaxt.framework.Assert.succeed;
import static junit.framework.Assert.assertEquals;

/**
 * Project specific recommendations as axioms for the java interface
 * java.lang.Comparable related to <a
 * url="http://java.sun.com/j2se/1.5.0/docs/api/">Java 5.0 API</a>
 * 
 * @see java.lang.Comparable
 * @see java.lang.Object
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 */

abstract public class ComparableAxiomsProject<T extends Comparable<T>>
		implements jaxt.framework.OptionalAxioms<Comparable<T>> {

	/**
	 * Sees the equals method as a congruence relation (smallest equivalence
	 * relation that yields full abstraction). This should generate congruence
	 * axioms for every declared interface and class.
	 * <p>
	 * This axiom is less restrictive than the strong recommendation that the
	 * natural ordering is consistent with equals.
	 */
	public static <T extends Comparable<T>> void congruenceCompareTo(T a, T b) {
		try {
			if (a.equals(b))
				assertEquals("Compares (" + a.getClass() + ")" + a.toString()
						+ " (" + b.getClass() + ")" + b.toString(), a
						.compareTo(b), 0);
		} catch (RuntimeException e) {
			succeed("some run-time exception occurred");
		}
	}

}