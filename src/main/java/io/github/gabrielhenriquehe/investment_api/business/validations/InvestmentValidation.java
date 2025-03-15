package io.github.gabrielhenriquehe.investment_api.business.validations;

import io.github.gabrielhenriquehe.investment_api.infrastructure.exceptions.UnprocessableEntityException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Slf4j
public class InvestmentValidation {

    public static void validateInvestmentName(String name) {
        if (name == null) {
            String message = "No name provided.";

            log.error(message);
            throw new UnprocessableEntityException(message);
        }

        if (name.isBlank()) {
            String message = "Invalid name provided. Field must be filled correctly.";

            log.error(message);
            throw new UnprocessableEntityException(message);
        }
    }

    public static void validateInvestmentType(Set<String> availableInvestmentTypes, String type) {
        if (type == null) {
            String message = "No type provided.";

            log.error(message);
            throw new UnprocessableEntityException(message);
        }

        if (!availableInvestmentTypes.contains(type.toUpperCase())) {
            String message = "Invalid type provided.";

            log.error(message);
            throw new UnprocessableEntityException(message);
        }
    }

    public static void validateInvestmentAmount(BigDecimal amount) {
        if (amount == null) {
            String message = "No amount provided.";

            log.error(message);
            throw new UnprocessableEntityException(message);
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            String message = "Invalid amount provided. Value must be greater than zero.";
            log.error(message);
            throw new UnprocessableEntityException(message);
        }
    }

    public static void validateInvestmentDatetime(OffsetDateTime datetime) {
        if (datetime == null) {
            String message = "No datetime provided.";

            log.error(message);
            throw new UnprocessableEntityException(message);
        }

        if (datetime.isAfter(OffsetDateTime.now())) {
            String message = "Invalid datetime provided. It can't be a future date.";

            log.error(message);
            throw new UnprocessableEntityException(message);
        }
    }
}
