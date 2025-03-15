package io.github.gabrielhenriquehe.investment_api.infrastructure.exceptions;

import io.github.gabrielhenriquehe.investment_api.infrastructure.dto.out.AppResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalRequestHandler {

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<AppResponseDTO<Void>> handleUnprocessableEntityException(Exception e) {

        AppResponseDTO<Void> response = new AppResponseDTO<>(
                e.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                null
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }
}
