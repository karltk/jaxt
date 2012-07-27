package jaxt.java.util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * These axioms are required for implementations of Collection that allows
 * duplicate values in the collection.
 */
abstract public class CollectionOptionalDuplicatesAllowedAxioms<E> implements
		jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#add(E)"
	 * > Collection.add(E)</a>: "Returns true if this collection changed as a
	 * result of the call. (Returns false if this collection does not permit
	 * duplicates and already contains the specified element.)"
	 * 
	 * @param <E>
	 * @param c
	 *            the Collection to be tested
	 * @param t
	 *            an element to be added to the collection
	 */
	public static <E> void addDontAcceptDuplicates(Collection<E> c, E t) {
		try {

			assertTrue(
					"add should never return false when duplicates is allowed",
					c.add(t));

		} catch (NullPointerException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		} catch (IllegalStateException e) {
			// OK
		} catch (UnsupportedOperationException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href="http://java.sun.com/javase/6/docs/api/java/util/Collection.html#addAll(java.util.Collection)"
	 * > Collection.addAll(java.util.Collection)</a>: "Adds all of the elements
	 * in the specified collection to this collection (optional operation)."
	 * <p>
	 *"See Also: <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#add(E)"
	 * > add(Object)</a>"
	 * 
	 * @param <E>
	 * @param c
	 *            The Collection to be tested.
	 * @param a
	 *            An ArrayList containing the elements to be added to the
	 *            collection.
	 */
	public static <E> void addAllDontAcceptDuplicates(Collection<E> c,
			ArrayList<E> a) {
		try {
			int size = c.size();

			assertTrue(
					"addAll should never return false when duplicates is allowed",
					c.addAll(a));

			assertEquals("The collections size was not correct after addAll. "
					+ a.size() + " elements should have been added.", size
					+ a.size(), c.size());

		} catch (NullPointerException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		} catch (IllegalStateException e) {
			// OK
		} catch (UnsupportedOperationException e) {
			// OK
		}
	}

}
