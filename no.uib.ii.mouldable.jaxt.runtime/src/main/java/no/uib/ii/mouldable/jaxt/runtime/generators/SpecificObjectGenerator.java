/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import java.lang.annotation.Annotation;
import no.uib.ii.mouldable.jaxt.runtime.GenericGenerator;
import no.uib.ii.mouldable.jaxt.runtime.SpecificGenerator;

public class SpecificObjectGenerator<T> implements SpecificGenerator<T> {

    private final Class<T> evidence;
    private final GeneratorOverrideLayer innerGenerator;
    private T yieldConstant = null;

    public SpecificObjectGenerator(final GenericGenerator parent, final Class<T> evidence) {
        this.innerGenerator = new GeneratorOverrideLayer(parent);
        this.evidence = evidence;
    }

    @Override
    public T yield(final Annotation annotation) {
        if (yieldConstant == null)
            return Instantiator.instantiateClass(evidence, innerGenerator);
        return yieldConstant;

    }

    public <U, V extends U> SpecificObjectGenerator<T> using(final Class<U> clazz,
                                                             final SpecificGenerator<V> specificGenerator) {
        innerGenerator.registerOverride(clazz, specificGenerator);
        return this;
    }

    public <U> SpecificObjectGenerator<T> using(final SpecificGenerator<U> specificGenerator) {
        innerGenerator.registerOverride(specificGenerator.getType(), specificGenerator);
        return this;
    }

    public <U> SpecificObjectGenerator<U> generate(final Class<U> clazz) {
        SpecificObjectGenerator<U> r = new SpecificObjectGenerator<U>(innerGenerator, clazz);
        innerGenerator.registerOverride(clazz, r);
        return r;
    }

    public <U> SpecificObjectGenerator<T> as(final T constant) {
        yieldConstant = constant;
        return this;
    }

    @Override
    public Class<T> getType() {
        return evidence;
    }

    public void as(final int i) {
        // TODO Auto-generated method stub

    }
}
