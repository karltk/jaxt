package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.Collection;

/**
 * This optional axiom should be enabled for sub-types of Collection where clear
 * is <b>not</b> implemented (should throw UnsupportedOperationException).
 */
abstract public class CollectionOptionalClearNotImplementedAxioms<E> implements
		jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

	public static <E> void clearShouldThrowUnsupportedException(
			Collection<E> c, E t) {
		try {
			c.clear();
			fail("clear() should throw UnsupportedOperationsException when it is not implemented.");
		} catch (UnsupportedOperationException e) {
			// success();
		}
	}
}