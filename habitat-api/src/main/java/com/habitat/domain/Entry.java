package com.habitat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "entries",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"habit_id", "date"})
        })
public class Entry {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Habit habit;

    @OneToOne(mappedBy = "entry", orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Note note;

    @OneToOne(mappedBy = "entry", orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Metric metric;
}