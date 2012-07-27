/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import no.uib.ii.mouldable.jaxt.runtime.GenericGenerator;
import no.uib.ii.mouldable.jaxt.runtime.SpecificGenerator;

public class GenericObjectGenerator implements GenericGenerator {

    private final GenericGenerator parent;
    private final Map<Class<?>, SpecificGenerator<?>> overrides =
            new HashMap<Class<?>, SpecificGenerator<?>>();

    public GenericObjectGenerator(final GenericGenerator parent) {
        this.parent = parent;
    }

    public <U, T extends U> GenericObjectGenerator using(final Class<U> clazz,
                                                         final SpecificGenerator<T> generator) {
        overrides.put(clazz, generator);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T yield(final Class<T> clazz) {
        for (Constructor<?> c : clazz.getConstructors()) {
            try {
                Collection<Object> args = new LinkedList<Object>();
                for (Class<?> param : c.getParameterTypes()) {
                    Object val = forward(param);
                    args.add(val);
                }
                // System.out.println(c.getName() + args);
                return (T) c.newInstance(args.toArray(new Object[0]));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Object forward(final Class<?> param) {
        if (overrides.containsKey(param))
            return overrides.get(param).yield();
        return parent.yield(param);
    }

}
