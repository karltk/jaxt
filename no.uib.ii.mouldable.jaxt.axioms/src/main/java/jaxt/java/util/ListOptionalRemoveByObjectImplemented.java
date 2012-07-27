package jaxt.java.util;

import java.util.List;

/**
 * This optional axiom should be enabled for sub-types of List where
 * remove(Object o) is implemented (does not throw
 * UnsupportedOperationException).
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */
public class ListOptionalRemoveByObjectImplemented implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	public static <E> void removeByObjectOptionalImplemented(List<E> l, E t,
			Object o) {
		try {
			l.remove(o);

		} catch (ClassCastException cce) {

		} catch (NullPointerException npe) {

		} catch (IllegalArgumentException igl) {

		}
	}
}
