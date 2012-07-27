package jaxt.java.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * These axioms are required for implementations of Collection that allows null
 * values in the collection. In that case the following methods should not throw
 * NullPointerException if used with null, or in some cases a Collection
 * <i>containing</i> null, as an argument:
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
 */
abstract public class CollectionOptionalNullValuesSupportedAxioms<E> implements
		jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

	/**
	 * Axiom for add()
	 * 
	 * @param <E>
	 * @param c
	 *            - The Collection that is tested
	 */
	public static <E> void addShouldAllowNullValues(Collection<E> c) {
		try {
			c.add(null);
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
	public static <E> void removeShouldAllowNullValues(Collection<E> c) {
		try {
			c.remove(null);
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
	public static <E> void containsShouldAllowNullValues(Collection<E> c) {
		try {
			c.contains(null);
		} catch (ClassCastException e) {
			// OK
		}
	}

	/**
	 * Axiom for containsAll()
	 * 
	 * @param <E>
	 * @param c
	 *            - The Collection that is tested
	 * @param l
	 *            - The List of elements to be contained in the Collection.
	 */

	public static <E> void containsAllShouldAllowNullValues(Collection<E> c,
			ArrayList<E> l) {
		if (!l.contains(null))
			l.add(null);
		try {
			c.containsAll(l);
		} catch (ClassCastException e) {
			// OK
		}
	}

	/**
	 * Axiom for addAll()
	 * 
	 * @param <E>
	 * @param c
	 *            - The Collection that is tested
	 * @param l
	 *            - The List of elements to be added
	 */
	public static <E> void addAllShouldAllowNullValues(Collection<E> c,
			ArrayList<E> l) {
		if (!l.contains(null))
			l.add(null);
		try {
			c.addAll(l);
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
	 * @param c
	 *            - The Collection that is tested
	 * @param l
	 *            - The List of elements to be removed
	 */
	public static <E> void removeAllShouldAllowNullValues(Collection<E> c,
			ArrayList<E> l) {
		if (!l.contains(null))
			l.add(null);
		try {
			c.removeAll(l);
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
	 * @param c
	 *            - the Collection that is tested
	 * @param l
	 *            - The List of elements to be retained
	 */
	//
	public static <E> void retainAllShouldAllowNullValues(Collection<E> c,
			ArrayList<E> l) {
		if (!l.contains(null))
			l.add(null);
		try {
			c.retainAll(l);
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		}
	}
}
