/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
            for (Class<?> c : m.getParameterTypes()) {
                actualArgs.add(generator.yield(c));
            }
            System.out.println("" + m + actualArgs);
            m.invoke(null, actualArgs.toArray(new Object[0]));
        }
    }
}
