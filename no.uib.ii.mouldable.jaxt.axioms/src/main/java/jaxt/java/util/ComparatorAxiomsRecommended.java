package jaxt.java.util;

import static junit.framework.Assert.assertEquals;

import java.util.Comparator;

/**
 * Axions capturing recommendations for the java interface java.util.Comparator
 * as taken from <a url="http://java.sun.com/j2se/1.5.0/docs/api/">Java 5.0 API</a>
 * <p>
 * The ordering imposed by a Comparator c on a set of elements S is said to be
 * consistent with equals if and only if (compare((Object)e1, (Object)e2)==0)
 * has the same boolean value as e1.equals((Object)e2) for every e1 and e2 in S.
 * <p>
 * Caution should be exercised when using a comparator capable of imposing an
 * ordering inconsistent with equals to order a sorted set (or sorted map).
 * Suppose a sorted set (or sorted map) with an explicit Comparator c is used
 * with elements (or keys) drawn from a set S. If the ordering imposed by c on S
 * is inconsistent with equals, the sorted set (or sorted map) will behave
 * "strangely." In particular the sorted set (or sorted map) will violate the
 * general contract for set (or map), which is defined in terms of equals.
 * <p>
 * For example, if one adds two keys a and b such that (a.equals((Object)b) &&
 * c.compare((Object)a, (Object)b) != 0) to a sorted set with comparator c, the
 * second add operation will return false (and the size of the sorted set will
 * not increase) because a and b are equivalent from the sorted set's
 * perspective.
 * 
 * @see java.util.Comparator
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 */

abstract public class ComparatorAxiomsRecommended<T> implements
		jaxt.framework.OptionalAxioms<Comparator<T>> {

	/**
	 * compare property 1 (and 9): the natural ordering is consistent with
	 * equals.
	 * <p>
	 * It is generally the case, but not strictly required that (compare(x,
	 * y)==0) == (x.equals(y)). Generally speaking, any comparator that violates
	 * this condition should clearly indicate this fact. The recommended
	 * language is "Note: this comparator imposes orderings that are
	 * inconsistent with equals."
	 */
	public static <T> void compareProperty1consistentOrderingEquals(Comparator<T> c, T x,
			T y) {
		try {
			if (y != null)
				assertEquals(
						"Note: this comparator imposes orderings that are inconsistent with equals.",
						x.equals(y), c.compare(x,y) == 0);
		} catch (RuntimeException e) {
			// some run-time exception occurred
		}
	}

}