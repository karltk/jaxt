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
import no.uib.ii.mouldable.jaxt.runtime.generators.DoubleGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.EnumGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.GenericObjectGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.IntegerGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.LongGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.SpecificObjectGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.StringGenerator;

public class JaxtMockery implements GenericGenerator {

    private final Map<Class<?>, SpecificGenerator<?>> mockers = new HashMap<Class<?>, SpecificGenerator<?>>();
    private EnumGenerator enumMocker;
    private GenericObjectGenerator objectMocker;

    public JaxtMockery() {
        mockers.put(long.class, new LongGenerator());
        mockers.put(double.class, new DoubleGenerator());
        mockers.put(boolean.class, new BooleanGenerator());
        mockers.put(int.class, new IntegerGenerator());

        mockers.put(String.class, new StringGenerator());

        mockers.put(Collection.class, new SpecificGenerator<Collection<?>>() {
            @Override
            public Collection<?> yield() {
                return Arrays.asList();
            }

            @Override
            public Class<Collection<?>> getType() {
                @SuppressWarnings({ "unchecked" })
                Class<Collection<?>> r = (Class<Collection<?>>) (Object) Collection.class;
                return r;
            }
        });
        enumMocker = new EnumGenerator();
        objectMocker = new GenericObjectGenerator(this);
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> T yield(final Class<T> clazz) {
        if (mockers.containsKey(clazz))
            return (T) mockers.get(clazz).yield();
        if (Enum.class.isAssignableFrom(clazz)) {
            return (T) enumMocker.generate((Class) clazz);
        }
        if (clazz.isInterface()) {
            throw new IllegalArgumentException("Cannot instantiate interface '" + clazz.getName()
                    + "' because no concrete class has been registered for it");
        }
        return objectMocker.yield(clazz);
    }

    public <T> void registerGenerator(final Class<T> clazz, final SpecificGenerator<T> integerGenerator) {
        mockers.put(clazz, integerGenerator);
    }

    public <T> SpecificObjectGenerator<T> generate(final Class<T> clazz) {
        SpecificObjectGenerator<T> r = new SpecificObjectGenerator<T>(this, clazz);
        mockers.put(clazz, r);
        return r;
    }

    public <U> void registerConstant(final Class<U> clazz, final U val) {
        mockers.put(clazz, new SpecificGenerator<U>() {

            @Override
            public U yield() {
                return val;
            }

            @Override
            public Class<U> getType() {
                return clazz;
            }

        });
    }

    public GenericObjectGenerator newScope() {
        return new GenericObjectGenerator(this);
    }

}
