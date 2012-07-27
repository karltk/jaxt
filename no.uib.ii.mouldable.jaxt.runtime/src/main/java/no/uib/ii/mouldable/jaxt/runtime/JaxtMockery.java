/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import no.uib.ii.mouldable.jaxt.runtime.generators.BooleanGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.DoubleGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.EnumGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.Generator;
import no.uib.ii.mouldable.jaxt.runtime.generators.IntegerGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.LongGenerator;

public class JaxtMockery {

    private final Map<Class<?>, Generator<?>> mockers = new HashMap<Class<?>, Generator<?>>();
    private EnumGenerator enumMocker;

    public JaxtMockery() {
        mockers.put(long.class, new LongGenerator());
        mockers.put(double.class, new DoubleGenerator());
        mockers.put(boolean.class, new BooleanGenerator());
        mockers.put(int.class, new IntegerGenerator());

        mockers.put(String.class, new Generator<String>() {
            @Override
            public String generate() {
                return "/this string is random";
            }
        });

        mockers.put(Collection.class, new Generator<Collection<?>>() {
            @Override
            public Collection<?> generate() {
                return Arrays.asList();
            }
        });
        enumMocker = new EnumGenerator();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> T mock(final Class<T> clazz) {
        System.out.println(clazz);
        if (mockers.containsKey(clazz))
            return (T) mockers.get(clazz).generate();
        if (Enum.class.isAssignableFrom(clazz)) {
            return (T) enumMocker.generate((Class) clazz);
        }
        for (Constructor<?> c : clazz.getConstructors()) {
            try {
                Collection<Object> args = new LinkedList<Object>();
                for (Class<?> param : c.getParameterTypes()) {
                    Object val = mock(param);
                    args.add(val);
                }
                System.out.println(c.getName() + args);
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

    public <T> void registerGenerator(final Class<T> clazz, final Generator<T> integerGenerator) {
        mockers.put(clazz, integerGenerator);
    }
}
