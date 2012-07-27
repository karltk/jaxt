package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

/**
 * These axioms are required for implementations of Collection that does NOT
 * allow null values in the collection. In that case the following methods
 * should throw NullPointerException if used with null, or in some cases a
 * Collection <i>containing</i> null, as an argument:
 * <p>
 * <li><a href=
 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#add(E)"
 * >add()</a></li>
 * <li><a href=
 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#remove(E)"
 * >remove()</a></li>
 * <li><a href=
 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#contains(E)"
 * >contains()</a></li>
 * <li><a href="http://java.sun.com/javase/6/docs/api/java/util/Collection.html#addAll(java.util.Collection)"
 * > addAll() </a></li>
 * <li><a href="http://java.sun.com/javase/6/docs/api/java/util/Collection.html#removeAll(java.util.Collection)"
 * > removeAll() </a></li>
 * <li><a href="http://java.sun.com/javase/6/docs/api/java/util/Collection.html#containsAll(java.util.Collection)"
 * > containsAll() </a></li>
 * <li><a href="http://java.sun.com/javase/6/docs/api/java/util/Collection.html#retainAll(java.util.Collection)"
 * > retainAll() </a></li>
 * <p>
 * In these axioms we choose to use ArrayList as the argument for addAll,
 * containsAll, removeAll and retainAll instead of Collection because we need to
 * be certain that the argument is something that allows null as a value.
 * <p>
 * These axioms catches all the exceptions that the methods declares to throw,
 * because we must expect that they can be thrown because of the random test
 * data, but only NullPointerException is really a successful outcome. We are
 * not sure that this is the right way to handle this at this point. It doesn't
 * matter for the tests themselves, any of the exceptions is an accepted
 * outcome, but it may affect such things as measuring test-coverage and
 * testdata-quality later on.
 */
abstract public class CollectionOptionalNullValuesNotSupported<E> implements
		jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

	/**
	 * Axiom for add()
	 * 
	 * @param <E>
	 * @param c
	 *            - The Collection that is tested
	 */
	public static <E> void addShouldThrowNullPointerException(Collection<E> c) {
		try {
			c.add(null);
			fail("add() should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// success();
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		} catch (IllegalStateException e) {
			// OK
		}

	}

	/**
	 * Axiom for remove()
	 * 
	 * @param <E>
	 * @param c
	 *            - The Collection that is tested
	 */
	public static <E> void removeShouldThrowNullPointerException(Collection<E> c) {
		try {
			c.remove(null);
			fail("remove() should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// success();
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		}
	}

	/**
	 * Axiom for contains()
	 * 
	 * @param <E>
	 * @param c
	 *            - The Collection that is tested
	 */
	public static <E> void containsShouldThrowNullPointerException(
			Collection<E> c) {
		try {
			c.contains(null);
			fail("contains() should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// success();
		} catch (ClassCastException e) {
			// OK
		}
	}

	/**
	 * Axiom for containsAll()
	 * 
	 * @param <E>
	 * @param c1
	 *            - The Collection that is tested
	 * @param c2
	 *            - The List of elements to be contained in the Collection.
	 */

	public static <E> void containsAllShouldThrowNullPointerException(
			Collection<E> c, ArrayList<E> l) {
		if (!l.contains(null))
			l.add(null);

		try {
			c.containsAll(l);
			fail("containsAll() should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// success();
		} catch (ClassCastException e) {
			// OK
		}

	}

	/**
	 * Axiom for addAll()
	 * 
	 * @param <E>
	 * @param c1
	 *            - The Collection that is tested
	 * @param c2
	 *            - The List of elements to be added
	 */
	public static <E> void addAllShouldThrowNullPointerException(
			Collection<E> c, ArrayList<E> l) {
		if (!l.contains(null))
			l.add(null);

		try {
			c.addAll(l);
			fail("addAll() should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// success();
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		} catch (IllegalStateException e) {
			// OK
		}
	}

	/**
	 * Axiom for removeAll()
	 * 
	 * @param <E>
	 * @param c1
	 *            - The Collection that is tested
	 * @param c2
	 *            - The List of elements to be removed
	 */
	public static <E> void removeAllShouldThrowNullPointerException(
			Collection<E> c, ArrayList<E> l) {
		if (!l.contains(null))
			l.add(null);

		try {
			c.removeAll(l);
			fail("removeAll() should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// success();
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		}
	}

	/**
	 * Axiom for retainAll()
	 * 
	 * @param <E>
	 * @param c1
	 *            - the Collection that is tested
	 * @param c2
	 *            - The List of elements to be retained
	 */
	//
	public static <E> void retainAllShouldThrowNullPointerException(
			Collection<E> c, ArrayList<E> l) {
		if (!l.contains(null))
			l.add(null);

		try {
			c.retainAll(l);
			fail("retainAll() should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// success();
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		}
	}
}
