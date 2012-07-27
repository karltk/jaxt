package jaxt.java.util;

import static junit.framework.Assert.*;

import java.util.Iterator;

/**
 * These axioms are required for implementations of the interface <a
 * href="http://java.sun.com/javase/6/docs/api/java/util/Iterator.html">
 * java.util.<i>Iterator</></a></i> if the optional remove() method is
 * implemented.
 */
abstract public class IteratorOptionalAxiomsRemoveImplemented<T> implements
		jaxt.framework.OptionalAxioms<java.util.Iterator<T>> {

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Iterator.html#remove()">
	 * remove()</a>: "throws UnsupportedOperationException if the remove
	 * operation is not supported by this Iterator."
	 * 
	 * @param <T>
	 * @param iterator
	 *            the Iterator to be tested
	 */
	public static <T> void removeIsSupported(Iterator<T> iterator) {
		try {
			if (iterator.hasNext()) {
				iterator.next();
				iterator.remove();
			}
		} catch (UnsupportedOperationException e) {
			fail("remove() is supported but threw UnsupportedOperationException. "
					+ e);

		} catch (IllegalStateException e) {
			// OK
		}
	}
}