package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.Collection;

/**
 * This optional axiom should be enabled for sub-types of Collection where
 * retainAll is <b>not</b> implemented (should throw
 * UnsupportedOperationException).
 */
abstract public class CollectionOptionalRetainAllNotImplementedAxioms<E>
		implements jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

	public static <E> void retainAllShouldThrowUnsupportedException(
			Collection<E> c1, Collection<E> c2) {
		try {
			c1.retainAll(c2);
			fail("retainAll() should throw UnsupportedOperationsException when it is not implemented.");
		} catch (UnsupportedOperationException e) {
			// success();

		} catch (ClassCastException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		}
	}
}