package io.github.gabrielhenriquehe.investment_api.infrastructure.enums;

import lombok.Getter;

@Getter
public enum InvestmentTypeEnum {
    FUNDS("FUNDS"),
    STOCKS("STOCKS"),
    SECURITIES("SECURITIES");

    private final String value;

    InvestmentTypeEnum(String value){
        this.value = value;
    }
}
