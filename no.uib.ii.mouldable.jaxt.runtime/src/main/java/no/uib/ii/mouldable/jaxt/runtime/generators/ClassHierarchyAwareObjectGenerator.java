/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import java.io.IOException;
import java.util.ArrayList;
import no.uib.ii.mouldable.jaxt.runtime.GenericGenerator;
import no.uib.ii.mouldable.jaxt.runtime.ReflectionUtils;
import no.uib.ii.mouldable.jaxt.runtime.SpecificGenerator;

public class ClassHierarchyAwareObjectGenerator<U> implements SpecificGenerator<U> {

    private final Class<U> evidence;
    private final GenericObjectGenerator generator;
    private final ArrayList<Class<?>> allKnownSubclasses;

    public ClassHierarchyAwareObjectGenerator(final Class<U> evidence, final GenericGenerator parent) {
        this.evidence = evidence;
        this.generator = new GenericObjectGenerator(parent);
        allKnownSubclasses = new ArrayList<Class<?>>();
        try {
            allKnownSubclasses.addAll(ReflectionUtils.findAllSubtypes(evidence));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public U yield() {
        int max = allKnownSubclasses.size();/*

        @SuppressWarnings("unchecked")
        Class<U> selected = (Class<U>) allKnownSubclasses.get((int) (Math.random() * max));
        return generator.yield(selected);
    }

    public static <T> ClassHierarchyAwareObjectGenerator<T> from(final Class<T> clazz,
                                                                 final GenericGenerator parent) {
        return new ClassHierarchyAwareObjectGenerator<T>(clazz, parent);
    }

    public ClassHierarchyAwareObjectGenerator<U> except(final Class<?> clazz) {
        allKnownSubclasses.remove(clazz);
        return this;
    }

    @Override
    public Class<U> getType() {
        return evidence;
    }

}
