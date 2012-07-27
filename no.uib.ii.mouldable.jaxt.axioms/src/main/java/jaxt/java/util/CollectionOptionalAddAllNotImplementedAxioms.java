package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.Collection;

/**
 * This optional axiom should be enabled for sub-types of Collection where
 * addAll is <b>not</b> implemented (should throw
 * UnsupportedOperationException).
 */
abstract public class CollectionOptionalAddAllNotImplementedAxioms<E>
		implements jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

	public static <E> void addAllShouldThrowUnsupportedException(
			Collection<E> c1, Collection<E> c2) {
		try {
			c1.addAll(c2);
			fail("addall() should throw UnsupportedOperationsException when it is not implemented.");
		} catch (UnsupportedOperationException e) {
			// success();
		} catch (ClassCastException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		} catch (IllegalStateException e) {
			// OK
		}
	}
}