package org.kodigo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Tax {

    private String name;

    private Double rate;

    public abstract Double getTax();

}