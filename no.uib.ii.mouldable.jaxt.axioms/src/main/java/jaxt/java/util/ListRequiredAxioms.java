package jaxt.java.util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * These axioms are required for all implementations of <a
 * href="http://java.sun.com/javase/6/docs/api/java/util/List.html">
 * java.util.<i>List</i></a>.
 * <p>
 * <b>Optional methods.</b> These methods are optional in the sense that they
 * are part of the signature but may not be implemented. List has many optional
 * functionalities.Null-values may or may not be allowed and in addition there
 * are many operations that are "optional" If they are not implemented they must
 * throw UnsupportedOperationException. If they are implemented they may have
 * several optional requirements and axioms for these are not located here.All
 * of these axioms are in separated files named as nameOfMethod followed by
 * OptionalImplemented and OptionalnotImplemented also axioms for where
 * null-values accepted and where null-values not accepted
 * <p>
 * The optional methods are:
 * <li>add()</li>
 * <li>addAll()</li>
 * <li>clear()</li>
 * <li>remove()</li>
 * <li>removeAll()</li>
 * <li>retainAll()</li>
 * <li>Set()</li>
 *<p>
 * <b>Required Methods </b> These methods has to be implemented .The required
 * methods are
 *<p>
 *<li>contains()</li>
 *<li>containsAll()</li>
 *<li>get()</li>
 *<li>hashCode()</li>
 *<li>iterator()</li>
 *<li>indexOf()</li>
 *<li>lastIndexOf()</li>
 *<li>isEmpty()</li>
 *<li>iterator()</li>
 *<li>lastIndexOf()</li>
 *<li>listIterator</li>
 *<li>size()</li>
 *<li>toArray()</li>
 *<li>toArray(T[] a)</li>
 *<p>
 * <b>The subList method:</b><br>
 *"Returns a view of the portion of this list between the specified fromIndex,
 * inclusive, and toIndex, exclusive. The returned list is backed by this list,
 * so non-structural changes in the returned list are reflected in this list,
 * and vice-versa. The returned list supports all of the optional list
 * operations supported by this list. This method eliminates the need for
 * explicit range operations (of the sort that commonly exist for arrays). Any
 * operation that expects a list can be used as a range operation by passing a
 * subList view instead of a whole list. For example, the following idiom
 * removes a range of elements from a list:
 * <p>
 * list.subList(from, to).clear();
 * <p>
 * Similar idioms may be constructed for indexOf and lastIndexOf, and all of the
 * algorithms in the Collections class can be applied to a subList."
 * <p>
 * This means that the subList method has to be tested with all the destructive
 * methods of List, to see that calling these methods on a sublist really
 * changes the original list in the expected way.
 * 
 * @see java.util.List
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */
public class ListRequiredAxioms<T> implements
		jaxt.framework.RequiredAxioms<java.util.List<T>> {

	/**
	 * Axiom for <a href
	 * ="http://java.sun.com/javase/6/docs/api/java/util/List.html#add(E)" >
	 * List.add(E e)</a>: Appends the specified element to the end of this list
	 * (optional operation).
	 * <p>
	 * This is the only new requirement for add(E) in List. The rest of the
	 * functionality of add(E) is covered by the axioms for Collection.
	 * 
	 * @see CollectionRequiredAxioms
	 * 
	 * @param <T>
	 * @param l
	 *            the list that is being tested
	 * @param t
	 *            an element that is added to the list
	 */
	public static <T> void addAppendsElementAtTheEndOfList(List<T> l, T t) {

		try {
			int index = l.size();

			assertTrue("", l.add(t));
			assertSame(
					"After add(E), the element at the end of the list was not the added element",
					t, l.get(index));

		} catch (UnsupportedOperationException use) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException iae) {
			// OK
		}
	}

	/**
	 * Axiom for <a href
	 * ="http://java.sun.com/javase/6/docs/api/java/util/List.html#add(int,E)" >
	 * List.add(int , E)</a>: Property 1 -
	 * "Inserts the specified element at the specified position in this list."
	 * <p>
	 * Inserts the specified element at the specified position in this list
	 * (optional operation). Shifts the element currently at that position (if
	 * any) and any subsequent elements to the right (adds one to their
	 * indices).
	 * 
	 * @see CollectionRequiredAxioms
	 * 
	 * @param <T>
	 * @param l
	 *            the list that is being tested
	 * @param t
	 *            an element that is added to the list
	 */
	public static <T> void addByIndexAddsElementAtSpecifiedIndex(List<T> l,
			T t, int index) {
		try {
			index = (l.size() > 0) ? index % l.size() : 0;

			l.add(index, t);
			assertSame(
					"After adding an element to index "
							+ index
							+ ", the element at that position was not the element added.",
					t, l.get(index));

		} catch (UnsupportedOperationException use) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException iae) {
			// OK
		} catch (IndexOutOfBoundsException iobe) {
			// OK
		}
	}

	/**
	 * Axiom for <a href
	 * ="http://java.sun.com/javase/6/docs/api/java/util/List.html#add(int,E)" >
	 * List.add(int , E)</a> Property 2 - Shifts the element currently at that
	 * position (if any) and any subsequent elements to the right (adds one to
	 * their indices).
	 * 
	 * @see CollectionRequiredAxioms
	 * 
	 * @param <E>
	 * @param l
	 *            the list that is tested
	 * @param t
	 *            an element that is added to the list
	 * @param index
	 *            the index the element is added at
	 * @param offset
	 *            a random offset of an element that is checked after the
	 *            operation
	 */

	public static <E> void addByIndexShiftsElementsToTheRight(List<E> l, E t,
			int index, int offset) {
		try {
			if (l.size() > 0) {
				// Make sure the index is in range and that offset is between
				// index and size()
				index = index % l.size();
				offset = index + (offset % (l.size() - index));

				E oldValueAtIndex = l.get(index);
				E oldValueAtOffset = l.get(offset);

				l.add(index, t);

				assertSame(
						"After add(index, element), the element returned by get(index) was not the added element.",
						t, l.get(index));
				assertSame(
						"After add(index, element), the element at index was not shifted to the right",
						oldValueAtIndex, l.get(index + 1));
				assertSame(
						"After add(index, element), the element at position offset was not shifted to the right",
						oldValueAtOffset, l.get(offset + 1));
			} else {
				index = 0;
				l.add(index, t);
				assertSame(
						"After add(index, element), the element returned by get(index) was not the added element.",
						t, l.get(index));
			}
		} catch (UnsupportedOperationException use) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException iae) {
			// OK
		} catch (IndexOutOfBoundsException iobe) {
			// OK
		}
	}

	/**
	 * Axiom for <a href ="http://java.sun.com/javase/6/docs/api/java/util/List.html#addAll(java.util.Collection)"
	 * > List.addAll(java.util.Collection)</a> Appends all of the elements in
	 * the specified collection to the end of this list, in the order that they
	 * are returned by the specified collection's iterator
	 * 
	 * @see CollectionRequiredAxioms
	 * 
	 * @param <E>
	 * @param l
	 *            the list that is tested
	 * @param c
	 *            - collection containing elements
	 **/

	public static <E> void addAllAppendsElementsAtEndOfList(Collection<E> c,
			List<E> l) {

		try {
			int index = l.size();

			l.addAll(c);
			assertEquals(l.size(), c.size() + index);

			for (E e : c) {
				assertSame(e, l.get(index));
				index++;
			}

		} catch (UnsupportedOperationException uoe) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException iae) {
			// OK
		}

	}

	/**
	 * Axiom for <a href ="http://java.sun.com/javase/6/docs/api/java/util/List.html#addAll(int,java.util.Collection)"
	 * > List.addAll(int, java.util.Collection)</a> Inserts all of the elements
	 * in the specified collection into this list at the specified position.
	 * 
	 * @see CollectionRequiredAxioms
	 **/
	public static <E> void addAllByIndexInsertsAllElementsAtIndex(List<E> l,
			E t, int index, Collection<? extends E> c) {
		try {
			index = index % l.size();
			int size = l.size();
			l.addAll(index, c);

			assertEquals(l.size(), c.size() + size);

			for (E e : c) {
				assertSame(e, l.get(index));
				index++;
			}

		} catch (UnsupportedOperationException uoe) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException iae) {
			// OK
		}

	}

	/**
	 * Axiom for <a href
	 * ="http://java.sun.com/javase/6/docs/api/java/util/List.html#get(int)" >
	 * List.get(int)</a> Returns the element at the specified position in this
	 * list.
	 * 
	 * @param <E>
	 * @param l
	 *            the list that is tested
	 * @param index
	 *            - index of the element to return
	 * 
	 * @see CollectionRequiredAxioms
	 **/

	public static <E> void getElementInList(List<E> l, int index) {

		if (index >= 0 && index < l.size()) {
			Object[] o = l.toArray();
			assertSame(l.get(index), o[index]);

		} else {
			try {
				l.get(index);
			} catch (IndexOutOfBoundsException iobe) {
				// OK
			}
		}
	}

	/**
	 * Axiom for <a href
	 * ="http://java.sun.com/javase/6/docs/api/java/util/List.html#set(int,E)" >
	 * List.set(int,E)</a> Replaces the element at the specified position in
	 * this list with the specified element.
	 * 
	 * @param <E>
	 * @param l
	 *            the list that is tested
	 * @param index
	 *            - index of the element to return
	 * @param e
	 *            - element to be stored
	 * 
	 * **/

	public static <E> void replaceElementAtSpecifiedPositionInList(List<E> l,
			int index, E e) {

		try {

			E OldElementInList = l.get(index);

			assertSame(l.set(index, e), OldElementInList);

		} catch (UnsupportedOperationException usoe) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException iae) {
			// OK
		} catch (IndexOutOfBoundsException iobe) {

		}
	}

	/**
	 * Axiom for <a href ="http://java.sun.com/javase/6/docs/api/java/util/List.html#indexOf(java.lang.Object)"
	 * > List.indexOf(java.lang.Object)</a>Returns the index of the first
	 * occurrence of the specified element in this list, or -1 if this list does
	 * not contain the element.
	 * 
	 * @param <E>
	 * @param l
	 *            the list that is tested
	 * @param index
	 *            - index of the element to return
	 * @param e
	 *            - element to be stored
	 * */
	public static <E> void returnTheIndexOfFirstOccurrenceInList(List<E> l,
			int index, E e) {

		try {

			if (l.contains(e)) {
				int index1 = 0;
				for (E element : l) {
					if (element.equals(e)) {
						break;
					}
					index1++;
				}

			} else {
				index = -1;
			}

			assertEquals(l.indexOf(e), index);

		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		}
	}

	/**
	 * Axiom for <a href ="http://java.sun.com/javase/6/docs/api/java/util/List.html#lastIndexOf(java.lang.Object)"
	 * > List.lastIndexOf(java.lang.Object)</a> Returns the index of the last
	 * occurrence of the specified element in this list, or -1 if this list does
	 * not contain the element
	 * 
	 * @param <E>
	 * @param l
	 *            the list that is tested
	 * @param index
	 *            - index of the element to return
	 * 
	 **/

	public static <E> void returnTheLastIndexOfInList(List<E> l, int index, E e) {

		try {
			int index1 = 0;
			for (int i = 0; i < l.size(); i++) {
				if (l.get(index1).equals(e)) {
					index1 = i;
				}
				index1++;
			}

			assertEquals(l.lastIndexOf(e), index1);
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		}
	}

	/**
	 * Axiom for <a href =
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#iterator()">
	 * List.iterator()</a> Returns an iterator over the elements in this list in
	 * proper sequence.
	 * 
	 * @param <E>
	 * @param l
	 *            the list that is tested
	 * @param index
	 *            - index of the element to return
	 **/

	public static <E> void returnAnIterator(List<E> l, int index, E e) {

		Iterator<E> i = l.iterator();
		for (int j = 0; j < l.size(); j++) {
			assertSame(
					"The iterator didn't contain the same elements in the same order as the list.",
					l.get(j), i.next());
		}
	}

	/**
	 * Axiom for <a href
	 * ="http://java.sun.com/javase/6/docs/api/java/util/List.html#hashCode()" >
	 * List.hashCode()</a> Returns the hash code value for this list.
	 * 
	 **/
	public static <E> void returnHashCodeValue(List<E> l, int index, E e) {

		int hashCode = 1;
		Iterator<E> i = l.iterator();
		while (i.hasNext()) {
			E obj = i.next();
			hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
		}
		assertEquals(l.hashCode(), hashCode);
	}

	/**
	 * Axiom for <a href ="http://java.sun.com/javase/6/docs/api/java/util/List.html#equals(java.lang.Object)"
	 * > List.equals(java.lang.Object)</a> Compares the specified object with
	 * this list for equality. Returns true if and only if the specified object
	 * is also a list, both lists have the same size, and all corresponding
	 * pairs of elements in the two lists are equal. (Two elements e1 and e2 are
	 * equal if (e1==null ? e2==null : e1.equals(e2)).) In other words, two
	 * lists are defined to be equal if they contain the same elements in the
	 * same order. This definition ensures that the equals method works properly
	 * across different implementations of the List interface.
	 * 
	 */
	public static <E> void equals(List<E> l1, Object o) {
		if (o instanceof List) {
			List<Object> l2 = (List<Object>) o;

			boolean equal = true;
			if (l1.size() == l2.size()) {
				for (int i = 0; i < l1.size(); i++) {
					E e1 = l1.get(i);
					Object e2 = l2.get(i);
					equal &= (e1 == null ? e2 == null : e1.equals(e2));
				}
				assertEquals(equal, l1.equals(l2));
			} else {
				assertFalse(l1.equals(o));
			}
		} else {
			assertFalse(l1.equals(o));
		}
	}

	/**
	 * Axiom for <a href =
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#remove(int)" >
	 * List.remove(int)</a> Removes the element at the specified position in
	 * this list Shifts any subsequent elements to the left (subtracts one from
	 * their indices). Returns the element that was removed from the list.
	 */

	public static <E> void removeElementatSpecifiedPosition(List<E> l, E t,
			int index) {
		try {
			int listSize = l.size();

			if (index >= 0 && index <= l.size()) {

				E oldValueFromList = l.get(index);

				E oldValueFromListInIndex = l.get(index + 1);

				assertSame(l.remove(index), oldValueFromList);
				assertSame(l.get(index), oldValueFromListInIndex);
				assertSame(listSize, l.size());

			} else {
				try {
					l.remove(index);
				} catch (IndexOutOfBoundsException iobe) {

				}
			}

		} catch (NullPointerException npe) {

		} catch (ClassCastException cce) {

		} catch (UnsupportedOperationException uspe) {

		}
	}

	/**
	 * Axiom for <a href ="http://java.sun.com/javase/6/docs/api/java/util/List.html#remove(java.lang.Object)"
	 * > List.remove(java.lang.Object)</a>Removes the first occurrence of the
	 * specified element from this list, if it is present If this list does not
	 * contain the element, it is unchanged
	 * **/
	public static <E> void removeFirstOccurenceOfSpecifiedElement(List<E> l,
			Object o) {
		try {
			int size = l.size();

			if (l.contains(o)) {
				int index;
				for (index = 0; index <= l.size(); index++) {
					if (l.get(index).equals(o)) {
						break;
					}
				}
				E oldValue = l.get(index);
				E oldNextValue = l.get(index + 1);

				l.remove(o);

				// this may not be correct because there maybe same objects in
				// the list several times
				assertSame(l.get(index), oldNextValue);
				assertEquals(l.size(), size - 1);

			} else {
				assertFalse(l.remove(o));
			}
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (UnsupportedOperationException usoe) {
			// OK
		}
	}

	// subList ---------------------------------------------------------------
	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#subList(int,%20
	 * i n t ) > subList(int, int)</a>: "subList throws
	 * IndexOutOfBoundsException for an illegal endpoint index value (fromIndex
	 * < 0 || toIndex > size || fromIndex > toIndex)"
	 * 
	 * @param <E>
	 * @param l
	 *            the List to be tested
	 * @param fromIndex
	 *            the first index of the sublist (inclusive)
	 * @param toIndex
	 *            the last index of the sublist (exclusive)
	 */
	public static <E> void subListThrowsIndexOutOfBounds(List<E> l,
			int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex > l.size() || fromIndex > toIndex) {
			try {
				l.subList(fromIndex, toIndex);
				fail("subList should throw IndexOutOfBoundsException for illegal endpoint index values (fromIndex < 0 || toIndex > size || fromIndex > toIndex)");
			} catch (IndexOutOfBoundsException e) {
				// success();
			}
		} else {
			try {
				l.subList(fromIndex, toIndex);
			} catch (IndexOutOfBoundsException e) {
				fail("subList should NOT throw IndexOutOfBoundsException if not (fromIndex < 0 || toIndex > size || fromIndex > toIndex)");
			}
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#subList(int,%20int)"
	 * > subList(int, int)</a>:
	 * "If fromIndex and toIndex are equal, the returned list is empty."
	 * 
	 * @param <E>
	 * @param l
	 *            the List to be tested
	 * @param fromIndex
	 *            the first index of the sublist (inclusive)
	 * @param toIndex
	 *            the last index of the sublist (exclusive)
	 */
	public static <E> void subListEmptyIfIndicesEqual(List<E> l, int fromIndex,
			int toIndex) {
		if (fromIndex == toIndex) {
			assertEquals(
					"If fromIndex and toIndex are equal, the returned list should be empty.",
					0, l.subList(fromIndex, toIndex).size());
		}
	}

	// subList in combinations ------------------------------------------------
	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#subList(int,%20int)"
	 * > subList(int, int)</a> and <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#clear()"
	 * >clear()</a>:
	 * 
	 * @param <E>
	 * @param l
	 *            the List to be tested
	 * @param fromIndex
	 *            the first index of the sublist (inclusive)
	 * @param toIndex
	 *            the last index of the sublist (exclusive)
	 */
	// clear
	public static <E> void subListClearProperty1(List<E> l, int fromIndex,
			int toIndex) {

		List<E> sublistBefore = l.subList(0, fromIndex);
		List<E> sublistAfter = l.subList(toIndex, l.size());

		try {
			List<E> sublist = l.subList(fromIndex, toIndex);
			sublist.clear();

			int sublistSize = fromIndex - toIndex;
			assertEquals(
					"Calling clear on a sublist didn't decrease the size of the original list correspondingly",
					l.size() - sublistSize, l.size());

			assertEquals(
					"After clearing a sublist the original list was changed outside the sublist range.",
					sublistBefore, l.subList(0, fromIndex));
			assertEquals(
					"After clearing a sublist the original list was changed outside the sublist range.",
					sublistAfter, l.subList(fromIndex, l.size()));

		} catch (IndexOutOfBoundsException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#subList(int,%20int)"
	 * > subList(int, int)</a> and <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#add(E)"
	 * >add(E)</a>: "Appends the specified element to the end of the sublist"
	 * 
	 * @param <E>
	 * @param l
	 *            the List to be tested
	 * @param fromIndex
	 *            the first index of the sublist (inclusive)
	 * @param toIndex
	 *            the last index of the sublist (exclusive)
	 */
	public static <E> void subListAdd(List<E> l, int fromIndex, int toIndex, E e) {
		fromIndex = fromIndex % l.size();
		toIndex = toIndex % l.size();
		if (fromIndex < toIndex) {
			int tmp = toIndex;
			toIndex = fromIndex;
			fromIndex = tmp;
		}
		List<E> sublist = l.subList(fromIndex, toIndex);
		try {
			sublist.add(e);
			assertSame(
					"After calling add(E) on a sublist, the added element should be at position toIndex in the original list",
					l.get(toIndex), e);

		} catch (UnsupportedOperationException use) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException iae) {
			// OK
		}

	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#subList(int,%20int)"
	 * > subList(int, int)</a> and <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#add(int,%20E)"
	 * >add(int, E)</a>:
	 * 
	 * @param <E>
	 * @param l
	 *            the List to be tested
	 * @param fromIndex
	 *            the first index of the sublist (inclusive)
	 * @param toIndex
	 *            the last index of the sublist (exclusive)
	 * @param index
	 *            the index in the sublist to add an element at
	 * @param e
	 *            an element to be added to the sublist
	 */
	public static <E> void subListAddByIndex(List<E> l, int fromIndex,
			int toIndex, int index, E e) {
		try {
			fromIndex = fromIndex % l.size();
			toIndex = toIndex % l.size();
			if (fromIndex < toIndex) {
				int tmp = toIndex;
				toIndex = fromIndex;
				fromIndex = tmp;
			}

			List<E> sublist = l.subList(fromIndex, toIndex);
			index = index % sublist.size();
			sublist.add(index, e);

			assertSame(
					"After calling add(index, E) on a sublist, the added element should be at position (fromIndex + index) in the original list",
					l.get(toIndex), e);

		} catch (UnsupportedOperationException use) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException iae) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#subList(int,%20int)"
	 * > subList(int, int)</a> and <a href="http://java.sun.com/javase/6/docs/api/java/util/List.html#addAll(java.util.Collection)"
	 * >addAll(java.util.Collection)</a>:
	 * 
	 * @param <E>
	 * @param l
	 *            the List to be tested
	 * @param fromIndex
	 *            the first index of the sublist (inclusive)
	 * @param toIndex
	 *            the last index of the sublist (exclusive)
	 */
	public static <E> void subListAddAll(List<E> l, int fromIndex, int toIndex,
			Collection<? extends E> c) {
		try {
			fromIndex = fromIndex % l.size();
			toIndex = toIndex % l.size();
			if (fromIndex < toIndex) {
				int tmp = toIndex;
				toIndex = fromIndex;
				fromIndex = tmp;
			}
			List<E> sublist = l.subList(fromIndex, toIndex);
			sublist.addAll(c);

			List<E> newSublist = l.subList(toIndex, toIndex + c.size());

			Iterator<? extends E> it = c.iterator();
			for (E e : newSublist) {
				assertSame(
						"After calling addAll on a sublist, the elements was not added correctly to the original list.",
						e, it.next());
			}
		} catch (UnsupportedOperationException use) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException iae) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#subList(int,%20int)"
	 * > subList(int, int)</a> and <a href="http://java.sun.com/javase/6/docs/api/java/util/List.html#addAll(int, %20java.util.Collection)"
	 * >addAll(int, java.util.Collection)</a>:
	 * 
	 * @param <E>
	 * @param l
	 *            the List to be tested
	 * @param fromIndex
	 *            the first index of the sublist (inclusive)
	 * @param toIndex
	 *            the last index of the sublist (exclusive)
	 */
	public static <E> void subListAddAllByIndex(List<E> l, int fromIndex,
			int toIndex, Collection<? extends E> c, int index) {
		try {
			fromIndex = fromIndex % l.size();
			toIndex = toIndex % l.size();
			if (fromIndex < toIndex) {
				int tmp = toIndex;
				toIndex = fromIndex;
				fromIndex = tmp;
			}

			List<E> sublist = l.subList(fromIndex, toIndex);
			index = index % sublist.size();

			sublist.addAll(index, c);

			List<E> newSublist = l.subList(fromIndex + index, fromIndex + index
					+ c.size());

			Iterator<? extends E> it = c.iterator();
			for (E e : newSublist) {
				assertSame(
						"After calling addAll on a sublist, the elements was not added correctly to the original list.",
						e, it.next());
			}
		} catch (UnsupportedOperationException use) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		} catch (IllegalArgumentException iae) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#subList(int,%20int)"
	 * > subList(int, int)</a> and <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#remove(int)"
	 * >remove(int)</a>:
	 * 
	 * @param <E>
	 * @param l
	 *            the List to be tested
	 * @param fromIndex
	 *            the first index of the sublist (inclusive)
	 * @param toIndex
	 *            the last index of the sublist (exclusive)
	 */
	public static <E> void subListRemoveByIndex(List<E> l, int fromIndex,
			int toIndex, int index) {
		try {
			fromIndex = fromIndex % l.size();
			toIndex = toIndex % l.size();
			if (fromIndex < toIndex) {
				int tmp = toIndex;
				toIndex = fromIndex;
				fromIndex = tmp;
			}
			List<E> sublist = l.subList(fromIndex, toIndex);
			index = index % sublist.size();

			int oldSize = l.size();

			sublist.remove(index);

			assertEquals(
					"After calling remove(int) on a sublist the size of the original list should decrease by one.",
					oldSize - 1, l.size());
		} catch (UnsupportedOperationException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#subList(int,%20int)"
	 * > subList(int, int)</a> and <a href="http://java.sun.com/javase/6/docs/api/java/util/List.html#remove(java.lang.Object)"
	 * >remove(java.lang.Object)</a>:
	 * 
	 * @param <E>
	 * @param l
	 *            the List to be tested
	 * @param fromIndex
	 *            the first index of the sublist (inclusive)
	 * @param toIndex
	 *            the last index of the sublist (exclusive)
	 */
	public static <E> void subListRemoveByObject(List<E> l, int fromIndex,
			int toIndex, int offset, Object o) {
		try {
			fromIndex = fromIndex % l.size();
			toIndex = toIndex % l.size();
			if (fromIndex < toIndex) {
				int tmp = toIndex;
				toIndex = fromIndex;
				fromIndex = tmp;
			}

			List<E> sublist = l.subList(fromIndex, toIndex);
			if (sublist.contains(o)) {
				int index = sublist.indexOf(o);
				// An offset between index and end of the list
				offset = offset % (l.size() - 1 - fromIndex + index);
				int size = l.size();
				E e = l.get(fromIndex + index + offset);

				sublist.remove(o);

				assertEquals(
						"When calling remove on a sublist the size of the original list should decrease by one.",
						size - 1, l.size());
				assertSame(
						"After calling remove on a sublist the elements in the original list should be shifted left.",
						e, l.get(fromIndex + index + offset - 1));
			}
		} catch (UnsupportedOperationException use) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#subList(int,%20int)"
	 * > subList(int, int)</a> and <a href="http://java.sun.com/javase/6/docs/api/java/util/List.html#removeAll(java.util.Collection)"
	 * >removeAll(java.util.Collection)</a>:
	 * 
	 * @param <E>
	 * @param l
	 *            the List to be tested
	 * @param fromIndex
	 *            the first index of the sublist (inclusive)
	 * @param toIndex
	 *            the last index of the sublist (exclusive)
	 */
	// TODO I'm not sure about this one.
	public static <E> void subListRemoveAll(List<E> l, int fromIndex,
			int toIndex, Collection<? extends E> c) {
		try {
			fromIndex = fromIndex % l.size();
			toIndex = toIndex % l.size();
			if (fromIndex < toIndex) {
				int tmp = toIndex;
				toIndex = fromIndex;
				fromIndex = tmp;
			}
			List<E> sublist = l.subList(fromIndex, toIndex);
			int size = l.size();

			sublist.removeAll(c);

			int removedElements = size - l.size();

			/*
			 * Subtract from the range the number of elements that was removed,
			 * and create a new list from that range. This should be the
			 * elements in the sublist that was not removed. So newList should
			 * contain no element in c.
			 */
			ArrayList<E> newList = new ArrayList<E>();
			for (int i = fromIndex; i < toIndex - removedElements; i++) {
				newList.add(l.get(i));
			}
			for (E e : c) {
				assertFalse(
						"All the elements in c was not removed from the list by calling removeAll on a subList.",
						newList.contains(e));
			}
		} catch (UnsupportedOperationException use) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#subList(int,%20int)"
	 * > subList(int, int)</a> and <a href="http://java.sun.com/javase/6/docs/api/java/util/List.html#retainAll(java.util.Collection)"
	 * >retainAll(java.util.Collection)</a>:
	 * 
	 * @param <E>
	 * @param l
	 *            the List to be tested
	 * @param fromIndex
	 *            the first index of the sublist (inclusive)
	 * @param toIndex
	 *            the last index of the sublist (exclusive)
	 */
	public static <E> void subListRetainAll(List<E> l, int fromIndex,
			int toIndex, Collection<? extends E> c) {
		try {
			fromIndex = fromIndex % l.size();
			toIndex = toIndex % l.size();
			if (fromIndex < toIndex) {
				int tmp = toIndex;
				toIndex = fromIndex;
				fromIndex = tmp;
			}
			List<E> sublist = l.subList(fromIndex, toIndex);
			int size = l.size();

			sublist.retainAll(c);

			int removedElements = size - l.size();

			/*
			 * Subtract from the range the number of elements that was removed.
			 * This should be the elements in the sublist that was not removed.
			 * This range should contain only elements in c.
			 */
			for (int i = fromIndex; i < toIndex - removedElements; i++) {
				assertTrue(
						"After calling retainAll on a sublist, the selected "
								+ "range in the original list contained an element not "
								+ "in the retained collection.", c.contains(l
								.get(i)));
			}
		} catch (UnsupportedOperationException use) {
			// OK
		} catch (ClassCastException cce) {
			// OK
		} catch (NullPointerException npe) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#subList(int,%20int)"
	 * > subList(int, int)</a> and <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/List.html#set(int,%20E)"
	 * >set(int, E)(java.util.Collection)</a>:
	 * 
	 * @param <E>
	 * @param l
	 *            the List to be tested
	 * @param fromIndex
	 *            the first index of the sublist (inclusive)
	 * @param toIndex
	 *            the last index of the sublist (exclusive)
	 */
	public static <E> void subListSet(List<E> l, int fromIndex, int toIndex,
			E e, int index) {
		fromIndex = fromIndex % l.size();
		toIndex = toIndex % l.size();
		if (fromIndex < toIndex) {
			int tmp = toIndex;
			toIndex = fromIndex;
			fromIndex = tmp;
		}
		List<E> sublist = l.subList(fromIndex, toIndex);
		index = index % sublist.size();
		int size = l.size();

		sublist.set(index, e);

		assertEquals(
				"The size of the original list changed when calling set on a sublist.",
				size, l.size());
		assertSame(
				"After calling set on a sublist, the element in the position "
						+ "fromIndex + index in the original list was not the inserted element.",
				e, l.get(fromIndex + index));
	}

}
