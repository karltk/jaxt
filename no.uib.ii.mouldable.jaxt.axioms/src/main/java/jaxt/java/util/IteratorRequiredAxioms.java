package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * These axioms are required for all implementations of <a
 * href="http://java.sun.com/javase/6/docs/api/java/util/Iterator.html">
 * java.util.<i>Iterator</></a></i>.
 * <p>
 * The <a href=
 * "http://java.sun.com/javase/6/docs/api/java/util/Iterator.html#remove()">
 * remove</a> method changes the underlying collection and that functionality
 * can not be tested here as we need access to the collection to examine it. In
 * order to teste this properly an extra axiom that tests the functionality of
 * Iterator.remove should therefore be added to the axioms for all classes that
 * implements <a href=
 * "http://java.sun.com/javase/6/docs/api/java/lang/Iterable.html">
 * java.util.<i>Iterable</i></a>.
 * 
 */
abstract public class IteratorRequiredAxioms<T> implements
		jaxt.framework.RequiredAxioms<java.util.Iterator<T>> {

	/**
	 * Axioms for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Iterator.html#next()">
	 * next()</a> and <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Iterator.html#hasNext()"
	 * > hasNext()</a>.
	 * <p>
	 * <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Iterator.html#hasNext()"
	 * > hasNext()</a>: Returns true if the iteration has more elements. (In
	 * other words, returns true if next would return an element rather than
	 * throwing an exception.)
	 * <p>
	 * <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Iterator.html#next()">
	 * next()</a>: Returns the next element in the iteration or
	 * NoSuchElementException if there are no more elements.
	 * 
	 * @param <T>
	 * @param iterator
	 *            an Iterator
	 */
	public static <T> void iteratorNextAndHasNext(Iterator<T> iterator) {
		if (iterator.hasNext()) {
			try {
				iterator.next();
			} catch (NoSuchElementException e) {
				fail("hasNext() was true, but next() threw NoSuchElementException");
			}
		} else {
			try {
				iterator.next();
				fail("hasNext() was false, but next() didn't throw NoSuchElementException");
			} catch (NoSuchElementException e) {
				// OK
			}
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Iterator.html#remove()">
	 * remove()</a>: Removes from the underlying collection the last element
	 * returned by the iterator (optional operation). It is not possible to test
	 * here that the element is really removed.
	 * <p>
	 * remove() can be called only once per call to next and should throw
	 * IllegalStateException if the next method has not yet been called, or the
	 * remove method has already been called after the last call to the next
	 * method.
	 * <p>
	 * remove() throws UnsupportedOperationException if the remove operation is
	 * not supported by this Iterator.
	 * 
	 * @param <T>
	 * @param iterator
	 */
	public static <T> void iteratorRemove(Iterator<T> iterator) {
		if (iterator.hasNext()) {
			iterator.next();
			try {
				iterator.remove();
				try {
					iterator.remove();
					fail("remove() should throw IllegalStateException when called more than once per call to next().");
				} catch (IllegalStateException e) {
					// OK
				}
			} catch (UnsupportedOperationException e) {
				// OK
			}
		}
	}
}