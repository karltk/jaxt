/*
 * Copyright (c) 2010-2012, KolibriFX AS. All rights reserved.
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

public class LongGenerator implements Generator<Long> {

    @Override
    public Long generate() {
        // FIXME this is not uniform by a long shot
        return Double.doubleToLongBits(Math.random());
    }

}
