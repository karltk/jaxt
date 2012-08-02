/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

import no.uib.ii.mouldable.jaxt.runtime.generators.GeneratorOverrideLayer;

public class GenericRegisterer<T> {

    private final Class<T> clazz;
    private final GeneratorOverrideLayer innerGenerator;

    public GenericRegisterer(final GenericGenerator parent, final Class<T> clazz) {
        this.clazz = clazz;
        innerGenerator = new GeneratorOverrideLayer(parent);
    }

    public <U> void using(final SpecificGenerator<U> contextOverride) {
        innerGenerator.registerOverride(contextOverride.getType(), contextOverride);
    }

    public <U> SpecificRegisterer<U> generate(final Class<U> clazz) {
        return new SpecificRegisterer<U>(clazz, innerGenerator);
    }

}
