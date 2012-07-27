/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import no.uib.ii.mouldable.jaxt.runtime.generators.BooleanGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.DefaultObjectGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.DoubleGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.EnumGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.Generator;
import no.uib.ii.mouldable.jaxt.runtime.generators.IntegerGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.LongGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.SpecificObjectGenerator;

public class JaxtMockery implements Mockery {

    private final Map<Class<?>, Generator<?>> mockers = new HashMap<Class<?>, Generator<?>>();
    private EnumGenerator enumMocker;
    private DefaultObjectGenerator objectMocker;

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
        objectMocker = new DefaultObjectGenerator(this);
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> T mock(final Class<T> clazz) {
        if (mockers.containsKey(clazz))
            return (T) mockers.get(clazz).generate();
        if (Enum.class.isAssignableFrom(clazz)) {
            return (T) enumMocker.generate((Class) clazz);
        }
        if (clazz.isInterface()) {
            throw new IllegalArgumentException("Cannot instantiate interface '" + clazz.getName()
                    + "' because no concrete class has been registered for it");
        }
        return objectMocker.generate(clazz);
    }

    public <T> void registerGenerator(final Class<T> clazz, final Generator<T> integerGenerator) {
        mockers.put(clazz, integerGenerator);
    }

    public <T> SpecificObjectGenerator<T> generate(final Class<T> clazz) {
        SpecificObjectGenerator<T> r = new SpecificObjectGenerator<T>(this, clazz);
        mockers.put(clazz, r);
        return r;
    }

    public <U> void registerConstant(final Class<U> clazz, final U val) {
        mockers.put(clazz, new Generator<U>() {

            @Override
            public U generate() {
                return val;
            }

        });
    }
}
