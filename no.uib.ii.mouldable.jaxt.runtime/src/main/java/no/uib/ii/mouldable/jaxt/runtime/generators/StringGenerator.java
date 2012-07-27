/*
 * Copyright (c) 2010-2012, KolibriFX AS. All rights reserved.
 */

package no.uib.ii.mouldable.jaxt.runtime.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import no.uib.ii.mouldable.jaxt.runtime.SpecificGenerator;

public class StringGenerator implements SpecificGenerator<String> {

    private final SecureRandom random = new SecureRandom();

    @Override
    public String yield() {
        return new BigInteger(130, random).toString(32);
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

}
