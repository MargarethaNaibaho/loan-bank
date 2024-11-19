package com.enigmabank.enigmabanking.entity;

import com.enigmabank.enigmabanking.constant.EApprovalStatus;
import com.enigmabank.enigmabanking.constant.ListTables;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Entity
@Table(name = ListTables.LOAN)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "loan_type_id", nullable = false)
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "instalment_type_id", nullable = false)
    private InstalmentType instalmentType;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "nominal", columnDefinition = "DOUBLE PRECISION CHECK (nominal >= 0)")
    private Double nominal;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Column(name = "approved_at")
    private Long approvedAt;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "rejected_at")
    private Long rejectedAt;

    @Column(name = "rejected_by")
    private String rejectedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status")
    private EApprovalStatus approvalStatus;

    @Column(name = "is_fully_paid")
    private Boolean isFullyPaid;

    @OneToMany(mappedBy = "loan")
    @JsonManagedReference
    private List<LoanDetail> loanDetails;

    @PrePersist
    protected void onCreate() {
        this.createdAt = System.currentTimeMillis();
        this.isFullyPaid = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = System.currentTimeMillis();
    }

    public void setApprovedAt(Long approvedAt){
        if (this.approvedAt != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction has already been approved. You cannot modify it.");
        }
        this.approvedAt = approvedAt;
    }

    public void setApprovedBy(String approvedBy){
        if(this.approvedBy != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction has already been approved. You cannot modify it.");
        }
        this.approvedBy = approvedBy;
    }

    public void setRejectedAt(Long rejectedAt){
        if (this.rejectedAt != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction has already been rejected. You cannot modify it.");
        }
        this.rejectedAt = rejectedAt;
    }

    public void setRejectedBy(String rejectedBy){
        if(this.rejectedBy != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction has already been rejected. You cannot modify it.");
        }
        this.rejectedBy = rejectedBy;
    }
}
