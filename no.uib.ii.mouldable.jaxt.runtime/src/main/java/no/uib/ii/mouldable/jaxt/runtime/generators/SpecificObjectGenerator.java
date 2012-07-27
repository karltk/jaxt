/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import no.uib.ii.mouldable.jaxt.runtime.GenericGenerator;
import no.uib.ii.mouldable.jaxt.runtime.SpecificGenerator;

public class SpecificObjectGenerator<T> implements SpecificGenerator<T> {

    private final Class<T> evidence;
    private final GenericObjectGenerator generator;

    public SpecificObjectGenerator(final GenericGenerator mockery, final Class<T> evidence) {
        this.evidence = evidence;
        generator = new GenericObjectGenerator(mockery);

    }

    @Override
    public T yield() {
        return generator.yield(evidence);
    }

    public <U, V extends U> SpecificObjectGenerator<T> using(final Class<U> clazz,
                                                             final SpecificGenerator<V> specificGenerator) {
        generator.using(clazz, specificGenerator);
        return this;
    }

    public <U> SpecificObjectGenerator<T> using(final SpecificGenerator<U> specificGenerator) {
        generator.using(specificGenerator.getType(), specificGenerator);
        return this;
    }

    @Override
    public Class<T> getType() {
        return evidence;
    }
}
