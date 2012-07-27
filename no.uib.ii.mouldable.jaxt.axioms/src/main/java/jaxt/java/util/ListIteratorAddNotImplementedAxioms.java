package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.ListIterator;

/**
 * This optional axiom should be enabled for sub-types of ListIterator where add
 * is <b>not</b> implemented (throws UnsupportedOperationException).
 * 
 * @see java.util.ListIterator
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */
abstract public class ListIteratorAddNotImplementedAxioms<T> implements
		jaxt.framework.OptionalAxioms<java.util.ListIterator<T>> {

	public static <T> void addShouldThrowUnsupportedException(
			ListIterator<T> it, T t) {
		try {
			it.add(t);
			fail("add() is not supported for this class, but didn't throw UnsupportedOperationsException.");
		} catch (UnsupportedOperationException e) {
			// success();

		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		}
	}
}
