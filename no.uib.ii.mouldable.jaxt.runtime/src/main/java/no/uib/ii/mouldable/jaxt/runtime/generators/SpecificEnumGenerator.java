/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

public class SpecificEnumGenerator<T extends Enum<T>> implements Generator<T> {

    private final EnumGenerator generator = new EnumGenerator();
    private final Class<T> evidence;

    public SpecificEnumGenerator(final Class<T> evidence) {
        this.evidence = evidence;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T generate() {
        return (T) generator.generate(evidence);
    }

    public static <U extends Enum<U>> Generator<U> using(final Class<U> clazz) {
        return new SpecificEnumGenerator<U>(clazz);
    }

}
