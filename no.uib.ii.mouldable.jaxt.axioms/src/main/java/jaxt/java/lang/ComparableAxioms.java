package jaxt.java.lang;

import static jaxt.framework.Assert.succeed;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

// import static java.lang.Integer.signum;

/**
 * Required axioms for the java interface java.lang.Comparable as taken from <a
 * url="http://java.sun.com/j2se/1.5.0/docs/api/">Java 5.0 API</a>
 * <p>
 * This interface imposes a total ordering on the objects of each class that
 * implements it. This ordering is referred to as the class's natural ordering,
 * and the class's compareTo method is referred to as its natural comparison
 * method.
 * <p>
 * These are axioms for the requirements on implementations of the interface.
 * See separate files for recommendations.
 * 
 * @see java.lang.Comparable
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 */

abstract public class ComparableAxioms<T extends Comparable<T>> implements
		jaxt.framework.RequiredAxioms<Comparable<T>> {

	/**
	 * compareTo property 2: comparison with null.
	 * <p>
	 * e.compareTo(null) should throw a NullPointerException
	 */
	public static <T extends Comparable<T>> void compareToProperty2null(T e) {
		try {
			e.compareTo(null);
			fail(e + ".compareTo(null) should throw a NullPointerException");
		} catch (NullPointerException npe) {
			succeed();
			// As required
		}
	}

	/**
	 * compareTo property 3: natural ordering is LE (less than or equal to),
	 * hence antisymmetric.
	 * <p>
	 * For the mathematically inclined, the relation that defines the natural
	 * ordering on a given class C is:
	 * <p align=center>
	 * {(x, y) such that x.compareTo((Object)y) <= 0}.
	 * </p>
	 */
	public static <T extends Comparable<T>> void compareToProperty3antisymmetricLE(
			T x, T y) {
		try {
			if (x.compareTo(y) <= 0 && y.compareTo(x) <= 0)
				assertEquals("Compares (" + x.getClass() + ")" + x.toString()
						+ " (" + y.getClass() + ")" + y.toString(), x
						.compareTo(y), 0);
		} catch (RuntimeException e) {
			succeed();
			// some run-time exception occurred
		}
	}

	/**
	 * compareTo property 3: natural ordering is LE (less than or equal to),
	 * hence transitive.
	 * <p>
	 * For the mathematically inclined, the relation that defines the natural
	 * ordering on a given class C is:
	 * <p align=center>
	 * {(x, y) such that x.compareTo((Object)y) <= 0}.
	 * </p>
	 */
	public static <T extends Comparable<T>> void compareToProperty3transitiveLE(
			T a, T b, T c) {
		try {
			if (a.compareTo(b) <= 0 && b.compareTo(c) <= 0)
				assertTrue("Compares (" + a.getClass() + ")" + a.toString()
						+ " (" + b.getClass() + ")" + b.toString() + " ("
						+ c.getClass() + ")" + c.toString(),
						a.compareTo(c) <= 0);
		} catch (RuntimeException e) {
			succeed();
			// some run-time exception occurred
		}
	}

	/**
	 * compareTo property 3&4: the natural ordering is LE (less than or equal
	 * to) and an equivalence relation, hence reflexive.
	 * <p>
	 * For the mathematically inclined, the relation that defines the natural
	 * ordering on a given class C is:
	 * <p align=center>
	 * {(x, y) such that x.compareTo((Object)y) <= 0}.
	 * </p>
	 * <p>
	 * The quotient for this total order is:
	 * <p align=center>
	 * {(x, y) such that x.compareTo((Object)y) == 0}.
	 * </p>
	 */
	public static <T extends Comparable<T>> void compareToProperty4reflexive(T x) {
		try {
			assertEquals("Compares (" + x.getClass() + ")" + x.toString(), x
					.compareTo(x), 0);
		} catch (RuntimeException e) {
			succeed("some run-time exception occurred");
		}
	}

	/**
	 * compareTo property 4: the natural ordering is an equivalence relation,
	 * hence symmetric.
	 * <p>
	 * The quotient for this total order is:
	 * <p align=center>
	 * {(x, y) such that x.compareTo((Object)y) == 0}.
	 * </p>
	 */
	public static <T extends Comparable<T>> void compareToProperty4symmetricEQ(
			T x, T y) {
		try {
			if (x.compareTo(y) == 0)
				assertEquals("Compares (" + x.getClass() + ")" + x.toString()
						+ " (" + y.getClass() + ")" + y.toString(), y
						.compareTo(x), 0);
		} catch (RuntimeException e) {
			succeed("some run-time exception occurred");
		}
	}

	/**
	 * compareTo property 4: the natural ordering is an equivalence relation,
	 * hence transitive.
	 * <p>
	 * The quotient for this total order is:
	 * <p align=center>
	 * {(x, y) such that x.compareTo((Object)y) == 0}.
	 * </p>
	 */
	public static <T extends Comparable<T>> void compareToProperty4transitiveEQ(
			T x, T y, T z) {
		try {
			if (x.compareTo(y) == 0 && y.compareTo(z) == 0)
				assertEquals("Compares (" + x.getClass() + ")" + x.toString()
						+ " (" + y.getClass() + ")" + y.toString() + " ("
						+ z.getClass() + ")" + z.toString(), x.compareTo(z), 0);
		} catch (RuntimeException e) {
			succeed();
			// some run-time exception occurred
		}
	}

	/**
	 * compareTo property 5: total order.
	 * <p>
	 * In the foregoing description, the notation sgn(expression) designates the
	 * mathematical signum function, which is defined to return one of -1, 0, or
	 * 1 according to whether the value of expression is negative, zero or
	 * positive. The implementor must ensure sgn(x.compareTo(y)) ==
	 * -sgn(y.compareTo(x)) for all x and y.
	 */
	public static <T extends Comparable<T>> void compareToProperty5totalOrder(
			T x, T y) {
		try {
			assertEquals("Compares (" + x.getClass() + ")" + x.toString()
					+ " (" + y.getClass() + ")" + y.toString(), signum(x
					.compareTo(y)), -signum(y.compareTo(x)));
		} catch (RuntimeException e) {
			succeed("some run-time exception occurred");
		}
	}

	/**
	 * compareTo property 6: strong symmetry (both alternatives fail or both
	 * succeed).
	 * <p>
	 * This implies that x.compareTo(y) must throw an exception iff
	 * y.compareTo(x) throws an exception.
	 */
	public static <T extends Comparable<T>> void compareToProperty6strongSymmetry(
			T x, T y) {
		try {
			x.compareTo(y);
			y.compareTo(x);
			succeed("neither call fails");
		} catch (RuntimeException e) {
			// at least one of the calls throws an exception
			try {
				x.compareTo(y);
				fail(y + ".compareTo(" + x
						+ ") throws an exception while the converse does not");
			} catch (RuntimeException e1) {
				try {
					y.compareTo(x);
					fail(x
							+ ".compareTo("
							+ y
							+ ") throws an exception while the converse does not");
				} catch (RuntimeException e2) {
					succeed("OK! Both calls fail symmetrically");
				}
			}
		}
	}

	/**
	 * compareTo property 7: transitive GT (greater than).
	 * <p>
	 * The implementor must also ensure that the relation is transitive:
	 * (x.compareTo(y)>0 && y.compareTo(z)>0) implies x.compareTo(z)>0.
	 */
	public static <T extends Comparable<T>> void compareToProperty7transitiveGT(
			T x, T y, T z) {
		try {
			if (x.compareTo(y) > 0 && y.compareTo(z) > 0)
				assertTrue("Compares (" + x.getClass() + ")" + x.toString()
						+ " (" + y.getClass() + ")" + y.toString() + " ("
						+ z.getClass() + ")" + z.toString(), x.compareTo(z) > 0);
		} catch (RuntimeException e) {
			succeed("some run-time exception occurred");
		}
	}

	/**
	 * compareTo property 8: congruence wrt. ordering.
	 * <p>
	 * Finally, the implementer must ensure that x.compareTo(y)==0 implies that
	 * sgn(x.compareTo(z)) == sgn(y.compareTo(z)), for all z.
	 */
	public static <T extends Comparable<T>> void compareToProperty8congruenceCompareTo(
			T x, T y, T z) {
		try {
			if (x.compareTo(y) == 0)
				assertTrue("Compares (" + x.getClass() + ")" + x.toString()
						+ " (" + y.getClass() + ")" + y.toString() + " ("
						+ z.getClass() + ")" + z.toString(), signum(x
						.compareTo(z)) == signum(y.compareTo(z)));
		} catch (RuntimeException e) {
			succeed("some run-time exception occurred");
		}
	}

	public static int signum(int a) {
		return a < 0 ? -1 : a == 0 ? 0 : 1;
	}

}