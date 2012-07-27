package jaxt.java.util;

import static junit.framework.Assert.*;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * Required axioms for the java interface java.util.Enumeration as taken from <a
 * url="http://java.sun.com/j2se/1.5.0/docs/api/">Java 5.0 API</a>
 * <p>
 * An object that implements the Enumeration interface generates a series of
 * elements, one at a time. Successive calls to the nextElement method return
 * successive elements of the series.
 * 
 * For example, to print all elements of a vector v:
 * 
 * <pre>
 * for (Enumeration e = v.elements(); e.hasMoreElements();) {
 * 	System.out.println(e.nextElement());
 * }
 * </pre>
 * 
 * Methods are provided to enumerate through the elements of a vector, the keys
 * of a hashtable, and the values in a hashtable. Enumerations are also used to
 * specify the input streams to a SequenceInputStream.
 * 
 * NOTE: The functionality of this interface is duplicated by the Iterator
 * interface. In addition, Iterator adds an optional remove operation, and has
 * shorter method names. New implementations should consider using Iterator in
 * preference to Enumeration.
 * <p>
 * These are axioms for the requirements on implementations of the interface.
 * See separate files for recommendations.
 * 
 * @see java.util.Iterator
 * @see java.util.Enumeration
 * @see java.lang.Iterable
 * 
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 */

abstract public class EnumerationAxioms<E> implements
		jaxt.framework.RequiredAxioms<Enumeration<?>> {

	/**
	 * nextElement property 1: failure of nextElement method
	 * <p>
	 * Throws: NoSuchElementException - if no more elements exist.
	 */
	public static <E> void nextElementProperty1failure(Enumeration<E> e) {
		try {
			if (e.hasMoreElements()) {
				e.nextElement();
				return;
			}
			e.nextElement();
			fail();
		} catch (NoSuchElementException e1) {
			// As required
		}
	}

}