package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.ListIterator;

/**
 * This optional axiom should be enabled for sub-types of ListIterator where
 * remove is <b>not</b> implemented (throws UnsupportedOperationException).
 * 
 * @see java.util.Iterator
 * @see java.util.ListIterator
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */
abstract public class ListIteratorRemoveNotImplementedAxioms<T> implements
		jaxt.framework.OptionalAxioms<java.util.ListIterator<T>> {

	public static <T> void removeShouldThrowUnsupportedException(
			ListIterator<T> it) {
		try {
			if (it.hasNext()) {
				it.next();
				it.remove();
				fail("add() is not supported for this class, but didn't throw UnsupportedOperationsException.");
			}

		} catch (UnsupportedOperationException e) {
			// success();

		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		}
	}
}
