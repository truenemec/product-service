package com.demo.productservice.domain;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "product")
@Builder(toBuilder = true)
@AllArgsConstructor
//@Setter
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;
}
