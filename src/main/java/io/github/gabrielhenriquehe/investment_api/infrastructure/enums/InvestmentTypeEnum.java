package io.github.gabrielhenriquehe.investment_api.infrastructure.enums;

import lombok.Getter;

@Getter
public enum InvestmentTypeEnum {
    FUNDS(0),
    STOCKS(1),
    SECURITIES(2);

    private final Integer value;

    InvestmentTypeEnum(Integer value){
        this.value = value;
    }
}
