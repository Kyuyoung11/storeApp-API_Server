package com.example.storeapp.entity;



import lombok.*;

import java.util.List;
import javax.persistence.*;



@Builder
@AllArgsConstructor
@Entity
@Table(name= "images")
@Getter
@Setter
@NoArgsConstructor
public class Product extends AbstractEntity{

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="url", nullable = false)
    private String url;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name="writer", nullable = false)
    private String writer;

    @Column(name="company", nullable = false)
    private String company;

    @Column(name="detail")
    private String detail;






}
