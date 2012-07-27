/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

public class BooleanGenerator implements Generator<Boolean> {

    @Override
    public Boolean generate() {
        return Math.random() > 0.5 ? true : false;
    }

}
