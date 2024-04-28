package org.kodigo.domain;

import lombok.*;
import org.kodigo.sequence.UserSequence;

@Getter
@Setter
@ToString
public class User {

    @Setter(value = AccessLevel.NONE)
    private Integer id;

    private String name;

    private String email;

    private String password;

    private String address;

    @Builder
    private User(String name, String email, String password, String address) {
        this.id = UserSequence.getInstance().getNextId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }
}
