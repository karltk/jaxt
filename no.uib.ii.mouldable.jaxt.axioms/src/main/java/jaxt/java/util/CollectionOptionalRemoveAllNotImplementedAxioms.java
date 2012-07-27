package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.Collection;

/**
 * This optional axiom should be enabled for sub-types of Collection where
 * removeAll is <b>not</b> implemented (should throw
 * UnsupportedOperationException).
 */
abstract public class CollectionOptionalRemoveAllNotImplementedAxioms<E>
		implements jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

	public static <E> void removeAllShouldThrowUnsupportedException(
			Collection<E> c1, Collection<E> c2) {
		try {
			c1.removeAll(c2);
			fail("removeAll() should throw UnsupportedOperationsException when it is not implemented.");
		} catch (UnsupportedOperationException e) {
			// success();
		} catch (ClassCastException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		}
	}
}