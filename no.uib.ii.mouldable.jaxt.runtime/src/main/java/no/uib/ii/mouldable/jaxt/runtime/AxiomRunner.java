/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

import java.lang.annotation.Annotation;
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
            // System.out.println("" + m +
            // Arrays.asList(m.getParameterTypes()));
            Class<?>[] paramTypes = m.getParameterTypes();
            Annotation[][] paramAnnos = m.getParameterAnnotations();
            for (int i = 0; i < paramTypes.length; i++) {
                Class<?> c = paramTypes[i];
                Annotation[] annos = paramAnnos[i];
                Annotation a = null;
                // FIXME deal with multiple annotations
                if (annos.length > 0)
                    a = annos[0];
                actualArgs.add(generator.yield(c, a));
            }
            // System.out.println("" + m + actualArgs);
            m.invoke(null, actualArgs.toArray(new Object[0]));
        }
    }
}
