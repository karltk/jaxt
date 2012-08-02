/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

import java.lang.annotation.Annotation;

public interface GenericGenerator {

    <T> T yield(Class<T> clazz);

    <T> T yield(Class<T> clazz, Annotation declaredAnnotation);
}
