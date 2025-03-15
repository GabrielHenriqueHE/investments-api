package io.github.gabrielhenriquehe.investment_api.infrastructure.dto.out;

public record AppResponseDTO<T>(
        String message,
        Integer status,
        T data
) {
}
