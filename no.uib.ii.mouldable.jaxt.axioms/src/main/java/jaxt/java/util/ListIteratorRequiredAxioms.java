package jaxt.java.util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.fail;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * These axioms are required for all implementations of <a
 * href="http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html">
 * java.util.<i>ListIterator</></a></i>.
 * 
 * "An iterator for lists that allows the programmer to traverse the list in
 * either direction, modify the list during iteration, and obtain the iterator's
 * current position in the list. A ListIterator has no current element; its
 * cursor position always lies between the element that would be returned by a
 * call to previous() and the element that would be returned by a call to
 * next(). An iterator for a list of length n has n+1 possible cursor
 * positions."
 * <p>
 *"Note that the remove() and set(Object) methods are not defined in terms of
 * the cursor position; they are defined to operate on the last element returned
 * by a call to next() or previous()."
 * <p>
 * As with axioms for Iterator some properties has to be tested in the axioms
 * for the implementing classes, in this case in the axioms for List. These are:
 * <br>
 * 
 * <li>That nextIndex and previousIndex returns the correct index.</li>
 * <li>That remove really removes the correct element from the list.</li>
 * <p>
 * Two methods, next and hasNext is completely covered by
 * IteratorRequiredAxioms. Remove is partly covered by ListIterator, but only
 * the parts that has to do with next.
 * 
 * 
 * 
 * @see java.util.Iterator
 * @see java.util.ListIterator
 * 
 * @author Erlend Birkenes & Madiha Mir, 2009
 */
abstract public class ListIteratorRequiredAxioms<T> implements
		jaxt.framework.RequiredAxioms<java.util.ListIterator<T>> {

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#next()"
	 * >next()</a> and <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#previous()"
	 * >previous()</a>:"Alternating calls to next and previous will return the same element repeatedly."
	 * 
	 * @param <T>
	 * @param iterator
	 */
	public static <T> void alternatningNextPrevious(ListIterator<T> iterator) {
		if (iterator.hasNext())
			assertSame(
					"Alternating calls to next and previous should return the same element",
					iterator.next(), iterator.previous());
		if (iterator.hasPrevious())
			assertSame(
					"Alternating calls to next and previous should return the same element",
					iterator.previous(), iterator.next());
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#add()"
	 * >add()</a>: Property 1 - "The new element is inserted before the implicit
	 * cursor: a subsequent call to next would be unaffected"
	 * 
	 * @param <T>
	 * @param iterator
	 *            the ListIterator to be tested
	 * @param t
	 *            an element to be added by the ListIterator
	 */
	public static <T> void addNextIsUnaffected(ListIterator<T> iterator, T t) {
		try {

			if (iterator.hasNext()) {
				T nextElement = iterator.next();
				iterator.previous();

				assertSame(
						"After calling add, a subsequent call to next should be "
								+ "unaffected. The next element was not the same after add.",
						nextElement, iterator.next());
			} else {
				iterator.add(t);
				try {
					iterator.add(t);
					iterator.next();
					fail("after calling add, a subsequent call to next should be"
							+ "unaffected. There was no next element before add, "
							+ "but there was one after the call.");
				} catch (NoSuchElementException e) {
					// success()
				}
			}
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#add()"
	 * >add()</a>: Property 2 - "The new element is inserted before the implicit
	 * cursor: a subsequent call to next would be unaffected, and a subsequent
	 * call to previous would return the new element."
	 * 
	 * @param <T>
	 * @param iterator
	 *            the ListIterator to be tested
	 * @param t
	 *            an element to be added by the ListIterator
	 */
	public static <T> void addPreviousReturnsNewElement(
			ListIterator<T> iterator, T t) {
		try {
			iterator.add(t);
			assertSame(
					"after calling add, a subsequent call to previous should return the new element.",
					t, iterator.previous());

		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#add()"
	 * >add()</a>: Property 3 - "This call increases by one the value that would
	 * be returned by a call to nextIndex or previousIndex."
	 * 
	 * @param <T>
	 * @param iterator
	 *            the ListIterator to be tested
	 * @param t
	 *            an element to be added by the ListIterator
	 */
	public static <T> void addShouldIncreaseNextIndex(ListIterator<T> iterator,
			T t) {
		try {
			int index = iterator.nextIndex();
			iterator.add(t);
			assertEquals(
					"Calling add should increase by one the value that is returned by"
							+ "a call to nextIndex ", index + 1, iterator
							.nextIndex());

		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#add()"
	 * >add()</a>: Property 4 - "This call increases by one the value that would
	 * be returned by a call to nextIndex or previousIndex."
	 * 
	 * @param <T>
	 * @param iterator
	 *            the ListIterator to be tested
	 * @param t
	 *            an element to be added by the ListIterator
	 */
	public static <T> void addShouldIncreasePreviousIndex(
			ListIterator<T> iterator, T t) {
		try {
			int index = iterator.previousIndex();
			iterator.add(t);
			assertEquals(
					"Calling add should increase by one the value that is returned by"
							+ "a call to previousIndex ", index + 1, iterator
							.previousIndex());

		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href="http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#hasPrevious()"
	 * > hasPrevious()</a> and <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#previous()"
	 * > previous()</a>.
	 * <p>
	 * <a href="http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#hasPrevious()"
	 * > hasPrevious()</a>: Returns true if this list iterator has more elements
	 * when traversing the list in the reverse direction. (In other words,
	 * returns true if previous would return an element rather than throwing an
	 * exception.)
	 * <p>
	 * <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#previous()"
	 * > previous()</a>: Returns the previous element in the list. This method
	 * may be called repeatedly to iterate through the list backwards, or
	 * intermixed with calls to next to go back and forth. (Note that
	 * alternating calls to next and previous will return the same element
	 * repeatedly.)
	 * 
	 * @param <T>
	 * @param iterator
	 *            the Iterator to be tested
	 */
	public static <T> void iteratorPreviousAndHasPrevious(
			ListIterator<T> iterator) {
		if (iterator.hasPrevious()) {
			try {
				iterator.previous();
			} catch (NoSuchElementException e) {
				fail("hasPrevious() was true, but previous() threw NoSuchElementException");
			}
		} else {
			try {
				iterator.previous();
				fail("hasPrevious() was false, but previous() didn't throw NoSuchElementException");
			} catch (NoSuchElementException e) {
				// OK
			}
		}
	}

	// TODO This has to be tested in the list axioms
	/**
	 * Returns the index of the element that would be returned by a subsequent
	 * call to previous. (Returns -1 if the list iterator is at the beginning of
	 * the list.)
	 */
	public static <T> void previousIndex(ListIterator<T> iterator) {
		if (!iterator.hasPrevious()) {
			assertEquals(-1, iterator.previousIndex());
		}
	}

	// TODO This has to be tested in the list axioms
	/**
	 * Returns the index of the element that would be returned by a subsequent
	 * call to next. (Returns list size if the list iterator is at the end of
	 * the list.)
	 */
	public static <T> void nextIndex(ListIterator<T> iterator) {
		if (!iterator.hasNext()) {
			assertEquals(-1, iterator.nextIndex());
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#remove()"
	 * >remove()</a>: Property 1 - "This call can only be made once per call to
	 * next or previous."
	 * <p>
	 * remove() throws UnsupportedOperationException if the remove operation is
	 * not supported by this Iterator.
	 * 
	 * @param <T>
	 * @param iterator
	 *            the iterator to be tested
	 */
	public static <T> void removeOncePerCallToPrevious(ListIterator<T> iterator) {
		if (iterator.hasPrevious()) {
			iterator.previous();
			try {
				iterator.remove();
				try {
					iterator.remove();
					fail("remove() should throw IllegalStateException when called more than once per call to previous()");
				} catch (IllegalStateException e) {
					// success();
				}
			} catch (UnsupportedOperationException e) {
				// OK
			}
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#remove()"
	 * >remove()</a>: Property 2 - "It can be made only if ListIterator.add has
	 * not been called after the last call to (next or) previous."
	 * <p>
	 * remove() throws UnsupportedOperationException if the remove operation is
	 * not supported by this Iterator.
	 * 
	 * @param <T>
	 * @param iterator
	 *            the iterator to be tested
	 */
	public static <T> void removeAddNotCalledSincePrevious(
			ListIterator<T> iterator, T t) {
		try {
			if (iterator.hasPrevious()) {
				iterator.previous();
				iterator.add(t);
				try {
					iterator.remove();
					fail("remove() should throw IllegalStateException if add has been called since the last call to previous()");
				} catch (IllegalStateException e) {
					// success();
				}
			}
		} catch (UnsupportedOperationException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#remove()"
	 * >remove()</a>: Property 3 - "It can be made only if ListIterator.add has
	 * not been called after the last call to next (or previous)."
	 * <p>
	 * remove() throws UnsupportedOperationException if the remove operation is
	 * not supported by this Iterator.
	 * 
	 * @param <T>
	 * @param iterator
	 *            the iterator to be tested
	 * @param t
	 *            an element to be added by the ListIterator
	 */
	public static <T> void removeAddNotCalledSinceNext(
			ListIterator<T> iterator, T t) {
		try {
			if (iterator.hasNext()) {
				iterator.next();
				iterator.add(t);
				try {
					iterator.remove();
					fail("remove() should throw IllegalStateException if add has been called since the last call to next()");
				} catch (IllegalStateException e) {
					// success();
				}
			}
		} catch (UnsupportedOperationException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#set(E)"
	 * >set(E)</a>: Property 1 - Add must not have been called since last call
	 * to previous.
	 * <p>
	 *"This call can be made only if neither ListIterator.remove nor
	 * ListIterator.add have been called after the last call to next or
	 * previous."
	 * 
	 * @param <T>
	 * @param iterator
	 *            the iterator to be tested
	 * @param t
	 *            an element to be added by the ListIterator
	 */
	public static <T> void setExceptionIfAddCalledSincePrevious(
			ListIterator<T> iterator, T t) {
		try {
			if (iterator.hasPrevious()) {
				iterator.previous();
				iterator.add(t);
				try {
					iterator.remove();
					fail("remove() should throw IllegalStateException if add has been called since the last call to previous()");
				} catch (IllegalStateException e) {
					// success();
				}
			}
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#set(E)"
	 * >set(E)</a>: Property 1 - Add must not have been called since last call
	 * to next.
	 * <p>
	 *"This call can be made only if neither ListIterator.remove nor
	 * ListIterator.add have been called after the last call to next or
	 * previous."
	 * 
	 * @param <T>
	 * @param iterator
	 *            the iterator to be tested
	 * @param t
	 *            an element to be added by the ListIterator
	 */
	public static <T> void setExceptionIfAddCalledSinceNext(
			ListIterator<T> iterator, T t) {
		try {
			if (iterator.hasNext()) {
				iterator.next();
				iterator.add(t);
				try {
					iterator.remove();
					fail("set() should throw IllegalStateException if add has been called since the last call to next()");
				} catch (IllegalStateException e) {
					// success();
				}
			}
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#set(E)"
	 * >set(E)</a>: Property 3 - Set can only be called once per call to next.
	 * <p>
	 *"This call can be made only if neither ListIterator.remove nor
	 * ListIterator.add have been called after the last call to next or
	 * previous."
	 * 
	 * @param <T>
	 * @param iterator
	 *            the iterator to be tested
	 * @param t
	 *            an element to be added by the ListIterator
	 */
	public static <T> void setExceptionIfRemoveCalledSinceNext(
			ListIterator<T> iterator) {
		try {
			if (iterator.hasNext()) {
				iterator.next();
				iterator.remove();
				try {
					iterator.remove();
					fail("set() should throw IllegalStateException if remove has already been called since the last call to next()");
				} catch (IllegalStateException e) {
					// success();
				}
			}

		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		}
	}

	/**
	 * Axiom for <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/util/ListIterator.html#set(E)"
	 * >set(E)</a>: Property 3 - Set can only be called once per call to
	 * previous.
	 * <p>
	 *"This call can be made only if neither ListIterator.remove nor
	 * ListIterator.add have been called after the last call to next or
	 * previous."
	 * 
	 * @param <T>
	 * @param iterator
	 *            the iterator to be tested
	 * @param t
	 *            an element to be added by the ListIterator
	 */
	public static <T> void setExceptionIfAddCalledSincePrevious(
			ListIterator<T> iterator) {
		try {
			if (iterator.hasPrevious()) {
				iterator.previous();
				iterator.remove();
				try {
					iterator.remove();
					fail("set() should throw IllegalStateException if remove has already been called since the last call to previous()");
				} catch (IllegalStateException e) {
					// success();
				}
			}
		} catch (UnsupportedOperationException e) {
			// OK
		} catch (ClassCastException e) {
			// OK
		} catch (IllegalArgumentException e) {
			// OK
		}
	}
}