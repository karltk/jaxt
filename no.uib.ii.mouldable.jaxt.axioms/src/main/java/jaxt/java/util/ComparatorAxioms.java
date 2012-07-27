package jaxt.java.util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.util.Comparator;

// import static java.lang.Integer.signum;

/**
 * Required axioms for the java interface java.util.Comparator as taken from <a
 * url="http://java.sun.com/j2se/1.5.0/docs/api/">Java 5.0 API</a>
 * <p>
 * A comparison function, which imposes a total ordering on some collection of
 * objects. Comparators can be passed to a sort method (such as
 * Collections.sort) to allow precise control over the sort order. Comparators
 * can also be used to control the order of certain data structures (such as
 * TreeSet or TreeMap).
 * <p>
 * These are axioms for the requirements on implementations of the interface.
 * See separate files for recommendations.
 * 
 * @see java.util.Comparator
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 */

abstract public class ComparatorAxioms<T> implements
		jaxt.framework.RequiredAxioms<Comparator<T>> {

	/**
	 * compare property 1: natural ordering is LE (less than or equal to), hence
	 * antisymmetric.
	 * <p>
	 * For the mathematically inclined, the relation that defines the total
	 * order that a given comparator c imposes on a given set of objects S is:
	 * <p align=center> {(x, y) such that c.compare((Object)x, (Object)y) &lt;=
	 * 0}.
	 * </p>
	 */
	public static <T> void compareProperty3antisymmetricLE(Comparator<T> c,
			T x, T y) {
		try {
			if (c.compare(x, y) <= 0 && c.compare(y, x) <= 0)
				assertEquals(c.compare(x, y), 0);
		} catch (RuntimeException e) {
			// some run-time exception occurred
		}
	}

	/**
	 * compare property 3: natural ordering is LE (less than or equal to), hence
	 * transitive.
	 * <p>
	 * For the mathematically inclined, the relation that defines the natural
	 * ordering on a given class C is: <p align=center> {(x, y) such that
	 * x,(Object)y) <= 0}.
	 * </p>
	 */
	public static <T> void compareProperty3transitiveLE(Comparator<T> c, T x,
			T y, T z) {
		try {
			if (c.compare(x, y) <= 0 && c.compare(y, z) <= 0)
				assertTrue(c.compare(x, z) <= 0);
		} catch (RuntimeException e) {
			// some run-time exception occurred
		}
	}

	/**
	 * compare property 3&4: the natural ordering is LE (less than or equal to)
	 * and an equivalence relation, hence reflexive.
	 * <p>
	 * For the mathematically inclined, the relation that defines the natural
	 * ordering on a given class C is: <p align=center> {(x, y) such that
	 * x,(Object)y) <= 0}.
	 * </p>
	 * <p>
	 * The quotient for this total order is: <p align=center> {(x, y) such that
	 * x,(Object)y) == 0}.
	 * </p>
	 */
	public static <T> void compareProperty4reflexive(Comparator<T> c, T x) {
		try {
			assertEquals(c.compare(x, x), 0);
		} catch (RuntimeException e) {
			// some run-time exception occurred
		}
	}

	/**
	 * compare property 4: the natural ordering is an equivalence relation,
	 * hence symmetric.
	 * <p>
	 * The quotient for this total order is: <p align=center> {(x, y) such that
	 * x,(Object)y) == 0}.
	 * </p>
	 */
	public static <T> void compareProperty4symmetricEQ(Comparator<T> c, T x, T y) {
		try {
			if (c.compare(x, y) == 0)
				assertEquals(c.compare(y, x), 0);
		} catch (RuntimeException e) {
			// some run-time exception occurred
		}
	}

	/**
	 * compare property 4: the natural ordering is an equivalence relation,
	 * hence transitive.
	 * <p>
	 * The quotient for this total order is: <p align=center> {(x, y) such that
	 * x,(Object)y) == 0}.
	 * </p>
	 */
	public static <T> void compareProperty4transitiveEQ(Comparator<T> c, T x,
			T y, T z) {
		try {
			if (c.compare(x, y) == 0 && c.compare(y, z) == 0)
				assertEquals(c.compare(x, z), 0);
		} catch (RuntimeException e) {
			// some run-time exception occurred
		}
	}

	/**
	 * compare property 5: total order.
	 * <p>
	 * In the foregoing description, the notation sgn(expression) designates the
	 * mathematical signum function, which is defined to return one of -1, 0, or
	 * 1 according to whether the value of expression is negative, zero or
	 * positive. The implementor must ensure sgn(x,y)) == -sgn(y,x)) for all x
	 * and y.
	 */
	public static <T> void compareProperty5totalOrder(Comparator<T> c, T x, T y) {
		try {
			assertEquals(signum(c.compare(x, y)), -signum(c.compare(y, x)));
		} catch (RuntimeException e) {
			// some run-time exception occurred
		}
	}

	/**
	 * compare property 6: strong symmetry (both alternatives fail or both
	 * succeed).
	 * <p>
	 * This implies that x,y) must throw an exception iff y,x) throws an
	 * exception.
	 */
	public static <T> void compareProperty6strongSymmetry(Comparator<T> c, T x,
			T y) {
		try {
			c.compare(x, y);
			c.compare(y, x);
			// neither call fails
		} catch (RuntimeException e) {
			// at least one of the calls throws an exception
			try {
				c.compare(x, y);
				fail(y + "," + x
						+ ") throws an exception while the converse does not");
			} catch (RuntimeException e1) {
				try {
					c.compare(y, x);
					fail(x
							+ ","
							+ y
							+ ") throws an exception while the converse does not");
				} catch (RuntimeException e2) {
					// OK! Both calls fail symmetrically
				}
			}
		}
	}

	/**
	 * compare property 7: transitive GT (greater than).
	 * <p>
	 * The implementor must also ensure that the relation is transitive: (x,y)>0
	 * && y,z)>0) implies x,z)>0.
	 */
	public static <T> void compareProperty7transitiveGT(Comparator<T> c, T x,
			T y, T z) {
		try {
			if (c.compare(x, y) > 0 && c.compare(y, z) > 0)
				assertTrue(c.compare(x, z) > 0);
		} catch (RuntimeException e) {
			// some run-time exception occurred
		}
	}

	/**
	 * compare property 8: congruence wrt. ordering.
	 * <p>
	 * Finally, the implementer must ensure that x,y)==0 implies that sgn(x,z))
	 * == sgn(y,z)), for all z.
	 */
	public static <T> void compareProperty8congruenceCompareTo(Comparator<T> c,
			T x, T y, T z) {
		try {
			if (c.compare(x, y) == 0)
				assertTrue(signum(c.compare(x, z)) == signum(c.compare(y, z)));
		} catch (RuntimeException e) {
			// some run-time exception occurred
		}
	}

	public static int signum(int a) {
		return a < 0 ? -1 : a == 0 ? 0 : 1;
	}

}