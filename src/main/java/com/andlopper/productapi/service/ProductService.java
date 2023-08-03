package com.andlopper.productapi.service;

import com.andlopper.productapi.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        // Inicializar produtos para teste
        products.add(new Product(1L, "Geladeira", 10, 1000.0));
        products.add(new Product(2L, "Fogão", 15, 300.0));
    }

    public Product findProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Product> findProducts(){
        return products;
    }

    public Product addProduct(Product newProduct){
        products.add(newProduct);
        return newProduct;
    }

    public boolean removeProduct(Long id){
        try {
            return products.removeIf(product -> product.getId().equals(id)); //Deleta usuário
        }
        catch (RuntimeException e){
            throw new RuntimeException();
        }
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = findProductById(id);
        if (existingProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O produto com ID " + id + " não foi encontrado.");
        }

        // Verifica se todos os atributos, exceto o ID, são fornecidos no payload JSON
        if (updatedProduct.getName() == null || updatedProduct.getQuantity() == null || updatedProduct.getPrice() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos os atributos, exceto o ID, são obrigatórios para atualização.");
        }

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setPrice(updatedProduct.getPrice());
        // Defina outros campos que deseja atualizar completamente

        return existingProduct;
    }

    public Product partialUpdateProduct(Long id, Product updatedProduct) {
        Product existingProduct = findProductById(id);
        if (existingProduct == null) {
            return null;
        }

        if (updatedProduct.getName() != null) {
            existingProduct.setName(updatedProduct.getName());
        }
        if (updatedProduct.getQuantity() != null) {
            existingProduct.setQuantity(updatedProduct.getQuantity());
        }
        if (updatedProduct.getPrice() != null) {
            existingProduct.setPrice(updatedProduct.getPrice());
        }
        // Defina outros campos que deseja atualizar completamente, se houver

        // Caso todos os atributos enviados no payload sejam nulos, retorne um erro apropriado
        if (updatedProduct.getName() == null && updatedProduct.getQuantity() == null && updatedProduct.getPrice() == null) {
            throw new IllegalArgumentException("Nenhum atributo válido para atualização foi fornecido no payload.");
        }

        return existingProduct;
    }
}
