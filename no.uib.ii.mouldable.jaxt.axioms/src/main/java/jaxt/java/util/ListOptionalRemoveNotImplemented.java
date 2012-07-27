package jaxt.java.util;

import static junit.framework.Assert.fail;

import java.util.List;

/**
 * This optional axiom should be enabled for sub-types of List where remove() is
 * not implemented should (throw UnsupportedOperationException).
 * 
 * @see java.util.List
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */
public class ListOptionalRemoveNotImplemented implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	public static <E> void removeOptionalNotImplemented(List<E> l, E t,
			int index) {

		try {
			l.remove(null);
			fail(l
					+ ".remove(index) should throw a unsupported operation exception");
		} catch (UnsupportedOperationException usop) {
			// Success
		}

	}

}
