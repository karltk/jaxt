/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import no.uib.ii.mouldable.jaxt.runtime.SpecificGenerator;

public class SpecificEnumGenerator<T extends Enum<T>> implements SpecificGenerator<T> {

    private final EnumInstantiator generator = new EnumInstantiator();
    private final Class<T> evidence;

    public SpecificEnumGenerator(final Class<T> evidence) {
        this.evidence = evidence;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T yield() {
        return (T) generator.instantiate(evidence);
    }

    public static <U extends Enum<U>> SpecificGenerator<U> forClass(final Class<U> clazz) {
        return new SpecificEnumGenerator<U>(clazz);
    }

    @Override
    public Class<T> getType() {
        return evidence;
    }

}
