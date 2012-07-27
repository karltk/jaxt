package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.Collection;

/**
 * This optional axiom should be enabled for sub-types of Collection where
 * removeAll is implemented (does not throw UnsupportedOperationException).
 */
abstract public class CollectionOptionalRemoveAllImplementedAxioms<E>
		implements jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

	public static <E> void removeAllShouldNotThrowUnsupportedException(
			Collection<E> c1, Collection<E> c2) {
		try {
			c1.removeAll(c2);
		} catch (UnsupportedOperationException e) {
			fail("removeAll() is required for this class, but threw UnsupportedOperationsException."
					+ e);

		} catch (ClassCastException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		}
	}
}
