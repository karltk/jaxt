package jaxt.java.util;

/**
 * This optional axiom should be enabled for sub-types of List where 
 * add(int index,E element) is implemented (does not throw UnsupportedOperationException).
 * 
 * @see java.util.List
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009 
 */

import static junit.framework.Assert.assertSame;

import java.util.List;

public class ListOptionalAddByIndexImplemented<E> implements
		jaxt.framework.RequiredAxioms<java.util.List<E>> {

	public static <E> void addByIndexOptionalImplemented(List<E> l, E t,
			int index) {
		try {

			l.add(index, t);
			assertSame(l.get(index), t);

		} catch (ClassCastException cce) {

		} catch (NullPointerException npe) {

		} catch (IllegalArgumentException iae) {

		} catch (IndexOutOfBoundsException iobe) {

		}

	}

	public static <E> void addByIndexOptionalImplementedProperty2(List<E> l,
			E t, int index) {
		try {

			E oldValueInList = l.get(index);

			assertSame(l.set(index, t), oldValueInList);
			assertSame(l.get(index + 1), oldValueInList);

		} catch (ClassCastException cce) {

		} catch (NullPointerException npe) {

		} catch (IllegalArgumentException iae) {

		} catch (IndexOutOfBoundsException iobe) {

		}

	}

}