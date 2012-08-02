/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import jaxt.framework.Axioms;

public class AxiomRunner {

    private final GenericGenerator generator;

    public AxiomRunner(final GenericGenerator generator) {
        this.generator = generator;
    }

    public <T, V extends Axioms<?>> void apply(final Class<T> clazz, final Class<V> axiomClass)
        throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        for (Method m : axiomClass.getDeclaredMethods()) {
            Collection<Object> actualArgs = new LinkedList<Object>();
            System.out.println("" + m + Arrays.asList(m.getParameterTypes()));
            for (Class<?> c : m.getParameterTypes()) {
                Annotation a = null;
                // FIXME deal with multiple annotations
                if (m.getDeclaredAnnotations().length > 0)
                    a = m.getDeclaredAnnotations()[0];
                actualArgs.add(generator.yield(c, a));
            }
            System.out.println("" + m + actualArgs);
            m.invoke(null, actualArgs.toArray(new Object[0]));
        }
    }
}
