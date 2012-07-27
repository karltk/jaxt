/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at boblycat dot org>
 * 
 * Licensed under the GNU Lesser General Public License v2.1
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

public class IntegerGenerator implements TypeAwareGenerator<Integer> {

    private final int maxValue;
    private final int minValue;

    public IntegerGenerator(final int minValue, final int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;

        if (maxValue < minValue)
            throw new IllegalArgumentException("Max value must be greated than min value");
    }

    public IntegerGenerator() {
        this(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public Integer generate() {
        return new Integer((int) (Math.random() * (maxValue - minValue) + minValue));
    }

    public static IntegerGenerator constant(final int constant) {
        return new IntegerGenerator(constant, constant);
    }

    @Override
    public Class<Integer> getType() {
        return int.class;
    }

}
