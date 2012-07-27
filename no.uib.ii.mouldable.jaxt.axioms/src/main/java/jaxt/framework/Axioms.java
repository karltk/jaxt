/**
 * 
 */
package jaxt.framework;

/**
 * Empty interface indicating to JAxT that a class contains axioms, the various
 * axiom subinterfaces indicate what kind of axioms we have. This interface is
 * not to be used directly when declaring axioms.
 * <p>
 * The interface's only purpose is to allow the JAxT tool to identify that a
 * class <code>XA</code> is an axiom class for a class/interface <code>X</code>
 * by declaring (indirectly) that <code>XA implements Axioms&lt;X&gt;</code>.
 * 
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 */
public interface Axioms<T> {

}
