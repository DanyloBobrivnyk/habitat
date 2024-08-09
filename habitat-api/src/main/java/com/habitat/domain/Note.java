package com.habitat.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Entry entry;

    public Note(String content, Entry entry) {
        this.content = content;
        this.entry = entry;
    }
}
