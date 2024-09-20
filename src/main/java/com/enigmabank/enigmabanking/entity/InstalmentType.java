package com.enigmabank.enigmabanking.entity;

import com.enigmabank.enigmabanking.constant.EInstalmentType;
import com.enigmabank.enigmabanking.constant.ListTables;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = ListTables.INSTALMENT_TYPE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstalmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "instalment_type")
    private EInstalmentType instalmentType;
}
