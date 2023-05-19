package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductDetailRepositoryTest {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveAndReadTEst1() {
        Product product = new Product();
        product.setName("spring boot JPA");
        product.setPrice(5000);
        product.setStock(500);

        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setDescription("the book with springboot and jpa");

        productDetailRepository.save(productDetail);

        System.out.println("SAVED Product = " + productDetailRepository.findById(
                productDetail.getId()).get().getProduct());

        System.out.println("SAVED ProductDetail = " + productDetailRepository.findById(
                productDetail.getId()).get());
    }
}
