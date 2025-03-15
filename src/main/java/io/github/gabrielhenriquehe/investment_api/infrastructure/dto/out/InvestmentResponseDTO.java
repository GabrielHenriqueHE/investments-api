package io.github.gabrielhenriquehe.investment_api.infrastructure.dto.out;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record InvestmentResponseDTO(
        Long id,
        String name,
        String type,
        BigDecimal amount,
        OffsetDateTime datetime
) {
}
