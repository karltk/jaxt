package jaxt.java.util;

import static junit.framework.Assert.assertEquals;

import java.util.Comparator;

/**
 * Project specific recommendations as axioms for the java interface
 * java.util.Comparator related to <a
 * url="http://java.sun.com/j2se/1.5.0/docs/api/">Java 5.0 API</a>
 * 
 * @see java.util.Comparator
 * @see java.lang.Object
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 */

abstract public class ComparatorAxiomsProject<T> implements
		jaxt.framework.OptionalAxioms<Comparator<T>> {

	/**
	 * Sees the equals method as a congruence relation (smallest equivalence
	 * relation that yields full abstraction). This should generate congruence
	 * axioms for every declared interface and class.
	 * <p>
	 * This axiom is less restrictive than the recommendation that compare is
	 * consistent with equals.
	 */
	public static <T> void congruenceCompareTo(Comparator<T> c, T x, T y) {
		try {
			if (x.equals(y))
				assertEquals(c.compare(x, y), 0);
		} catch (RuntimeException e) {
			// some run-time exception occurred
		}
	}

}