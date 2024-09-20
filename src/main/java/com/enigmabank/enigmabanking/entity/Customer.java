package com.enigmabank.enigmabanking.entity;

import com.enigmabank.enigmabanking.constant.ListTables;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = ListTables.CUSTOMER)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column
    private String phone;

    @Column
    //true for active (default)
    //false for inactive
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "profile_picture_id")
    private ProfilePicture profilePicture;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Loan> loanList;

    @PrePersist
    public void prePersist(){
        if(status == null){
            status = true;
        }
    }
}
