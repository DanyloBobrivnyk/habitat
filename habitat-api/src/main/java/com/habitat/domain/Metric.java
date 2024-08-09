package com.habitat.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@Table(name = "metrics")
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long value;

    @OneToOne
    @JoinColumn(name = "entry_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Entry entry;

    public Metric(Long value, Entry entry) {
        this.value = value;
        this.entry = entry;
    }
}