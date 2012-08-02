/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import java.lang.annotation.Annotation;
import no.uib.ii.mouldable.jaxt.runtime.SpecificGenerator;

public class DoubleGenerator implements SpecificGenerator<Double> {

    @Override
    public Double yield(final Annotation annotation) {
        return Math.random();
    }

    @Override
    public Class<Double> getType() {
        return double.class;
    }
}
