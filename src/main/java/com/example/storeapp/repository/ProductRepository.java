package com.example.storeapp.repository;

import com.example.storeapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.name like %?1% or p.writer like %?1% or p.company like %?1%")
    List<Product> searchProducts(String words);

}
