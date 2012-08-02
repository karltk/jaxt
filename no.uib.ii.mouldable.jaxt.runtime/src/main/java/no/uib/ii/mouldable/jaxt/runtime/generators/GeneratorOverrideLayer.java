/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import no.uib.ii.mouldable.jaxt.runtime.GenericGenerator;
import no.uib.ii.mouldable.jaxt.runtime.SpecificGenerator;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class GeneratorOverrideLayer implements GenericGenerator {

    @SuppressWarnings("rawtypes")
    private final Map<ImmutablePair<Class, Class>, SpecificGenerator<?>> overrides =
            new HashMap<ImmutablePair<Class, Class>, SpecificGenerator<?>>();
    private final GenericGenerator parent;

    public GeneratorOverrideLayer(final GenericGenerator parent) {
        this.parent = parent;
    }

    private static Class<? extends Annotation> getAnnotationType(final Annotation anno) {
        return anno == null ? null : anno.annotationType();
    }

    public boolean hasOverrideFor(final Class<?> clazz, final Annotation a) {
        return overrides.containsKey(ImmutablePair.of(clazz, getAnnotationType(a)));
    }

    @SuppressWarnings("rawtypes")
    public <U, T extends U> void registerOverride(final Class<U> clazz, final Annotation annotation,
                                                  final SpecificGenerator<T> specificGenerator) {
        overrides.put(ImmutablePair.of((Class) clazz, (Class) getAnnotationType(annotation)),
                      specificGenerator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T yield(final Class<T> clazz, final Annotation annotation) {
        ImmutablePair<Class<T>, ?> lu = ImmutablePair.of(clazz, getAnnotationType(annotation));
        if (overrides.containsKey(lu))
            return (T) overrides.get(lu).yield(annotation);
        if (parent == null)
            throw new RuntimeException("Reached top of generator chain, and no appropriate generator for "
                    + clazz + " with annotation " + annotation + " was found");
        return parent.yield(clazz, annotation);
    }

    @Override
    public String toString() {
        return "GeneratorOverrideLayer : " + overrides.toString();
    }
}
