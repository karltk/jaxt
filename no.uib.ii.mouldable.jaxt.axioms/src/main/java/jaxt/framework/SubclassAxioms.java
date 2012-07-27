package jaxt.framework;

/**
 * Empty interface indicating to JAxT that a class contains axioms for all
 * subtypes. These are axioms a class need not satisfy, but all subtypes of the
 * class must satisfy these.
 * <p>
 * The interface's only purpose is to allow the JAxT tool to identify that a
 * class <code>XA</code> is an axiom class for all subtypes of a class/interface
 * <code>X</code> by declaring that
 * <code>XA implements OptionalAxioms&lt;X&gt;</code>.
 * 
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 * @see jaxt.framework.RequireAxioms
 * 
 */
public interface SubclassAxioms<T> extends Axioms<T> {

}
