/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

public class D {

    private D(final int val) {
    }

    public static D make(final int val) {
        return new D(val);
    }
}
