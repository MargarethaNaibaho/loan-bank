package com.enigmabank.enigmabanking.entity;

import com.enigmabank.enigmabanking.constant.ListTables;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = ListTables.PROFILE_PICTURE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfilePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String name;

    @Column(name = "content_type")
    private String contentType;

    @Column
    private Long size;

    @Column
    private String path;
}
