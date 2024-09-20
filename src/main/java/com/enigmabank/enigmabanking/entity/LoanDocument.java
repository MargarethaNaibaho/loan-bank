package com.enigmabank.enigmabanking.entity;

import com.enigmabank.enigmabanking.constant.ListTables;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = ListTables.LOAN_DOCUMENT)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String name;

    @Column(name = "content_type")
    private String contentType;

    @Column
    private String path;

    @Column
    private Long size;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
