/*
 * Copyright (c) 2010-2012, KolibriFX AS. All rights reserved.
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

public class BooleanGenerator implements Generator<Boolean> {

    @Override
    public Boolean generate() {
        return Math.random() > 0.5 ? true : false;
    }

}
