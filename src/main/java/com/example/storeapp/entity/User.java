package com.example.storeapp.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name="cart",
            joinColumns = {@JoinColumn(name="userID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="bookID", referencedColumnName = "id")})
    private List<Product> products = new ArrayList<>();


}
