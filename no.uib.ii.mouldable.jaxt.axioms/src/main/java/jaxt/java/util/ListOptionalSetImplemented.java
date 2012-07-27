package jaxt.java.util;

import java.util.List;

/**
 * /** This optional axiom should be enabled for sub-types of List where set()
 * is implemented (does not throw UnsupportedOperationException).
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */

public class ListOptionalSetImplemented implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	public static <E> void setOptionalImplemented(List<E> l, int index, E e) {
		try {

			l.set(index, e);
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException igl) {
			// OK
		} catch (IndexOutOfBoundsException iobe) {
			// OK
		}
	}

}// end

