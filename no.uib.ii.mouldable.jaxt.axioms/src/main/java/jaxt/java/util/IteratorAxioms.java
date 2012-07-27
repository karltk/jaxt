package jaxt.java.util;

import static junit.framework.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Required axioms for the java interface java.util.Iterator as taken from <a
 * url="http://java.sun.com/j2se/1.5.0/docs/api/">Java 5.0 API</a>
 * <p>
 * An iterator over a collection. Iterator takes the place of Enumeration in the
 * Java collections framework. Iterators differ from enumerations in two ways:
 * <ul>
 * <li> Iterators allow the caller to remove elements from the underlying
 * collection during the iteration with well-defined semantics. </li>
 * <li> Method names have been improved. </li>
 * </ul>
 * <p>
 * These are axioms for the requirements on implementations of the interface.
 * See separate files for recommendations.
 * 
 * @see java.util.Iterator
 * @see java.util.Enumeration
 * @see java.lang.Iterable
 * 
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 */

abstract public class IteratorAxioms<E> implements
		jaxt.framework.RequiredAxioms<Iterator<E>> {

	/**
	 * hasNext property 1: failure of next method
	 * <p>
	 * Returns true if the iteration has more elements. (In other words, returns
	 * true if next would return an element rather than throwing an exception.)
	 */
	public static <E> void hasNextProperty1nextFailure(Iterator<E> e) {
		try {
			if (e.hasNext()) {
				e.next();
				return;
			}
			e.next();
			fail();
		} catch (NoSuchElementException e1) {
			// As required
		}
	}

	/**
	 * remove property 1: failure modes.
	 * <p>
	 * Removes from the underlying collection the last element returned by the
	 * iterator (optional operation). This method can be called only once per
	 * call to next. The behavior of an iterator is unspecified if the
	 * underlying collection is modified while the iteration is in progress in
	 * any way other than by calling this method.
	 * 
	 */
	public static <E> void removeProperty1failureModes(Iterator<E> e) {
		try {
			try {
				e.remove();
			} catch (UnsupportedOperationException e1) {
				// if the remove operation is not supported by this Iterator.
				return;
			}
		} catch (IllegalStateException e2) {
			// if the next method has not yet been called, or the remove method
			// has already been called after the last call to the next method.
			return;
		}
		try {
			e.remove();
			fail("Exception missing: the remove method has already been called after the last call to the next method");
		} catch (IllegalStateException e1) {
			// OK
			return;
		}
	}

}