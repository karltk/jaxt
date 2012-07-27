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
import no.uib.ii.mouldable.jaxt.runtime.Mockery;

public class DefaultObjectGenerator {

    private final Mockery mockery;
    private final Map<Class<?>, Generator<?>> overrides = new HashMap<Class<?>, Generator<?>>();

    public DefaultObjectGenerator(final Mockery mockery) {
        this.mockery = mockery;
    }

    public <U, T extends U> DefaultObjectGenerator
            override(final Class<U> clazz, final Generator<T> generator) {
        overrides.put(clazz, generator);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T generate(final Class<T> clazz) {
        for (Constructor<?> c : clazz.getConstructors()) {
            try {
                Collection<Object> args = new LinkedList<Object>();
                for (Class<?> param : c.getParameterTypes()) {
                    Object val = mock(param);
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

    private Object mock(final Class<?> param) {
        if (overrides.containsKey(param))
            return overrides.get(param).generate();
        return mockery.mock(param);
    }

}
