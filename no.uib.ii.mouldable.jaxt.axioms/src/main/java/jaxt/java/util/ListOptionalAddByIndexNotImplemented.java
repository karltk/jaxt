package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.List;

/**
 * This optional axiom should be enabled for sub-types of List where add(int
 * index, E element) is <b>not</b> implemented should (throws
 * UnsupportedOperationException).
 * 
 * @see java.util.List
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */

public class ListOptionalAddByIndexNotImplemented implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	// addByIndex
	public static <E> void addByIndexOptionalNotImplemented(List<E> l, E t,
			int index) {

		try {
			l.add(index, t);
			fail(l
					+ ".add(index, t) should throw a unsupported operation exception");
		} catch (UnsupportedOperationException usop) {
			// Success
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException ilae) {
			// OK
		} catch (IndexOutOfBoundsException iob) {

		}

	}

}
