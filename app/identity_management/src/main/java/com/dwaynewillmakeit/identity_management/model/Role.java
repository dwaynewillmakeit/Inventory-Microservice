package com.dwaynewillmakeit.identity_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role", schema = "public")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "role_id_seq")
    private Long id;

    private String name;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_on", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdOn;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "modified_on", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime modifiedOn;


//    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
//    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",
            schema = "public",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();


}
