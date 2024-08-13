package com.example.crudexample.service;

import com.example.crudexample.entity.Product;
import com.example.crudexample.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testFindAll(){
        Product product = new Product();
        product.setName("Product 1");
        when(productRepository.findAll()).thenReturn(List.of(product,product));

        List<Product> products = productService.findAll();


        assertNotNull(products);
        assertEquals(2,products.size(),"expected only two");
    }

    @Test
    void testFindById() {
        //Arrange
        Product product = new Product();
        product.setName("product 1");
        when(productRepository.existsById(anyLong())).thenReturn(true);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        //Act
        Optional<Product> result = productService.findById(1L);


        //Assert
        assertNotNull(product);
        assertEquals("product 1",result.get().getName());
        verify(productRepository,times(1)).findById(anyLong());

    }
}