package jaxt.java.util;

/**
 * This optional axiom should be enabled for sub-types of List where 
 * addAll(Collection<? extends E> c) is implemented (does not throw UnsupportedOperationException).
 * 
 * property 1: Appends all of the elements in the specified collection to the end of this list,
 * in the order that they are returned by the specified collection's iterator
 * if it is supported it will not through exception.
 *  
 * @see java.util.List
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009 
 */
import java.util.Collection;
import java.util.List;

public class ListOptionalAddAllImplemented implements
		jaxt.framework.RequiredAxioms<java.util.List> {

	public static <E> void OptionalAddAllImplemented(Collection<E> c, List<E> l) {

		try {

			l.addAll(c);

		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException iae) {
			// OK
		}

	}

}
