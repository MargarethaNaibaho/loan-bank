package com.enigmabank.enigmabanking.entity;

import com.enigmabank.enigmabanking.constant.ListTables;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = ListTables.USER)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String id;

    @Column(unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @Column
    private String password;

    @OneToOne(mappedBy = "user")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
