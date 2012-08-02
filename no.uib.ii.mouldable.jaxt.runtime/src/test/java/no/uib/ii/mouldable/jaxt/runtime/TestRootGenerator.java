/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import no.uib.ii.mouldable.jaxt.runtime.generators.ClassHierarchyAwareObjectGenerator;
import no.uib.ii.mouldable.jaxt.runtime.generators.GenericObjectGenerator;
import org.junit.Before;
import org.junit.Test;

public class TestRootGenerator {

    private RootGenerator rootGenerator;

    @Before
    public void setUp() {
        rootGenerator = new RootGenerator();
    }

    @Test
    public void testPrimitiveGenerators() {
        assertEquals(Byte.class, rootGenerator.yield(byte.class).getClass());
        assertEquals(Boolean.class, rootGenerator.yield(boolean.class).getClass());
        assertEquals(Integer.class, rootGenerator.yield(int.class).getClass());
        assertEquals(Long.class, rootGenerator.yield(long.class).getClass());
        assertEquals(Float.class, rootGenerator.yield(float.class).getClass());
        assertEquals(Double.class, rootGenerator.yield(double.class).getClass());
    }

    @Test
    public void testNewScope() {
        GenericObjectGenerator scope = rootGenerator.newScope();
        Integer i = new Integer(10);
        scope.generate(Object.class).as(i);

        assertEquals(i, scope.yield(Object.class));
    }

    @Test
    public void testNewScopeWithParentOverrides() {
        D d = D.make(10);
        rootGenerator.generate(D.class).as(d);

        assertEquals(d, rootGenerator.newScope().yield(D.class));
    }

    @Test
    public void testNewScopeAndClassHierarchyGenerator() {
        GenericObjectGenerator scope = rootGenerator.newScope();
        scope.generate(Object.class).using(ClassHierarchyAwareObjectGenerator.from(A.class, rootGenerator));

        assertTrue(A.class.isAssignableFrom(scope.yield(Object.class).getClass()));
    }

    @Test
    public void testNewScopeAndClassHierarchyGeneratorWithOverrideInRoot() {
        GenericObjectGenerator scope = rootGenerator.newScope();
        rootGenerator.generate(Z.class).as(Z.create());
        scope.generate(Object.class).using(ClassHierarchyAwareObjectGenerator.from(E.class, scope));

        assertTrue(E.class.isAssignableFrom(scope.yield(Object.class).getClass()));
    }

}
