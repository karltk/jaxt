/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

import java.lang.annotation.Annotation;
import no.uib.ii.mouldable.jaxt.runtime.generators.GeneratorOverrideLayer;

public class SpecificRegisterer<T> {

    private final Class<T> clazz;
    private final GeneratorOverrideLayer innerGenerator;

    public SpecificRegisterer(final Class<T> clazz, final GeneratorOverrideLayer innerGenerator) {
        this.clazz = clazz;
        this.innerGenerator = innerGenerator;
    }

    public <U extends T> void using(final SpecificGenerator<U> generator) {
        innerGenerator.registerOverride(clazz, generator);
    }

    public void as(final T val) {
        innerGenerator.registerOverride(clazz, new SpecificGenerator<T>() {

            @Override
            public T yield(final Annotation annotation) {
                return val;
            }

            @Override
            public Class<T> getType() {
                return clazz;
            }
        });
    }
}
