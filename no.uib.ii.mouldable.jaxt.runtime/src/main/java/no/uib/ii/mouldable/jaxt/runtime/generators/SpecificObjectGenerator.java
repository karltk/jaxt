/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import no.uib.ii.mouldable.jaxt.runtime.Mockery;

public class SpecificObjectGenerator<T> implements Generator<T> {

    private final Class<?> evidence;
    private final DefaultObjectGenerator generator;

    public SpecificObjectGenerator(final Mockery mockery, final Class<?> evidence) {
        this.evidence = evidence;
        generator = new DefaultObjectGenerator(mockery);

    }

    @SuppressWarnings("unchecked")
    @Override
    public T generate() {
        return (T) generator.generate(evidence);
    }

    public <U, V extends U> SpecificObjectGenerator<T> using(final Class<U> clazz,
                                                             final Generator<V> specificGenerator) {
        generator.override(clazz, specificGenerator);
        return this;
    }

    public <U> SpecificObjectGenerator<T> using(final TypeAwareGenerator<U> specificGenerator) {
        generator.override(specificGenerator.getType(), specificGenerator);
        return this;
    }
}
