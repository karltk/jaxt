/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import no.uib.ii.mouldable.jaxt.runtime.SpecificGenerator;

public class ByteGenerator implements SpecificGenerator<Byte> {

    @Override
    public Byte yield() {
        return (byte) (Math.random() * 255);
    }

    @Override
    public Class<Byte> getType() {
        return byte.class;
    }

}
