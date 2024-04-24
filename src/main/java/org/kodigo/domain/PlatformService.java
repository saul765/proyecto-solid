package org.kodigo.domain;

import lombok.Builder;

@Builder
public class PlatformService extends Tax {


    private static final String NAME = "PLATFORM_SERVICE";

    private static final Double RATE = 0.1;

    public PlatformService(Double rate) {
        super(NAME, rate);
    }

    @Override
    public Double getTax() {
        return this.getRate();
    }
}
