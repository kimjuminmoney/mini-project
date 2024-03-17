package com.mini.company.domain.annual;

import lombok.Getter;

@Getter
public enum AnnualPlan {
    THIS_YEAR(11),
    OTHER(15);
    private int value;

    AnnualPlan(int value) {
        this.value = value;
    }
}
