package io.github.gabrielhenriquehe.investment_api.business.repositories;

import io.github.gabrielhenriquehe.investment_api.infrastructure.entities.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
