package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.Collection;

/**
 * This optional axiom should be enabled for sub-types of Collection where clear
 * is implemented (does not throw UnsupportedOperationException).
 */
abstract public class CollectionOptionalClearImplementedAxioms<E> implements
		jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

	public static <E> void clearShouldNotThrowUnsupportedException(
			Collection<E> c) {
		try {
			c.clear();
		} catch (UnsupportedOperationException e) {
			fail("clear() is required for this class, but threw UnsupportedOperationsException."
					+ e);
		}
	}
}
