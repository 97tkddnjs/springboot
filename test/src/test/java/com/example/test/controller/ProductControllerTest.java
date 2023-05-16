package com.example.test.controller;


import com.example.test.data.dto.ProductDto;
import com.example.test.data.dto.ProductResponseDto;
import com.example.test.data.entity.Product;
import com.example.test.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductServiceImpl productService;

    @Test
    @DisplayName("Mock을 통한 test")
    void getProductTest() throws Exception {

        //given
        given(productService.getProduct(123L)).willReturn(
                new ProductResponseDto(123L, "Pen", 5000,2000)
        );

        String productId = "123";

        //when

        mockMvc.perform(
                get("/product?number=" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());


        //then
        verify(productService).getProduct(123L);
    }

    @Test
    @DisplayName("create Product")
    void createProductTest() throws Exception{
        //given
        given(productService.saveProduct(new ProductDto("Pen", 5000,2000))).willReturn(
                new ProductResponseDto(12315L, "Pen", 5000,2000)
        );


        ProductDto productDto = ProductDto.builder()
                .name("Pen")
                .price(5000)
                .stock(2000)
                .build();

        // json 만들기 위한 준비
        ObjectMapper mapper = new ObjectMapper();

        String content = mapper.writeValueAsString(productDto);
        System.out.println("jsonString = " + content);

//        Gson gson = new Gson();
//        String content = gson.toJson(productDto);
        mockMvc.perform(
                post("/product")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).saveProduct(new ProductDto("Pen",5000,2000));

    }

}
