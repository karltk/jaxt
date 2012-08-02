/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedList;

public class EnumInstantiator {

    public <T extends Enum<T>> Enum<T> instantiate(final Class<T> clazz) {
        Collection<Field> candidates = new LinkedList<Field>();
        for (Field f : clazz.getDeclaredFields()) {
            if (clazz.isAssignableFrom(f.getType()))
                candidates.add(f);
        }
        return Enum.valueOf(clazz, candidates.iterator().next().getName());
    }
}
