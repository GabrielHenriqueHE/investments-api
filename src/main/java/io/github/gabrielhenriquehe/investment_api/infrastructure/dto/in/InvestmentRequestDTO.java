package io.github.gabrielhenriquehe.investment_api.infrastructure.dto.in;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record InvestmentRequestDTO(
        String name,
        String type,
        BigDecimal amount,
        OffsetDateTime datetime
) {
}
