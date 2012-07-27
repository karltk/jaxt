package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.Collection;
import java.util.List;

/**
 * This optional axiom should be enabled for sub-types of List where
 * addAll(Collection<? extends E> c) is <b>not</b> implemented should (throws
 * UnsupportedOperationException).
 * 
 * @see java.util.List
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */
public class ListOptionalAddAllNotImplemented implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	public static <E> void OptionalAddAllNotImplemented(Collection<E> c,
			List<E> l) {

		try {
			l.addAll(c);
			fail(l
					+ ".addAll(Collection<? extends E> c) should throw a unsupported operation exception");
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
