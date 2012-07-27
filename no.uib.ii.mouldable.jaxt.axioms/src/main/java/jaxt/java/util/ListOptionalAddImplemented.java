package jaxt.java.util;

import java.util.List;

/**
 * This optional axiom should be enabled for sub-types of List where add() is
 * implemented (does not throw UnsupportedOperationException).
 * 
 * @see java.util.List
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */
public class ListOptionalAddImplemented implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	public static <E> void addOptionalShouldNotThrowUnsupportedException(
			List<E> l, E t) {
		try {
			l.add(t);

		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException igl) {
			// OK

		}
	}
}
