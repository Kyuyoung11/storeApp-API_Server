package com.example.storeapp.entity;


import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Table(name= "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name="password", nullable = false)
    private String password;


}
