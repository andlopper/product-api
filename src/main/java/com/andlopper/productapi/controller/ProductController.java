package com.andlopper.productapi.controller;

import com.andlopper.productapi.model.Product;
import com.andlopper.productapi.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    // Injeção de dependência do serviço que acessa os dados dos produtos
    private final ProductService productService;

    private final ObjectMapper objectMapper;
//
//    @Autowired
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    @Autowired
    public ProductController(ProductService productService, ObjectMapper objectMapper) {
        this.productService = productService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.findProducts();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product newProduct) {
        Product createdProduct = productService.addProduct(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long id){
        boolean isRemoved = productService.removeProduct(id);
        if (isRemoved) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        try {
            return productService.updateProduct(id, updatedProduct);
        } catch (ResponseStatusException e) {
            throw e; // Repassa a exceção para ser tratada pelo Spring e retornar o erro HTTP correto
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro durante a atualização do produto.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> partialUpdateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product product = productService.partialUpdateProduct(id, updatedProduct);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
