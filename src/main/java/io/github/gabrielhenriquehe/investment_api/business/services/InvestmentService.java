package io.github.gabrielhenriquehe.investment_api.business.services;

import io.github.gabrielhenriquehe.investment_api.business.repositories.InvestmentRepository;
import io.github.gabrielhenriquehe.investment_api.business.validations.InvestmentValidation;
import io.github.gabrielhenriquehe.investment_api.infrastructure.dto.in.InvestmentRequestDTO;
import io.github.gabrielhenriquehe.investment_api.infrastructure.entities.Investment;
import io.github.gabrielhenriquehe.investment_api.infrastructure.enums.InvestmentTypeEnum;
import io.github.gabrielhenriquehe.investment_api.infrastructure.exceptions.ResourceNotFoundException;
import io.github.gabrielhenriquehe.investment_api.infrastructure.mappers.InvestmentMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    private final Set<String> availableInvestmentTypes = Arrays.stream(InvestmentTypeEnum.values())
            .map(InvestmentTypeEnum::getValue)
            .collect(Collectors.toSet());

    @Transactional
    public Investment createInvestment(InvestmentRequestDTO dto) {

        log.info("Initializing investment creation transaction.");
        log.info("Validating fields for creation.");
        this.validateInvestmentCreation(dto);

        log.info("Fields validation completed.");

        Investment investment = InvestmentMapper.DTOToInvestmentEntity(dto);

        log.info("Finishing investment creation transaction.");
        return this.investmentRepository.save(investment);
    }

    @Transactional
    public List<Investment> findAllInvestments() {
        return this.investmentRepository.findAll();
    }

    @Transactional
    public Investment updateInvestment(InvestmentRequestDTO dto, Long id) {
        log.info("Initializing task update transaction.");
        log.info("Searching for investment with ID: ".concat(String.valueOf(id)));
        Investment investment = this.investmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No investment found from ID: ".concat(String.valueOf(id))));

        if (dto.name() != null) {
            log.info("Validating investment name.");
            InvestmentValidation.validateInvestmentName(dto.name());

            investment.setName(dto.name());
        }

        if (dto.type() != null) {
            log.info("Validating investment type.");
            InvestmentValidation.validateInvestmentType(availableInvestmentTypes, dto.type());

            investment.setType(InvestmentTypeEnum.valueOf(dto.type()));
        }

        if (dto.amount() != null) {
            log.info("Validating investment amount.");
            InvestmentValidation.validateInvestmentAmount(dto.amount());

            investment.setAmount(dto.amount().setScale(2));
        }

        if (dto.datetime() != null) {
            log.info("Validating investment datetime.");
            InvestmentValidation.validateInvestmentDatetime(dto.datetime());

            investment.setDatetime(dto.datetime());
        }

        log.info("Finishing task update transaction.");

        return this.investmentRepository.save(investment);
    }

    private void validateInvestmentCreation(InvestmentRequestDTO dto) {
        InvestmentValidation.validateInvestmentName(dto.name());
        InvestmentValidation.validateInvestmentType(availableInvestmentTypes, dto.type());
        InvestmentValidation.validateInvestmentAmount(dto.amount());
        InvestmentValidation.validateInvestmentDatetime(dto.datetime());
    }
}
