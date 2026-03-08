package com.miniks.shop.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "deal")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer discount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_category_id")
    private HomeCategory category;

}
