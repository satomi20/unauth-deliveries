package com.unauthdeliveries.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "posting_id", nullable = false)
    private Long id;
    private String matDoc;
    private String item;
    private LocalDate docDate;
    private LocalDate postingDate;
    private String materialDescription;
    private int quantity;
    private String bun;
    private BigDecimal amountLc;
    private String currency;
    private String userName;
    private boolean authorizedDelivery;
}
