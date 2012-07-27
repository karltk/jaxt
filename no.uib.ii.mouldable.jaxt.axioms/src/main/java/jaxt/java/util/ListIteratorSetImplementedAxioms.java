package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.ListIterator;

/**
 * This optional axiom should be enabled for sub-types of ListIterator where set
 * is implemented (does not throw UnsupportedOperationException).
 * 
 * @see java.util.ListIterator
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */

abstract public class ListIteratorSetImplementedAxioms<T> implements
		jaxt.framework.OptionalAxioms<java.util.ListIterator<T>> {

	public static <T> void setShouldNotThrowUnsupportedException(
			ListIterator<T> it, T t) {
		try {
			if (it.hasNext()) {
				it.next();
				it.set(t);
			}
		} catch (UnsupportedOperationException e) {
			fail("set() is required for this class, but threw UnsupportedOperationsException."
					+ e);

		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		}
	}
}
