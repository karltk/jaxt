/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

public class LongGenerator implements Generator<Long> {

    @Override
    public Long generate() {
        // FIXME this is not uniform by a long shot
        return Double.doubleToLongBits(Math.random());
    }

}
