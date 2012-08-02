/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import java.lang.annotation.Annotation;
import no.uib.ii.mouldable.jaxt.runtime.GenericGenerator;
import no.uib.ii.mouldable.jaxt.runtime.SpecificGenerator;
import no.uib.ii.mouldable.jaxt.runtime.SpecificRegisterer;

public class GenericObjectGenerator implements GenericGenerator {

    private final GeneratorOverrideLayer innerGenerator;

    public GenericObjectGenerator(final GenericGenerator parent) {
        this.innerGenerator = new GeneratorOverrideLayer(parent);
    }

    public <U, T extends U> GenericObjectGenerator using(final Class<U> clazz,
                                                         final SpecificGenerator<T> generator) {
        innerGenerator.registerOverride(clazz, generator);
        return this;
    }

    public <T> T yield(final Class<T> clazz) {
        return yield(clazz, null);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public <T> T yield(final Class<T> clazz, final Annotation annotation) {
        if (innerGenerator.hasOverrideFor(clazz, annotation))
            return innerGenerator.yield(clazz, annotation);
        if (clazz.isPrimitive())
            innerGenerator.yield(clazz, annotation);
        if (Enum.class.isAssignableFrom(clazz))
            Instantiator.instantiateEnum((Class) clazz);
        if (clazz.isInterface())
            throw new IllegalArgumentException("Cannot instantiate interface '" + clazz.getName()
                    + "' because no concrete class has been registered for it");

        return Instantiator.instantiateClass(clazz, innerGenerator);
    }

    public <T> SpecificRegisterer<T> generate(final Class<T> clazz) {
        return new SpecificRegisterer<T>(clazz, innerGenerator);
    }

}
