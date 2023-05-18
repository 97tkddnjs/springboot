package com.springboot.advanced_jpa.data.repository;

import com.querydsl.core.types.Predicate;
import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
public class QProductRepositoryTest {
    
    @Autowired
    QProductRepository qProductRepository;

    @Test
    public void queryRepositoryTest1() {
        Predicate predicate = QProduct.product.name.containsIgnoreCase("pen")
                .and(QProduct.product.price.between(1000, 2500));

        Optional<Product> foundProduct = qProductRepository.findOne(predicate);

        if (foundProduct.isPresent()) {
            Product product = foundProduct.get();
            System.out.println("product.getName() = " + product.getName());
            System.out.println("product.getNumber() = " + product.getNumber());
            System.out.println("product.getName() = " + product.getName());
            System.out.println("product.getStock() = " + product.getStock());

        }


    }
    
}
