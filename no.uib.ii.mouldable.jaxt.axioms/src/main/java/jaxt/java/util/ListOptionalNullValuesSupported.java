package jaxt.java.util;

import java.util.Collection;
import java.util.List;

/**
 * These axioms are required for implementations of List that allow null values
 * in the list.In that case the following methods should throw
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
 * argument MUST be a List that allows null-values, otherwise it will not be
 * possible to test that the Collection under test does not allow them.
 * <p>
 */

public class ListOptionalNullValuesSupported implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	/**
	 * Axiom for add()
	 * 
	 * @param <E>
	 * @param l
	 *            - The List that is tested
	 */

	public static <E> void addShouldAllNullValues(List<E> l) {
		try {
			l.add(null);

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
	public static <E> void addByIndexShouldAllowNullValues(List<E> l, E t,
			int index) {
		try {
			l.add(null);
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
	public static <E> void addAllShouldAllowNullValues(
			Collection<? extends E> c, List<E> l, E t) {
		try {
			l.addAll(null);
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
	public static <E> void addAllByIndexShouldAllowNullValues(
			Collection<? extends E> c, List<E> l, int index) {
		try {
			l.addAll(null);

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
	public static <E> void containsShouldAllowNullValues(List<E> l) {
		try {
			l.contains(null);
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
	public static <E> void containsAllShouldAllowNullValues(List<E> l) {
		try {
			l.containsAll(null);

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
	public static <E> void removeAllShouldAllowNullValues(List<E> l) {
		try {
			l.removeAll(null);

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
	public static <E> void retainAllShouldAllowNullValues(List<E> l) {
		try {
			l.retainAll(null);

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
	public static <E> void setShouldAllowNullValues(List<E> l, E t, int i) {
		try {
			l.set(i, null);

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
	public static <E> void returnTheLastIndexOfInListAllowNullValues(List<E> l,
			E e) {

		try {
			l.lastIndexOf(null);
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
	public static <E> void returnTheFirstOccurenceOfInListAllowNullValues(
			List<E> l, E e) {

		try {
			l.indexOf(null);

		} catch (ClassCastException cce) {
			// OK
		}

	}
}
