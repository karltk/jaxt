package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.Collection;

/**
 * This optional axiom should be enabled for sub-types of Collection where
 * remove is <b>not</b> implemented (should throw
 * UnsupportedOperationException).
 */
abstract public class CollectionOptionalRemoveNotImplementedAxioms<E>
		implements jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

	public static <E> void removeShouldThrowUnsupportedException(
			Collection<E> c, E t) {
		try {
			c.remove(t);
			fail("remove() should throw UnsupportedOperationsException when it is not implemented.");
		} catch (UnsupportedOperationException e) {
			// success();

		} catch (ClassCastException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		}
	}
}