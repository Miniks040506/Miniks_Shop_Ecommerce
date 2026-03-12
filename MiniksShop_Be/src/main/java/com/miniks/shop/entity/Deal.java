package com.miniks.shop.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "deal")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    private Integer discount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_category_id")
    private HomeCategory category;

}
