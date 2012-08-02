/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import java.lang.annotation.Annotation;
import no.uib.ii.mouldable.jaxt.runtime.SpecificGenerator;

public class LongGenerator implements SpecificGenerator<Long> {

    @Override
    public Long yield(final Annotation annotation) {
        // FIXME this is not uniform by a long shot
        return Double.doubleToLongBits(Math.random());
    }

    @Override
    public Class<Long> getType() {
        return long.class;
    }

}
