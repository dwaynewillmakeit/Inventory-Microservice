package com.dwaynewillmakeit.identity_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permission")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "permission_id_seq")
    private Long id;

    private String name;


//    @ManyToMany(mappedBy = "permissions",fetch = FetchType.EAGER)
//    private Set<Role> roles = new HashSet<>();

}
