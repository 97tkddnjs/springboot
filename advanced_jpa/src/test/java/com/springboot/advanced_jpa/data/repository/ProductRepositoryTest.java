package com.springboot.advanced_jpa.data.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void sortingAndPagingTest() {
        Product product1 = new Product();
        product1.setName("pen");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());



        Product product2 = new Product();
        product2.setName("pen");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setCreatedAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());


        Product product3 = new Product();
        product3.setName("pen");
        product3.setPrice(3000);
        product3.setStock(200);
        product3.setCreatedAt(LocalDateTime.now());
        product3.setUpdatedAt(LocalDateTime.now());

        Product saveProduct1 = productRepository.save(product1);
        Product saveProduct2 = productRepository.save(product2);
        Product saveProduct3 = productRepository.save(product3);


        productRepository.findByName("pen", Sort.by(Sort.Order.asc("price")));
        productRepository.findByName("pen", Sort.by(Sort.Order.asc("price"), Sort.Order.desc("stock")));

        productRepository.findByName("pen", PageRequest.of(0,2));
    }

    @Test
    void queryDslTest() {
        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
                .from(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product :  productList) {
            System.out.println("--------------------");
            System.out.println();
            System.out.println("product.getName() = " + product.getName());
            System.out.println("product.getNumber() = " + product.getNumber());
            System.out.println("product.getPrice() = " + product.getPrice());
            System.out.println("product.getStock() = " + product.getStock());
            System.out.println();
            System.out.println("--------------------");

        }
    }

    @Test
    void queryDslTest2() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product :  productList) {
            System.out.println("--------------------");
            System.out.println();
            System.out.println("product.getName() = " + product.getName());
            System.out.println("product.getNumber() = " + product.getNumber());
            System.out.println("product.getPrice() = " + product.getPrice());
            System.out.println("product.getStock() = " + product.getStock());
            System.out.println();
            System.out.println("--------------------");

        }
    }

    @Test
    void queryDslTest3() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        System.out.println("test3 ~~~~~~~~~~");
        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();
        System.out.println("productList = " + productList);
        for (String product :  productList) {
            System.out.println("--------------------");
            System.out.println();
            System.out.println("product.getName() = " + product);
            System.out.println();
            System.out.println("--------------------");

        }


        List<Tuple> tupleList = jpaQueryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Tuple product :  tupleList) {
            System.out.println("--------------------");
            System.out.println();
            System.out.println("product.getName() = " + product.get(qProduct.name));
            System.out.println();
            System.out.println("--------------------");

        }

    }

}
