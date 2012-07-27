package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.List;

/**
 * This optional axiom should be enabled for sub-types of List where
 * remove(Object o) is not implemented (should throw
 * UnsupportedOperationException).
 * 
 * @see java.util.List
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */
public class ListOptionalRemoveByObjectNotImplemented implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	// remove
	public static <E> void removeByObjectOptionalNotImplemented(List<E> l, E t,
			Object o) {

		try {
			l.remove(o);
			fail(l
					+ ".remove(o) should throw a unsupported operation exception");
		} catch (UnsupportedOperationException usop) {
			// Success
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		}

	}

}
