package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.ListIterator;

/**
 * This optional axiom should be enabled for sub-types of ListIterator where
 * remove is implemented (does not throw UnsupportedOperationException).
 * 
 * @see java.util.Iterator
 * @see java.util.ListIterator
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */
abstract public class ListIteratorRemoveImplementedAxioms<T> implements
		jaxt.framework.OptionalAxioms<java.util.ListIterator<T>> {

	public static <T> void removeShouldNotThrowUnsupportedException(
			ListIterator<T> it) {
		try {
			if (it.hasNext()) {
				it.next();
				it.remove();
			}
		} catch (UnsupportedOperationException e) {
			fail("remove() is required for this class, but threw UnsupportedOperationsException."
					+ e);

		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		}
	}
}
