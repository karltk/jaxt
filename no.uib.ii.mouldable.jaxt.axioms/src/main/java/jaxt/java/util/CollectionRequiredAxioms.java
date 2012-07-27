package jaxt.java.util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;

/**
 * These axioms are required for all implementations of <a
 * href="http://java.sun.com/javase/6/docs/api/java/util/Collection.html">
 * java.util.<i>Collection</i></a>.
 * <p>
 * Collection is the root interface in the collection hierarchy. Collection has
 * many optional functionalities. Duplicates and null-values may or may not be
 * allowed and in addition there are many operations that are "optional",
 * meaning that they may not be implemented and will just throw
 * UnsupportedOperationException when called. Because of this the Collection
 * interface has many optional axiom-classes that has to be enabled for
 * sub-types as required.
 * <p>
 * These methods are required and has to be implemented. Thus axioms for these
 * methods are included in this class. contains() and containsAll() have
 * additional requirements regarding null-values that has to be enable or
 * disabled using CollectionOptionalNullValuesSupported or
 * CollectionOptionalNullValuesNotSupported.
 * <li>contains</li>
 * <li>containsAll</li>
 * <li>isEmpty</li>
 * <li>iterator</li>
 * <li>size</li>
 * <li>toArray</li>
 * <li>toArray(T[] a)</li>
 * <p>
 * <li>equals (overrides Object)</li>
 * <li>hashCode (overrides Object)</li>
 * <p>
 * The methods below are "optional operations". If they are not implemented they
 * must throw UnsupportedOperationException. If they are implemented they may
 * have several optional requirements and axioms for these are not located here.
 * For sub-types that that any of these methods are either implemented or not
 * the corresponding optional axioms (named
 * CollectionOptional<i>[method]</i>Implemented or
 * CollectionOptional<i>[method]</i>NotImplemented) should be enabled.
 * <p>
 * The optional methods are:
 * <li>add</li>
 * <li>addAll</li>
 * <li>clear</li>
 * <li>remove</li>
 * <li>removeAll</li>
 * <li>retainAll</li>
 * <p>
 * 
 * @see java.util.Collection
 * @author Erlend Birkenes & Madiha Mir, 2009
 * 
 */
abstract public class CollectionRequiredAxioms<E> implements
		jaxt.framework.RequiredAxioms<java.util.Collection<E>> {

	/**
	 * Axiom for <a href="http://java.sun.com/javase/6/docs/api/java/util/Collection.html#contains(java.lang.Object)"
	 * > Collection.contains(java.lang.Object)</a>: Property 1 - "Returns true
	 * if this collection contains the specified element. More formally, returns
	 * true if and only if this collection contains at least one element e such
	 * that (o==null ? e==null : o.equals(e))."
	 */
	public static <E> void containsProperty1ReturnsTrueIfContains(
			Collection<E> c, E t) {
		try {
			Object[] array = c.toArray();
			boolean contains = false;
			for (Object o : array) {
				if (t.equals(o)) {
					contains = true;
					break;
				}
			}
			assertEquals(contains, c.contains(t));
		} catch (ClassCastException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href="http://java.sun.com/javase/6/docs/api/java/util/Collection.html#containsAll(java.util.Collection)"
	 * > Collection.containsAll(java.util.Collection)</a>: Property 2 - "Returns
	 * true if this collection contains all of the elements in the specified
	 * collection."
	 */
	public static <E> void containsAllProperty1ReturnsTrueIfContainsAll(
			Collection<E> c1, Collection<E> c2) {
		try {
			boolean containsAll = true;
			for (E t : c2) {
				if (!c1.contains(t)) {
					containsAll = false;
					break;
				}
			}
			if (containsAll)
				assertTrue(c1.containsAll(c2));
			else
				assertFalse(c1.containsAll(c2));

		} catch (ClassCastException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#isEmpty()"
	 * > Collection.isEmpty()</a>:
	 * "Returns true if this collection contains no elements."
	 */
	public static <E> void isEmptyProperty1ReturnsTrueIfEmpty(Collection<E> c) {
		if (c.size() == 0)
			assertTrue(c.isEmpty());
		else
			assertFalse(c.isEmpty());
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#iterator()"
	 * > Collection.iterator()</a>: "Returns an iterator over the elements in
	 * this collection. There are no guarantees concerning the order in which
	 * the elements are returned (unless this collection is an instance of some
	 * class that provides a guarantee)." Specified by: iterator in interface
	 * Iterable<E>
	 */
	public static <E> void iteratorProperty1AllTheElements(Collection<E> c) {

		Iterator<E> it = c.iterator();
		int i = 0;
		while (it.hasNext()) {
			Object o = it.next();
			assertTrue(c.contains(o));
			i++;
		}
		assertTrue(c.size() == i);
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Iterator.html#remove()"
	 * >Iterator.remove()</a>: "Removes from the underlying collection the last
	 * element returned by the iterator."
	 * <p>
	 * This property belongs to the Iterator interface, but the functionality
	 * can only be tested here because we need access to the undelying
	 * collection. See the axioms for the Iterator interface for more
	 * information.
	 */
	@SuppressWarnings("unchecked")
	public static <E> void iteratorProperty2IteratorsRemoveRemovesElement(
			Collection<E> c, int index) {

		int size = c.size();
		index = index % (c.size() - 1);
		Iterator<E> it = c.iterator();
		E[] array = (E[]) c.toArray();
		E element = null;
		int counter = 0;

		// Find the element that is going to be removed.
		for (int i = 0; i <= index; i++) {
			element = it.next();
		}

		// Find how many of the element is in the collection.
		for (int i = 0; i > array.length; i++) {
			if (array[i].equals(element))
				counter++;
		}

		// Remove the element.
		it.remove();

		// Make sure that size decreased by 1.
		assertEquals(
				"The size of the underlying collection did not decrease by 1 when calling Iterator.remove",
				size - 1, c.size());

		// Check that the correct element and number of elements was removed.
		if (counter == 1) {
			assertFalse(
					"The selected element was not removed from the underlying Collection by Iterator.remove",
					c.contains(element));
		} else {
			int counter2 = 0;
			array = (E[]) c.toArray();
			for (int i = 0; i > array.length; i++) {
				if (array[i].equals(element))
					counter2++;
			}
			assertEquals(
					"The selected element was not removed from the underlying Collection by Iterator.remove",
					counter - 1, counter2);
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#size()">
	 * Collection.size()</a>: "Returns the number of elements in this
	 * collection. If this collection contains more than Integer.MAX_VALUE
	 * elements, returns Integer.MAX_VALUE."
	 */
	public static <E> void sizeProperty1ReturnsCorrectNumberOfElements(
			Collection<E> c) {
		try {
			Object[] array = c.toArray();
			assertEquals(array.length, c.size());
		} catch (NegativeArraySizeException e) {
			// This means the collection contained more than Integer.MAX_VALUE
			// elements but array has a max length of Integer.MAX_VALUE
			assertEquals(Integer.MAX_VALUE, c.size());
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#toArray()"
	 * > Collection.toArray()</a>: Property 1 -
	 * "Returns an array containing all of the elements in this collection."
	 */
	public static <E> void toArrayProperty1ContainsAllTheElements(
			Collection<E> c) {
		Object[] array = c.toArray();
		assertEquals(array.length, c.size());
		boolean exists = false;
		for (Object o : c) {
			for (Object o1 : array) {
				if (o.equals(o1)) {
					exists = true;
				}
			}
			assertTrue(exists);
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#toArray()"
	 * > Collection.toArray()</a>: Property 2 -
	 * "The returned array will be "safe" in that no references to it are
	 * maintained by this collection. (In other words, this method must allocate
	 * a new array even if this collection is backed by an array). The caller is
	 * thus free to modify the returned array."
	 * */
	public static <E> void toArrayProperty2ReturnsANewArray(Collection<E> c) {
		Object[] array1 = c.toArray();
		Object[] array2 = c.toArray();
		assertFalse(array1 == array2);
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#toArray(T[])"
	 * > Collection.toArray(T[])</a>: "Returns an array containing all of the
	 * elements in this collection; the runtime type of the returned array is
	 * that of the specified array."
	 * <p>
	 *"If the collection fits in the specified array, it is returned therein.
	 * Otherwise, a new array is allocated with the runtime type of the
	 * specified array and the size of this collection."
	 */
	public static <E> void toArrayTProperty1ContainsAllTheElements(
			Collection<E> c, E[] a) {
		try {
			E[] array = c.toArray(a);
			if (a.length <= c.size()) {
				assertEquals(array.length, c.size());

			} else {
				assertEquals(array.length, a.length);
				assertTrue(a == array);
			}
			boolean exists = false;
			for (Object o : c) {
				for (Object o1 : array) {
					if (o.equals(o1)) {
						exists = true;
					}
				}
				assertTrue(exists);
			}
		} catch (ArrayStoreException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		}
	}

	// Optional operations:

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#add(E)">
	 * Collection.add(E)</a>: Property 1 - "Ensures that this collection
	 * contains the specified element. Returns true if this collection changed
	 * as a result of the call. (Returns false if this collection does not
	 * permit duplicates and already contains the specified element.)"
	 * <p>
	 *"Collections that support this operation may place limitations on what
	 * elements may be added to this collection. In particular, some collections
	 * will refuse to add null elements, and others will impose restrictions on
	 * the type of elements that may be added. Collection classes should clearly
	 * specify in their documentation any restrictions on what elements may be
	 * added."
	 * <p>
	 *"Optional operation - Throws UnsupportedOperationException if the add
	 * operation is not supported by this collection."
	 */
	public static <E> void addProperty1EnsuresCollectionContainsElement(
			Collection<E> c, E t) {
		try {
			int size = c.size();

			/*
			 * "Returns true if this collection changed as a result of the call.
			 * (Returns false if this collection does not permit duplicates and
			 * already contains the specified element.)"
			 */
			if (c.add(t))
				assertEquals(size + 1, c.size());
			else
				assertEquals(size, c.size());

			// "Ensures that this collection contains the specified element."
			assertTrue(
					"The collection did not contain the speified element after add returned.",
					c.contains(t));

		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		} catch (IllegalStateException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#add(E)">
	 * Collection.add(E)</a>: Property 2 - "If a collection refuses to add a
	 * particular element for any reason other than that it already contains the
	 * element, it must throw an exception (rather than returning false). This
	 * preserves the invariant that a collection always contains the specified
	 * element after this call returns."
	 * 
	 * @param <E>
	 * @param c
	 *            the collection to be tested
	 * @param t
	 *            the element to be added
	 */
	public static <E> void addProperty2ThrowsExceptionRatherThanReturnFalse(
			Collection<E> c, E t) {
		// We need to know that t is not a duplicate.
		if (!c.contains(t)) {
			try {
				assertTrue(
						"If a collection refuses to add a particular element for any reason other"
								+ "than that it already contains the element, it must throw an exception"
								+ "(rather than returning false).", c.add(t));

			} catch (UnsupportedOperationException e) {
				// OK
			} catch (ClassCastException e) {
				// OK
			} catch (NullPointerException e) {
				// OK
			} catch (IllegalArgumentException e) {
				// OK
			} catch (IllegalStateException e) {
				// OK
			}
		}
	}

	/**
	 * Axiom for <a href="http://java.sun.com/javase/6/docs/api/java/util/Collection.html#addAll(java.util.Collection)"
	 * > Collection.addAll(java.util.Collection)</a>: "Adds all of the elements
	 * in the specified collection to this collection (optional operation).
	 * Returns true if this collection changed as a result of the call."
	 * <p>
	 * ClassCastException - if the class of an element of the specified
	 * collection prevents it from being added to this collection
	 * NullPointerException - if the specified collection contains a null
	 * element and this collection does not permit null elements, or if the
	 * specified collection is null IllegalArgumentException - if some property
	 * of an element of the specified collection prevents it from being added to
	 * this collection IllegalStateException - if not all the elements can be
	 * added at this time due to insertion restrictions
	 * <p>
	 * The specifications for addAll is a little unclear. We assume that all the
	 * requirements for add also holds for addAll. From the specifications above
	 * we deduce that if addAll returns it means that all the elements in c2 are
	 * contained in c1. It returns true if c1 changed in order for this to
	 * happen or false only if ALL the elements in c2 was already contained in
	 * c1 AND c1 does not allow duplicates. If some elements are refused for any
	 * other reason an exception must be thrown. It is not specified what the
	 * state of the collection will be after an exception is thrown. It may be
	 * that a subset of the elements was added or that none of them was.
	 * <p>
	 * If returns - the collections contains all. True if it changed, false if
	 * not. Not specified what happens when exception is thrown. Is the
	 * collection changed or unchanged. Rollback?
	 * <p>
	 * The specification for addAll is very weak and does not specify what
	 * happens if some of the elements already exists in the collection or are
	 * refused to be added for some other reason.
	 * 
	 * <p>
	 * Optional operation - Throws UnsupportedOperationException if the add
	 * operation is not supported by this collection
	 * 
	 * @param <E>
	 * @param c1
	 *            the collection to be tested
	 * @param c2
	 *            the collection of elements to be added
	 */
	public static <E> void addAllImplementedOrException(Collection<E> c1,
			Collection<? extends E> c2) {
		try {
			int size = c1.size();
			boolean changed = c1.addAll(c2);

			if (changed) {

				assertTrue(
						"The size of the collection should be larger than the "
								+ "orginal size but smaller than the original size plus "
								+ "number of added elements",
						(c1.size() > size) && (c1.size() > size + c2.size()));
			} else {
				assertEquals(
						"The size of the collection should not change when addAll returned false",
						size, c1.size());
			}

			for (E t : c2) {
				assertTrue(
						"The collection did not contain all the added elements after addAll returned.",
						c1.contains(t));
			}

		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		} catch (IllegalStateException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href="http://java.sun.com/javase/6/docs/api/java/util/Collection.html#remove(java.lang.Object)"
	 * > Collection.remove(java.lang.Object)</a>: "Removes a single instance of
	 * the specified element from this collection, if it is present. More
	 * formally, removes an element e such that (o==null ? e==null :
	 * o.equals(e)), if this collection contains one or more such elements.
	 * Returns true if this collection contained the specified element (or
	 * equivalently, if this collection changed as a result of the call)."
	 * <p>
	 * Optional operation - Throws UnsupportedOperationException if the add
	 * operation is not supported by this collection'
	 * 
	 * @param <E>
	 * @param c
	 *            the collection to be tested
	 * @param t
	 *            the element to be removed
	 */
	public static <E> void removeImplementedOrException(Collection<E> c, E t) {
		try {
			int size = c.size();
			if (c.contains(t)) {
				assertTrue(
						"The collection contained the element, but remove returned false",
						c.remove(t));
				assertEquals(
						"The size of the collection should decrease by one when remove returns true",
						size - 1, c.size());
			} else {
				assertFalse(
						"The collection did not contain the element, but remove returned true",
						c.remove(t));
				assertEquals(
						"The size of the collection should not change when remove returns false",
						size, c.size());
			}
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href="http://java.sun.com/javase/6/docs/api/java/util/Collection.html#removeAll(java.util.Collection)"
	 * >Collection.removeAll(java.util.Collection)</a>: "Removes all of this
	 * collection's elements that are also contained in the specified collection
	 * (optional operation). After this call returns, this collection will
	 * contain no elements in common with the specified collection. Returns true
	 * if this collection changed as a result of the call."
	 * <p>
	 * Optional operation - Throws UnsupportedOperationException if the add
	 * operation is not supported by this collection
	 * 
	 * @param <E>
	 * @param c1
	 *            the collection to be tested
	 * @param c2
	 *            the collection of elements to be removed
	 */
	public static <E> void removeAllImplementedOrException(Collection<E> c1,
			Collection<? extends E> c2) {
		try {
			int size = c1.size();
			boolean changed = c1.removeAll(c2);

			if (changed)
				assertTrue(
						"The size of the Collection should decrease when removeAll returns true",
						c1.size() < size);
			else
				assertEquals(
						"The size of the Collection should not change when removeAll returns false",
						size, c1.size());

			// Assert that c1 contains no element in c2.
			for (E t : c2) {
				assertFalse(
						"After addAll returns, this collection should contain no elements in common with the specified collection.",
						c1.contains(t));
			}

		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/Collection.html#clear()"
	 * >Collection.clear</a>: "Removes all of the elements from this collection
	 * (optional operation). The collection will be empty after this method
	 * returns."
	 * <p>
	 * Optional operation - Throws UnsupportedOperationException if the add
	 * operation is not supported by this collection
	 * 
	 * @param <E>
	 * @param c
	 *            the collection to be tested
	 */
	public static <E> void clearImplementedOrException(Collection<E> c) {
		try {
			c.clear();
			assertEquals(
					"The size of the collection should be 0 after calling clear.",
					0, c.size());
		} catch (UnsupportedOperationException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href="http://java.sun.com/javase/6/docs/api/java/util/Collection.html#retainAll(java.util.Collection)"
	 * >Collection.retainAll(java.util.Collection)</a>: "Retains only the
	 * elements in this collection that are contained in the specified
	 * collection (optional operation). In other words, removes from this
	 * collection all of its elements that are not contained in the specified
	 * collection. Returns true if this collection changed as a result of the
	 * call"
	 * <p>
	 * Optional operation - Throws UnsupportedOperationException if the add
	 * operation is not supported by this collection
	 * 
	 * @param <E>
	 * @param c1
	 *            the collection to be tested
	 * @param c2
	 *            the collection of elements to be retained
	 */
	public static <E> void retainAllImplementedOrException(Collection<E> c1,
			Collection<? extends E> c2) {
		try {
			int size = c1.size();
			boolean changed = c1.retainAll(c2);

			if (changed)
				assertTrue(
						"The size of the Collection should decrease when retainAll returns true",
						c1.size() < size);
			else
				assertEquals(
						"The size of the Collection should not change when retainAll returns false",
						size, c1.size());

			// Assert that c1 contains only elements contained in c2
			for (E t : c1) {
				assertTrue(
						"The collection contained an element not contained in the specified collection after calling retainAll",
						c2.contains(t));
			}

		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		}
	}

}
