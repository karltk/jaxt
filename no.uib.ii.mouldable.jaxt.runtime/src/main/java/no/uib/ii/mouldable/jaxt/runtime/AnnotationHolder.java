/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

import java.lang.annotation.Annotation;

public class AnnotationHolder implements Annotation {

    private final Class<? extends Annotation> clazz;

    public AnnotationHolder(final Class<? extends Annotation> clazz) {
        this.clazz = clazz;

    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return clazz;
    }

    @Override
    public int hashCode() {
        return clazz.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof AnnotationHolder))
            return false;
        return clazz.equals(((AnnotationHolder) obj).clazz);
    }
}
