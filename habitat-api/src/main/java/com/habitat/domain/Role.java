package com.habitat.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE roles SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;

    @Column(nullable = false)
    private boolean deleted = false;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<User> users = new ArrayList<>();

    public Role(RoleName name) {
        this.name = name;
    }

    public enum RoleName {
        PAID_USER,
        FREE_USER,
        ADMIN
    }
}