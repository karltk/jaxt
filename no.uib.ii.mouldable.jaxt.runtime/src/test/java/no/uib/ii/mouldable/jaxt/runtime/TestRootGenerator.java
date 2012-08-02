/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TestRootGenerator {

    private JaxtMockery rootGenerator;

    @Before
    public void setUp() {
        rootGenerator = new JaxtMockery();
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

}
