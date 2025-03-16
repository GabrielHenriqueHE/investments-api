package io.github.gabrielhenriquehe.investment_api.business.controllers;

import io.github.gabrielhenriquehe.investment_api.business.services.InvestmentService;
import io.github.gabrielhenriquehe.investment_api.infrastructure.dto.in.InvestmentRequestDTO;
import io.github.gabrielhenriquehe.investment_api.infrastructure.dto.out.AppResponseDTO;
import io.github.gabrielhenriquehe.investment_api.infrastructure.dto.out.InvestmentResponseDTO;
import io.github.gabrielhenriquehe.investment_api.infrastructure.entities.Investment;
import io.github.gabrielhenriquehe.investment_api.infrastructure.mappers.InvestmentMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Create a new investment",
            description = "Uses the provided data in request body to register a new investment"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Investment has been registered successfully."),
            @ApiResponse(responseCode = "422", description = "Invalid parameters for investment register."),
            @ApiResponse(responseCode = "400", description = "Malformed request from client.")
    })

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<AppResponseDTO<InvestmentResponseDTO>> createInvestment(
            @Parameter(description = "Investment data for registration", required = true)
            @RequestBody InvestmentRequestDTO dto
    ) {
        var investments = this.investmentService.createInvestment(dto);

        AppResponseDTO<InvestmentResponseDTO> response = new AppResponseDTO<>(
                "Investment created successfully",
                HttpStatus.CREATED.value(),
                InvestmentMapper.investmentToInvestmentResponseDTO(investments)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "List all registered investments",
            description = "Search in database and returns all registered investments"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Returns all investments registered."
            )
    })

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

    @Operation(
            summary = "Update an investment",
            description = "Uses the provided data to update a registered investment"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investment has been updated successfully."),
            @ApiResponse(responseCode = "422", description = "Invalid parameters for investment update."),
            @ApiResponse(responseCode = "404", description = "No investment found from provided ID."),
            @ApiResponse(responseCode = "400", description = "Malformed request from client.")
    })

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AppResponseDTO<InvestmentResponseDTO>> updateInvestment(
            @Parameter(description = "Investment data for update", required = true)
            @RequestBody InvestmentRequestDTO dto,

            @Parameter(description = "ID of the investment to be updated")
            @PathVariable("id") Long id
    ) {
        var investment = this.investmentService.updateInvestment(dto, id);

        AppResponseDTO<InvestmentResponseDTO> response = new AppResponseDTO<>(
                "Investment data updated successfully",
                HttpStatus.OK.value(),
                InvestmentMapper.investmentToInvestmentResponseDTO(investment)
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Delete an investment",
            description = "Uses the provided ID to delete an investment"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investment has been deleted successfully."),
            @ApiResponse(responseCode = "404", description = "No investment found from provided ID.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponseDTO<Void>> deleteInvestment(
            @Parameter(description = "ID of the investment to be deleted", required = true)
            @PathVariable("id") Long id
    ) {
        this.investmentService.deleteInvestment(id);

        AppResponseDTO<Void> response = new AppResponseDTO<>(
                "Investment deleted successfully",
                HttpStatus.OK.value(),
                null
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
