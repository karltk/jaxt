package jaxt.java.lang;

import static jaxt.framework.Assert.succeed;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.fail;

/**
 * Properties for the class java.lang.Object itself, as taken from <a
 * url="http://java.sun.com/j2se/1.5.0/docs/api/">Java 5.0 API</a>.
 * 
 * @see java.lang.Object
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 */
abstract public class ObjectAxiomsSpecific implements
		jaxt.framework.ThisclassAxioms<Object> {

	/**
	 * Constructor property 1: an object reference is not null.
	 */
	public static void constructorProperty1notNull() {
		assertNotNull(new Object());
	}

	/**
	 * Constructor property 2: references to distinct objects are not the same.
	 */
	public static void constructorProperty2notSame() {
		assertNotSame(new Object(), new Object());
	}

	/**
	 * equals property 6: implementation in class Object.
	 * <p>
	 * The equals method for class Object implements the most discriminating
	 * possible equivalence relation on objects; that is, for any non-null
	 * reference values x and y, this method returns true if and only if x and y
	 * refer to the same object (x == y has the value true).
	 * 
	 */
	public static void equalsProperty5implementation(Object x, Object y) {
		assertEquals("Compares (" + x.getClass() + ")" + x.toString() + " ("
				+ y.getClass() + ")" + y.toString(), x.equals(y), x == y);
	}

	/**
	 * hashCode property 4: recommended implementation in class Object.
	 * <p>
	 * As much as is reasonably practical, the hashCode method defined by class
	 * Object does return distinct integers for distinct objects. (This is
	 * typically implemented by converting the internal address of the object
	 * into an integer, but this implementation technique is not required by the
	 * JavaTM programming language.)
	 * 
	 */
	public static void hashCodeProperty4implementation(Object a, Object b) {
		if (a == b) {
			succeed("Nothing to check for");
		}
		assertFalse("Compares (" + a.getClass() + ")" + a.toString() + " ("
				+ b.getClass() + ")" + b.toString(), a.hashCode() == b
				.hashCode());
	}

	/**
	 * Null property 1: dereferencing a null reference shall cause a failure.
	 */
	@SuppressWarnings("null")
	public static void nullProperty1dereferenceFailure() {
		try {
			Object a = null;
			a.equals(a);
			fail();
		} catch (NullPointerException e) {
			succeed();
		}
	}

	/**
	 * hashCode property 4: recommended implementation in class Object.
	 * <p>
	 * The toString method for class Object returns a string consisting of the
	 * name of the class of which the object is an instance, the at-sign
	 * character `@', and the unsigned hexadecimal representation of the hash
	 * code of the object. In other words, this method returns a string equal to
	 * the value of: <br>
	 * getClass().getName() + '@' + Integer.toHexString(hashCode())
	 * 
	 * 
	 */
	public static void toStringProperty2value(Object x) {
		assertEquals(x.getClass().getName() + '@'
				+ Integer.toHexString(x.hashCode()), x.toString());
	}
}
