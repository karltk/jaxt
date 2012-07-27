package jaxt.java.util;

import static junit.framework.Assert.*;
import java.util.Collection;

/**
 * This optional axiom should be enabled for sub-types of Collection where 
 * retainAll is implemented (does not throw UnsupportedOperationException).
 */
abstract public class CollectionOptionalRetainAllImplementedAxioms<T> implements
jaxt.framework.OptionalAxioms<java.util.Collection<T>> {

	public static <T> void retainAllShouldNotThrowUnsupportedException(Collection<T> c1, Collection<T> c2) {
		try {
			c1.retainAll(c2);
		} catch (UnsupportedOperationException e) {
			fail("retainAll() is required for this class, but threw UnsupportedOperationsException." + e);
		
		}  catch (ClassCastException e) {
			// OK
		} catch (NullPointerException e) {
			// OK
		}
	}
}

