package com.example.test.service.impl;



import com.example.test.data.dto.ProductDto;
import com.example.test.data.dto.ProductResponseDto;
import com.example.test.data.entity.Product;
import com.example.test.data.repository.ProductRepository;
import com.example.test.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ProductResponseDto getProduct(Long number) {

        log.info("[getProduct] input number : {}", number);

        Product product = productRepository.findById(number).get();

        log.info("[getProduct] product number : {}, name : {} " , product.getNumber() ,product.getName());

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {

        log.info("[saveProduct] productDTO : {}", productDto.toString());
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());


        Product savedProduct = productRepository.save(product);
        log.info("[saveProduct] savedProduct : {}" ,savedProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {

        Product foundProduct = productRepository.findById(number).get();
        foundProduct.setName(name);
        Product changedProduct = productRepository.save(foundProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setNumber(changedProduct.getNumber());
        productResponseDto.setName(changedProduct.getName());
        productResponseDto.setPrice(changedProduct.getPrice());
        productResponseDto.setStock(changedProduct.getStock());

        return productResponseDto;

    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productRepository.deleteById(number);

    }
}
