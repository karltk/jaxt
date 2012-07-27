package jaxt.framework;

/**
 * Empty interface indicating to JAxT that a class satisfies the optional axioms
 * given as the type parameter.
 * <p>
 * The interface's only purpose is to allow the JAxT tool to identify that the
 * axioms from an optional axiom class
 * <code>XA&lt;T&gt; implements OptionalAxioms&lt;T&gt;</code> is to be used.
 * For instance,
 * <code>YA implements ThisclassAxioms&lt;Y&gt;, AxiomSet&lt;XA&lt;Y&gt;&gt;</code>
 * states that class <code>Y</code> (and not its subclasses) will satisfy the
 * optional axioms <code>XA&lt;Y&gt;</code>.
 * 
 * @author Magne Haveraaen & Karl Trygve Kalleberg, 2007
 * 
 * @see jaxt.framework.OptionalAxioms<Object>
 * 
 */
public interface AxiomSet<TA> {

}
