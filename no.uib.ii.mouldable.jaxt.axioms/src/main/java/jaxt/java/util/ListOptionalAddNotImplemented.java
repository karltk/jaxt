package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.List;

/**
 * This optional axiom should be enabled for sub-types of List where add() is
 * <b>not</b> implemented should (throw UnsupportedOperationException).
 * 
 * @see java.util.List
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */

public class ListOptionalAddNotImplemented implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	public static <E> void addOptionalNotImplemented(List<E> l, E t) {
		try {
			l.add(t);
			fail(l
					+ ".add(t) should throw a Unsupported operation exception when it is not Implemented");
		} catch (UnsupportedOperationException usop) {
			// success
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException ilae) {
			// OK
		}
	}
}
