package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.Collection;

/**
 * This optional axiom should be enabled for sub-types of Collection where add
 * is <b>not</b> implemented (should throw UnsupportedOperationException).
 */
abstract public class CollectionOptionalAddNotImplementedAxioms<E> implements
		jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

	public static <E> void addShouldThrowUnsupportedException(Collection<E> c,
			E t) {
		try {
			c.add(t);
			fail("add() should throw UnsupportedOperationsException when it is not implemented.");
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