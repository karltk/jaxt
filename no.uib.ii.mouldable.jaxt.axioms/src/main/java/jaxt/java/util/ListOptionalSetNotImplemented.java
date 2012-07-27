package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.List;

/**
 * This optional axiom should be enabled for sub-types of List where set() is
 * not implemented should (throw UnsupportedOperationException).
 * 
 * @see java.util.List
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */

public class ListOptionalSetNotImplemented implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	public static <E> void setOptionalShouldThrowUnSupportedException(
			List<E> l, int index, E e) {
		try {

			l.set(index, e);
			fail(l
					+ ".set(int index, E element) should throw a unsupported operation exception");
		} catch (UnsupportedOperationException usop) {
			// Success
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException iae) {
			// OK
		} catch (IndexOutOfBoundsException iob) {

		}
	}
}
