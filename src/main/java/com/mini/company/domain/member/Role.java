package com.mini.company.domain.member;

import lombok.Getter;

@Getter
public enum Role {
    MANAGER("MANAGER"),
    MEMBER("MEMBER");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}
