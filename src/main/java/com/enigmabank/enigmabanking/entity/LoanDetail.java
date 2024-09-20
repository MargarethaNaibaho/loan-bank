package com.enigmabank.enigmabanking.entity;

import com.enigmabank.enigmabanking.constant.ELoanStatus;
import com.enigmabank.enigmabanking.constant.ListTables;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = ListTables.LOAN_DETAIL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "createdAt", nullable = false)
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Column(name = "transaction_date")
    private Long transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_status")
    private ELoanStatus eLoanStatus;

    @Column(name = "nominal", columnDefinition = "DOUBLE PRECISION CHECK (nominal >= 0)")
    private Double nominal;

    //macam bukti pembayaran ataupun agunan yg bisa berubah ubah
    @OneToOne
    @JoinColumn(name = "guarantee_picture_id")
    private GuaranteePicture guaranteePicture;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "order_id")
    private Loan loan;

    @PrePersist
    protected void onCreate() {
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = System.currentTimeMillis();
    }
}
