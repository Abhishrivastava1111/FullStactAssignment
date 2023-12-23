package com.mapping.relationships.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "compounders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Compounder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compounderId;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "salary")
    private double salary;
}
