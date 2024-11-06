package com.bkaracan.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(
      name = "COUNTRY_ID_GENERATOR",
      sequenceName = "COUNTRY_ID_GEN",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COUNTRY_ID_GENERATOR")
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(length = 50, nullable = false)
  private String name;
}
