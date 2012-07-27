/*
 * Copyright (c) 2010-2012, KolibriFX AS. All rights reserved.
 */

package no.uib.ii.mouldable.jaxt.runtime;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedList;

public class EnumGenerator {

    public <T extends Enum<T>> Enum<T> generate(final Class<T> clazz) {
        Collection<Field> candidates = new LinkedList<Field>();
        for (Field f : clazz.getDeclaredFields()) {
            if (clazz.isAssignableFrom(f.getType()))
                candidates.add(f);
        }
        return Enum.valueOf(clazz, candidates.iterator().next().getName());
    }
}
