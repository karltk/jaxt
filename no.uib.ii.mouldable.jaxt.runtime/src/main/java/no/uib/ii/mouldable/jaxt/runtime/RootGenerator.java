/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import no.uib.ii.mouldable.jaxt.runtime.generators.BooleanGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.ByteGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.DoubleGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.FloatGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.GenericObjectGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.IntegerGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.LongGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.StringGenerator;

public class RootGenerator extends GenericObjectGenerator {

    public RootGenerator() {
        super(null);
        generate(byte.class).using(new ByteGenerator());
        generate(long.class).using(new LongGenerator());
        generate(float.class).using(new FloatGenerator());
        generate(double.class).using(new DoubleGenerator());
        generate(boolean.class).using(new BooleanGenerator());
        generate(int.class).using(new IntegerGenerator());
        generate(String.class).using(new StringGenerator());
        generate(Collection.class).using(new SpecificGenerator<Collection<?>>() {
            @Override
            public Collection<?> yield(final Annotation annotation) {
                return Arrays.asList();
            }

            @Override
            public Class<Collection<?>> getType() {
                @SuppressWarnings({ "unchecked" })
                Class<Collection<?>> r = (Class<Collection<?>>) (Object) Collection.class;
                return r;
            }
        });
    }

    public GenericObjectGenerator newScope() {
        return new GenericObjectGenerator(this);
    }

    public <T> GenericRegisterer<T> newContextFor(final Class<T> clazz) {
        final GenericGenerator gg = new GenericObjectGenerator(this);
        GenericRegisterer<T> r = new GenericRegisterer<T>(gg, clazz);
        generate(clazz).using(new SpecificGenerator<T>() {

            @Override
            public T yield(final Annotation annotation) {
                return gg.yield(clazz, annotation);
            }

            @Override
            public Class<T> getType() {
                return clazz;
            }
        });
        return r;
    }
}
