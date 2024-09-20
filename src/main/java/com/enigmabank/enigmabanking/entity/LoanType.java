package com.enigmabank.enigmabanking.entity;

import com.enigmabank.enigmabanking.constant.ListTables;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = ListTables.LOAN_TYPE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "loan_type_id")
    private String id;

    @Column(unique = true)
    private String type;

    @Column(name = "maximum_loan", columnDefinition = "DOUBLE PRECISION CHECK (maximum_loan >= 0)")
    private Double maxLoan;
}
