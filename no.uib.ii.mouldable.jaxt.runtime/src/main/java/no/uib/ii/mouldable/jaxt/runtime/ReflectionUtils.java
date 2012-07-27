/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;

public class ReflectionUtils {

    public static <T> Collection<Class<?>> findAllSubtypes(final Class<T> clazz) throws IOException {
        Enumeration<URL> roots = clazz.getClassLoader().getResources("");
        Collection<Class<?>> collector = new LinkedList<Class<?>>();
        for (URL url : Collections.list(roots)) {
            File root = new File(url.getPath());
            doFindAllSubtypes(root, root, collector, clazz);
        }
        return collector;
    }

    private static void doFindAllSubtypes(final File root, final File dir,
                                          final Collection<Class<?>> collector, final Class<?> parentType)
        throws IOException {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                doFindAllSubtypes(root, file, collector, parentType);
            } else if (file.getName().endsWith(".class")) {
                String fullPath = file.getCanonicalPath();
                String relPath = fullPath.substring(root.getCanonicalPath().length() + 1);
                String className = relPath.replace(".class", "").replace("/", ".");
                collectIfSubtype(className, collector, parentType);
            }
        }
    }

    private static void collectIfSubtype(final String className, final Collection<Class<?>> collector,
                                         final Class<?> parentType) {
        try {
            Class<?> c = Class.forName(className);
            if (parentType.isAssignableFrom(c)) {
                collector.add(c);
            }
        } catch (ClassNotFoundException e) {
            // nothing
        }

    }

}
