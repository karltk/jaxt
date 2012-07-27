package jaxt.java.util;

import static junit.framework.Assert.*;

import java.util.Iterator;

/**
 * These axioms are required for implementations of the interface <a
 * href="http://java.sun.com/javase/6/docs/api/java/util/Iterator.html">
 * java.util.<i>Iterator</></a></i> if the optional remove() method <b>is
 * not</b> implemented.
 */
abstract public class IteratorOptionalRemoveNotImplementedAxioms<T> implements
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
	public static <T> void removeIsNotSupported(Iterator<T> iterator) {
		if (iterator.hasNext()) {
			iterator.next();
			try {
				iterator.remove();
				fail("remove() is not supported but didn't throw UnsupportedOperationException");
			} catch (UnsupportedOperationException e) {
				// success();

			} catch (IllegalStateException e) {
				// OK
			}
		}
	}

}