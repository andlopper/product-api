package com.andlopper.productapi.service;

import com.andlopper.productapi.model.Product;
import com.andlopper.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Método para criar ou atualizar um product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Método para buscar um product pelo ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Método para listar todos os products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Método para atualizar um product existente pelo ID
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            updatedProduct.setId(id);
            return productRepository.save(updatedProduct);
        } else {
            return null; // ou lançar uma exceção, se preferir
        }
    }

    // Método para excluir um product pelo ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    //Adicionar PATCH
}
