package io.github.gabrielhenriquehe.investment_api.infrastructure.entities;

import io.github.gabrielhenriquehe.investment_api.infrastructure.enums.InvestmentTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "tb_investments")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Investment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private InvestmentTypeEnum type;

    @NonNull
    @Column(nullable = false)
    private BigDecimal amount;

    @NonNull
    @Column(nullable = false)
    private OffsetDateTime datetime;
}
