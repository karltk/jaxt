package jaxt.framework;

/**
 * Empty interface indicating to JAxT that a class contains required axioms.
 * These are axioms a class or an interface and all its subtypes are required to
 * satisfy.
 * <p>
 * Required axiom are both {@link jaxt.framework.ThisclassAxioms} and
 * {@link jaxt.framework.SubclassAxioms}.
 * <p>
 * The interface's only purpose is to allow the JAxT tool to identify that a
 * class <code>XA</code> is an axiom class for a class/interface <code>X</code>
 * and all its subinterfaces and implementing classes and their subclasses, by
 * declaring that <code>XA implements RequiredAxioms&lt;X&gt;</code>.
 * 
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 */
public interface RequiredAxioms<T> extends Axioms<T> {

}
