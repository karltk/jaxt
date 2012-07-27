package jaxt.java.lang;

import static jaxt.framework.Assert.succeed;
import static junit.framework.Assert.assertEquals;

/**
 * Axions capturing strong recommendations for the java interface
 * java.lang.Comparable as taken from <a
 * url="http://java.sun.com/j2se/1.5.0/docs/api/">Java 5.0 API</a>
 * <p>
 * It is strongly recommended (though not required) that natural orderings be
 * consistent with equals. This is so because sorted sets (and sorted maps)
 * without explicit comparators behave "strangely" when they are used with
 * elements (or keys) whose natural ordering is inconsistent with equals. In
 * particular, such a sorted set (or sorted map) violates the general contract
 * for set (or map), which is defined in terms of the equals method.
 * <p>
 * For example, if one adds two keys a and b such that (!a.equals((Object)b) &&
 * a.compareTo((Object)b) == 0) to a sorted set that does not use an explicit
 * comparator, the second add operation returns false (and the size of the
 * sorted set does not increase) because a and b are equivalent from the sorted
 * set's perspective.
 * <p>
 * Virtually all Java core classes that implement comparable have natural
 * orderings that are consistent with equals. One exception is
 * java.math.BigDecimal, whose natural ordering equates BigDecimal objects with
 * equal values and different precisions (such as 4.0 and 4.00).
 * 
 * @see java.lang.Comparable
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 */

abstract public class ComparableAxiomsStronglyRecommended<T extends Comparable<T>>
		implements jaxt.framework.OptionalAxioms<Comparable<T>> {

	/**
	 * compareTo property 1 (and 9): the natural ordering is consistent with
	 * equals.
	 * <p>
	 * The natural ordering for a class C is said to be consistent with equals
	 * if and only if (e1.compareTo((Object)e2) == 0) has the same boolean value
	 * as e1.equals((Object)e2) for every e1 and e2 of class C.
	 * <p>
	 * It is strongly recommended, but not strictly required that
	 * (x.compareTo(y)==0) == (x.equals(y)). Generally speaking, any class that
	 * implements the Comparable interface and violates this condition should
	 * clearly indicate this fact. The recommended language is "Note: this class
	 * has a natural ordering that is inconsistent with equals."
	 */
	public static <T extends Comparable<T>> void compareToProperty1naturalOrderingEquals(
			T x, T y) {
		try {
			if (y != null)
				assertEquals(
						"Note: this class has a natural ordering that is inconsistent with equals.",
						x.equals(y), x.compareTo(y) == 0);
		} catch (RuntimeException e) {
			succeed("some run-time exception occurred");
		}
	}

}