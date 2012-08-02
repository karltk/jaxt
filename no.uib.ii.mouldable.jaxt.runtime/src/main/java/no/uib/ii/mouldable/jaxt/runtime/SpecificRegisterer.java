/*
 * Copyright (c) 2010-2012, KolibriFX AS. All rights reserved.
 */

package no.uib.ii.mouldable.jaxt.runtime;

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
            public T yield() {
                return val;
            }

            @Override
            public Class<T> getType() {
                return clazz;
            }
        });
    }
}
