package jaxt.java.util;

import java.util.List;

/**
 * This optional axiom should be enabled for sub-types of List where remove() is
 * implemented (does not throw UnsupportedOperationException).
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */
public class ListOptionalRemoveImplemented implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	public static <E> void removeOptionalImplemented(List<E> l, E t, int index) {
		try {
			l.remove(index);

		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException igl) {
			// OK
		}
	}
}
