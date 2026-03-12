package com.miniks.shop.entity;

import com.miniks.shop.domain.HomeCategorySection;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "home_category")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HomeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String image;

    private String categoryId;

    @Enumerated(EnumType.STRING)
    private HomeCategorySection section;

}
