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
    private final Annotation anno;

    public <U extends Annotation> SpecificRegisterer(final Class<T> clazz,
                                                     final Annotation anno,
                                                     final GeneratorOverrideLayer innerGenerator) {
        this.clazz = clazz;
        this.anno = anno;
        this.innerGenerator = innerGenerator;
    }

    public <U extends T> void using(final SpecificGenerator<U> generator) {
        innerGenerator.registerOverride(clazz, anno, generator);
    }

    public void as(final T val) {
        innerGenerator.registerOverride(clazz, anno, new SpecificGenerator<T>() {

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
