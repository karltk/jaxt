package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.ListIterator;

/**
 * This optional axiom should be enabled for sub-types of ListIterator where set
 * is <b>not</b> implemented (throws UnsupportedOperationException).
 * 
 * @see java.util.ListIterator
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */
abstract public class ListIteratorSetNotImplementedAxioms<T> implements
		jaxt.framework.OptionalAxioms<java.util.ListIterator<T>> {

	public static <T> void setShouldThrowUnsupportedException(
			ListIterator<T> it, T t) {
		try {
			if (it.hasNext()) {
				it.next();
				it.set(t);
				fail("set() is not supported for this class, but didn't throw UnsupportedOperationsException.");
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
