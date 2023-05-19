package com.springboot.relationship.data.repository;



import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name, Sort sort);

    Page<Product> findByName(String name, Pageable pageable);

    @Query("SELECT p FROM Product AS p WHERE p.name=?1") // 직접 JPQL을 입력 받는 방식
    List<Product> findByName(String name);


}
