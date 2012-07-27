package jaxt.java.util;

import java.util.Collection;
import java.util.List;

/**
 * This optional axiom should be enabled for sub-types of List where addAll(int
 * index, Collection<? extends E> c) is implemented (does not throw
 * UnsupportedOperationException).
 * 
 * @see java.util.List
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */

public class ListOptionalAddAllByIndexImplemented implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	public static <E> void OptionalAddAllImplemented(Collection<E> c,
			List<E> l, int index) {

		try {

			l.addAll(index, c);

		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException iae) {
			// OK
		} catch (IndexOutOfBoundsException ibe) {

		}

	}

}
