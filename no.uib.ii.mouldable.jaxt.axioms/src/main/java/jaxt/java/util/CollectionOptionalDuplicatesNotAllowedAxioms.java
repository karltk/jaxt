package jaxt.java.util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * These axioms are required for implementations of Collection that does
 * <b>not</b> allow duplicate values in the collection.
 */
abstract public class CollectionOptionalDuplicatesNotAllowedAxioms<E>
		implements jaxt.framework.OptionalAxioms<java.util.Collection<E>> {

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
			if (c.contains(t))
				assertFalse(
						"add should not allow duplicates but returned true when a duplicate was added",
						c.add(t));
			else
				assertTrue("add() should not allow duplicates", c.add(t));

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
			ArrayList<E> a, E t) {
		try {
			int size = c.size();
			int counter = 0;
			ArrayList<E> tmp = new ArrayList<E>();
			for (E element : a) {
				// If the element is not in the collection and not a duplicate
				// in
				// the ArrayList we count it as expected to be added.
				if (!(c.contains(element) || tmp.contains(element))) {
					tmp.add(element);
					counter++;
				}
			}

			if (counter == 0)
				assertFalse(
						"addAll returned true even though it already contained "
								+ "all the elements and duplicates is not allowed. Thus "
								+ "it should not have changed.", c.addAll(a));
			else
				assertTrue(
						"addAll returned false even though it should have changed by adding "
								+ counter + "new elements.", c.addAll(a));

			assertEquals("The collections size was not correct after addAll. "
					+ a.size() + " elements was attempted added, "
					+ (a.size() - counter) + " was duplicates" + counter
					+ " elements should have been added.", size + counter, c
					.size());

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
