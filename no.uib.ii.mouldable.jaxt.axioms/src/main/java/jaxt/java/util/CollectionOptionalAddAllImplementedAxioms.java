package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.Collection;

/**
 * This optional axiom should be enabled for sub-types of Collection where
 * addAll is implemented (does not throw UnsupportedOperationException).
 */
abstract public class CollectionOptionalAddAllImplementedAxioms<E> implements
		jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

	public static <E> void addAllShouldNotThrowUnsupportedException(
			Collection<E> c1, Collection<E> c2) {
		try {
			c1.addAll(c2);
		} catch (UnsupportedOperationException e) {
			fail("addall() is required for this class, but threw UnsupportedOperationsException."
					+ e);

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