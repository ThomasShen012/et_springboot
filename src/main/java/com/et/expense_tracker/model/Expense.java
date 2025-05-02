package com.et.expense_tracker.model;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDate;

import io.micrometer.common.lang.NonNull;

@Entity
@Data
@Table(name = "expenses")
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String description;

    @NonNull
    private Double amount;

    @NonNull
    private LocalDate date;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Category category;
    
    @NonNull
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
}
