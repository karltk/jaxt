package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.Collection;
import java.util.List;

/**
 * These axioms are required for implementations of List that does not allow
 * null values in the list.In that case the following methods should throw
 * NullPointerException if used with null as an argument, or in some cases a
 * list <i>containing</i> null as an argument:
 * <p>
 * <li><a
 * href="http://java.sun.com/j2se/1.5.0/docs/api/java/util/List.html#add(E)"
 * >add()</a></li>
 * <li><a href=
 * "http://java.sun.com/j2se/1.5.0/docs/api/java/util/List.html#add(int, E)"
 * >add(int, E)</a></li>
 * <li><a href="http://java.sun.com/j2se/1.5.0/docs/api/java/util/List.html#addAll(java.util.Collection)"
 * >addAll()</a></li>
 * <li><a href="http://java.sun.com/j2se/1.5.0/docs/api/java/util/List.html#addAll(int,java.util.Collection)"
 * >addAll(int,java.util.Collection)</a></li>
 * <li><a href="http://java.sun.com/javase/6/docs/api/java/util/List.html#contains(java.lang.Object)"
 * >contains()</a></li>
 * <li><a href="http://java.sun.com/javase/6/docs/api/java/util/List.html#containsAll(java.util.Collection)"
 * >containsAll() </a></li>
 * <li><a href="http://java.sun.com/javase/6/docs/api/java/util/List.html#removeAll(java.util.Collection)"
 * >removeAll() </a></li>
 * <li><a href="http://java.sun.com/javase/6/docs/api/java/util/List.html#retainAll(java.util.Collection)"
 * >retainAll()</a></li>
 * <li><a href=
 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#Set(int, E)">set()
 * </a></li>
 * <li><a href="http://java.sun.com/javase/6/docs/api/java/util/List.html#lastIndexOf(Object o)"
 * >lastIndexOf() </a></li>
 * <li><a href=
 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#indexOf(Object o)"
 * >indexOf() </a></li>
 * 
 * <p>
 * Note that in the cases of addAll, containsAll, removeAll and retainAll the
 * argument MUST be a Collection that allows null-values, otherwise it will not
 * be possible to test that the Collection under test does not allow them.
 * <p>
 * These axioms catches all the exceptions that the methods declares to throw,
 * because we must expect that they can be thrown because of the random test
 * data, but only NullPointerException is really a successful outcome. We are
 * not sure that this is the right way to handle this at this point. It doesn't
 * matter for the tests themselves, any of the exceptions is an accepted
 * outcome, but it may affect such things as measuring test-coverage and
 * testdata-quality later on.
 */

public class ListOptionalNullValuesNotSupported implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	/**
	 * Axiom for add()
	 * 
	 * @param <E>
	 * @param l
	 *            - The List that is tested
	 */

	public static <E> void addShouldThrowNullPointerException(List<E> l) {
		try {
			l.add(null);
			fail("add() should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException npe) {
			// Success()
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (IllegalArgumentException ilae) {
			// OK

		}
	}

	/**
	 * Axiom for add(int index, E element)
	 * 
	 * @param <E>
	 * @param l
	 *            - The List that is tested
	 * @param index
	 */

	// addByIndex
	public static <E> void addByIndexShouldThrowNullPointerException(List<E> l,
			E t, int index) {
		try {
			l.add(index, t);
			fail("add(index, t) should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// success()
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (IllegalArgumentException ilae) {
			// OK
		} catch (IndexOutOfBoundsException iobe) {
			// OK
		}
	}

	/**
	 * Axiom for addAll(Collection<? extends E> c)
	 * 
	 * @param <E>
	 * @param l
	 *            - The List that is tested
	 */

	// addAll
	public static <E> void addAllShouldThrowNullPointerException(
			Collection<? extends E> c, List<E> l, E t) {
		try {
			l.addAll(null);
			fail("addAll() should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// success
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (IllegalArgumentException ilae) {
			// OK
		}
	}

	/**
	 * Axiom for addAll(int index, Collection<? extends E> c)
	 * 
	 * @param <E>
	 * @param l
	 *            - The List that is tested
	 * @param index
	 */

	// addAllByIndex
	public static <E> void addAllByIndexShouldThrowNullPointerException(
			Collection<? extends E> c, List<E> l, int index) {
		try {
			l.addAll(index, c);
			fail("add(index, t) should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException npe) {
			// OK
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (IllegalArgumentException ilae) {
			// OK
		} catch (IndexOutOfBoundsException iobe) {
			// OK
		}
	}

	/**
	 * Axiom for contains(Object o)
	 * 
	 * @param <E>
	 * @param l
	 *            - The List that is tested
	 */

	// contains
	public static <E> void containsShouldThrowNullPointerException(List<E> l) {
		try {
			l.contains(null);
			fail("contains() should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// Success()

		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		}
	}

	/**
	 * Axiom for containsAll(Collection<?> c)
	 * 
	 * @param <E>
	 * @param l
	 *            - The List that is tested
	 */
	// containsAll
	public static <E> void containsAllShouldThrowNullPointerException(List<E> l) {
		try {
			l.containsAll(null);
			fail("containsAll() should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// success
		} catch (ClassCastException cce) {
			// OK
		}
	}

	/**
	 * Axiom for removeAll(Collection<?> c)
	 * 
	 * @param <E>
	 * @param l
	 *            - The List that is tested
	 */

	// removeAll
	public static <E> void removeAllShouldThrowNullPointerException(List<E> l) {
		try {
			l.removeAll(null);
			fail(" removeAll() should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// Success
		} catch (ClassCastException cce) {
			// OK
		} catch (UnsupportedOperationException uspe) {
			// OK
		}
	}

	/**
	 * Axiom for retainAll(Collection<?> c)
	 * 
	 * @param <E>
	 * @param l
	 *            - The List that is tested
	 */
	// retainAll
	public static <E> void retainAllShouldThrowNullPointerException(List<E> l) {
		try {
			l.retainAll(null);
			fail("retainAll()should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (UnsupportedOperationException uspe) {
			// OK
		}
	}

	/**
	 * Axiom for set(int index, E element)
	 * 
	 * @param <E>
	 * @param l
	 *            - The List that is tested
	 * @param i
	 */

	// set
	public static <E> void setShouldThrowNullPointerException(List<E> l, E t,
			int i) {
		try {
			l.set(i, null);
			fail("set()should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException e) {
			// Success
		} catch (UnsupportedOperationException uspe) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (IllegalArgumentException ilae) {
			// OK
		} catch (IndexOutOfBoundsException iobe) {
			// OK
		}
	}

	/**
	 * Axiom for lastIndexOf(Object o)
	 * 
	 * @param <E>
	 * @param l
	 *            - The List that is tested
	 * @param e
	 */

	// lastIndexOf
	public static <E> void returnTheLastIndexOfInList(List<E> l, E e) {

		try {
			l.lastIndexOf(null);
			fail("lastIndexOf() "
					+ "should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException npe) {
			// success
		} catch (ClassCastException cce) {
			// OK
		}

	}

	/**
	 * Axiom for indexOf(Object o)
	 * 
	 * @param <E>
	 * @param l
	 *            - The List that is tested
	 * @param e
	 */

	// indexOf
	public static <E> void returnTheFirstOccurenceOfInList(List<E> l, E e) {

		try {
			l.indexOf(null);
			fail("indexOf() "
					+ "should throw a NullpointerException when null values are not supported");
		} catch (NullPointerException npe) {
			// success
		} catch (ClassCastException cce) {
			// OK
		}

	}
}
