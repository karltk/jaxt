/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import no.uib.ii.mouldable.jaxt.runtime.GenericGenerator;

public class Instantiator {

    private final static EnumInstantiator ENUM_INSTANTIATOR = new EnumInstantiator();

    @SuppressWarnings("unchecked")
    public static <T> T instantiateClass(final Class<T> clazz, final GenericGenerator generator) {
        for (Constructor<?> c : clazz.getConstructors()) {
            try {
                Collection<Object> args = new LinkedList<Object>();
                System.out.println("" + clazz + Arrays.asList(c.getParameterTypes()));
                for (Class<?> param : c.getParameterTypes()) {
                    Object val = generator.yield(param);
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
        return generator.yield(clazz);
    }

    public static <T extends Enum<T>> Enum<T> instantiateEnum(final Class<T> clazz) {
        return ENUM_INSTANTIATOR.instantiate(clazz);
    }

}
