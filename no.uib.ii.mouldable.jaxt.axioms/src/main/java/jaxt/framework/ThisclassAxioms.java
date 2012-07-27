package jaxt.framework;

/**
 * Empty interface indicating to JAxT that a class contains axioms only for the
 * class <code>T</code> itself. These are axioms a class or an interface should
 * satisfy, but they are optional for all subtype (and may in fact be unwanted
 * for the subclass).
 * <p>
 * The interface's only purpose is to allow the JAxT tool to identify that a
 * class <code>XA</code> is an axiom class for a class/interface <code>X</code>
 * by declaring that <code>XA implements ThisclassAxioms&lt;X&gt;</code>.
 * 
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007-2008
 * 
 */
public interface ThisclassAxioms<T> extends Axioms<T> {

}
