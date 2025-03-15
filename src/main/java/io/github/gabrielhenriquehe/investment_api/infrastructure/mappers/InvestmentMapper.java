package io.github.gabrielhenriquehe.investment_api.infrastructure.mappers;

import io.github.gabrielhenriquehe.investment_api.infrastructure.dto.in.InvestmentRequestDTO;
import io.github.gabrielhenriquehe.investment_api.infrastructure.dto.out.InvestmentResponseDTO;
import io.github.gabrielhenriquehe.investment_api.infrastructure.entities.Investment;
import io.github.gabrielhenriquehe.investment_api.infrastructure.enums.InvestmentTypeEnum;

public class InvestmentMapper {

    public static Investment DTOToInvestmentEntity(InvestmentRequestDTO dto) {
        return new Investment(
                dto.name(),
                InvestmentTypeEnum.valueOf(dto.type().toUpperCase()),
                dto.amount().setScale(2),
                dto.datetime()
        );
    }

    public static InvestmentResponseDTO investmentToInvestmentResponseDTO(Investment investment) {
        return new InvestmentResponseDTO(
                investment.getId(),
                investment.getName(),
                investment.getType().getValue(),
                investment.getAmount(),
                investment.getDatetime()
        );
    }
}
