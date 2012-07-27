package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.Collection;

/**
 * This optional axiom should be enabled for sub-types of Collection where add
 * is implemented (does not throw UnsupportedOperationException).
 */
abstract public class CollectionOptionalAddImplementedAxioms<E> implements
		jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

	public static <E> void addShouldNotThrowUnsupportedException(
			Collection<E> c, E t) {
		try {
			c.add(t);
		} catch (UnsupportedOperationException e) {
			fail("add() is required for this class, but threw UnsupportedOperationsException."
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
