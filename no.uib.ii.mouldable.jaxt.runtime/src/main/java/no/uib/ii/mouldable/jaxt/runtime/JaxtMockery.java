/*
 * Copyright (c) 2010-2012, KolibriFX AS. All rights reserved.
 */

package no.uib.ii.mouldable.jaxt.runtime;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class JaxtMockery {

    private final Map<Class<?>, Generator<?>> mockers = new HashMap<Class<?>, Generator<?>>();
    private EnumGenerator enumMocker;

    public JaxtMockery() {
        mockers.put(long.class, new Generator<Long>() {

            @Override
            public Long generate() {
                return new Long(1);
            }
        });

        mockers.put(double.class, new Generator<Double>() {

            @Override
            public Double generate() {
                return new Double(0);
            }
        });

        mockers.put(boolean.class, new Generator<Boolean>() {

            @Override
            public Boolean generate() {
                return new Boolean(true);
            }
        });

        mockers.put(int.class, new Generator<Integer>() {

            @Override
            public Integer generate() {
                return new Integer(0);
            }
        });

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
}
