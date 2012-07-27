package jaxt.framework;

/**
 * Empty interface indicating to JAxT that a class contains optional axioms.
 * Optional are recommended axioms for a class (and its subclasses), but are not
 * active axioms for any given class. They are taken in use via the
 * <code>AxiomSet</code> interface.
 * <p>
 * The interface's only purpose is to allow the JAxT tool to identify that a
 * class <code>XA</code> is an optional axiom class for a class/interface
 * <code>X</code> by declaring that
 * <code>XA implements OptionalAxioms&lt;X&gt;</code>.
 * 
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 * @see jaxt.framework.AxiomSet<Object>
 * 
 */
public interface OptionalAxioms<T> extends Axioms<T> {

}
