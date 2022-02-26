package com.example.storeapp.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@Entity
@Table(name= "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends  AbstractEntity{

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="pw", nullable = false)
    private String pw;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private List<Product> products;


}
