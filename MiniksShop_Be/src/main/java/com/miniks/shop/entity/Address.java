package com.miniks.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String locality;

    private String address;

    private String city;

    private String state;

    @Column(name = "pin_code")
    private String pinCode;

    private String mobile;

}