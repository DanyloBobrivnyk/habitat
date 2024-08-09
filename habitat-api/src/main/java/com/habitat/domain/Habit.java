package com.habitat.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "habits")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String name;

    public String metrics;

    @OneToMany(mappedBy = "habit", orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    public List<Entry> entries;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    public Habit(String name, @Nullable String metrics) {
        this.name = name;
        this.metrics = metrics;
    }
}