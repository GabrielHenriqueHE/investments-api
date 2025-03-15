package io.github.gabrielhenriquehe.investment_api.business.controllers;

import io.github.gabrielhenriquehe.investment_api.business.services.InvestmentService;
import io.github.gabrielhenriquehe.investment_api.infrastructure.dto.in.InvestmentRequestDTO;
import io.github.gabrielhenriquehe.investment_api.infrastructure.dto.out.AppResponseDTO;
import io.github.gabrielhenriquehe.investment_api.infrastructure.dto.out.InvestmentResponseDTO;
import io.github.gabrielhenriquehe.investment_api.infrastructure.mappers.InvestmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @PostMapping()
    public ResponseEntity<AppResponseDTO<InvestmentResponseDTO>> createInvestment(@RequestBody InvestmentRequestDTO dto) {
        var investments = this.investmentService.createInvestment(dto);

        AppResponseDTO<InvestmentResponseDTO> response = new AppResponseDTO<>(
                "Investment created successfully",
                HttpStatus.CREATED.value(),
                InvestmentMapper.investmentToInvestmentResponseDTO(investments)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<AppResponseDTO<List<InvestmentResponseDTO>>> getAllInvestments() {
        var rawInvestments = this.investmentService.findAllInvestments();

        List<InvestmentResponseDTO> investments = rawInvestments.stream()
                .map(InvestmentMapper::investmentToInvestmentResponseDTO)
                .toList();

        AppResponseDTO<List<InvestmentResponseDTO>> response = new AppResponseDTO<>(
                null,
                HttpStatus.OK.value(),
                investments
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
