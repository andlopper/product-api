package com.andlopper.productapi.service;

import com.andlopper.productapi.controller.v1.request.ProductRequest;
import com.andlopper.productapi.controller.v1.response.ProductResponse;
import com.andlopper.productapi.entity.ProductEntity;
import com.andlopper.productapi.exception.ProductNotFoundException;
import com.andlopper.productapi.mapper.ProductMapper;
import com.andlopper.productapi.repository.ProductRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final MessageSource messageSource;


    public ProductService(ProductRepository productRepository, MessageSource messageSource) {
        this.productRepository = productRepository;
        this.messageSource = messageSource;
    }

    // Método para criar ou atualizar um product
    public ProductResponse saveProduct(ProductRequest request) {
        var actualProduct = new ProductEntity();

        actualProduct.setName(request.getName());
        actualProduct.setPrice(request.getPrice());
        actualProduct.setQuantity(request.getQuantity());

        productRepository.save(actualProduct);

        return ProductMapper.fromEntityToResponse(actualProduct);
    }

    // Método para buscar um product pelo ID
    public ProductResponse getProductById(Long id) {
        var actualProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(messageSource.getMessage("product.not.found", new Object[]{id}, null)));

        return ProductMapper.fromEntityToResponse(actualProduct);
    }

    // Método para listar todos os products
    public List<ProductResponse> getAllProducts() {
        var products = productRepository.findAll();

        return ProductMapper.fromEntityToResponse(products);
    }

    // Método para atualizar um product existente pelo ID
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        var actualProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(messageSource.getMessage("product.not.found", new Object[]{id}, null)));

        actualProduct.setName(request.getName());
        actualProduct.setPrice(request.getPrice());
        actualProduct.setQuantity(request.getQuantity());

        productRepository.save(actualProduct);

        return ProductMapper.fromEntityToResponse(actualProduct);
    }

    // Método para excluir um product pelo ID
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(messageSource.getMessage("product.not.found", new Object[]{id}, null)));

        productRepository.deleteById(id);
    }

    public ProductResponse partialUpdateProduct(Long id, ProductRequest request) {
        var actualProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(messageSource.getMessage("product.not.found", new Object[]{id}, null)));

        if (request.getName() != null){
            actualProduct.setName(request.getName());
        }
        if (request.getPrice() != null){
            actualProduct.setPrice(request.getPrice());
        }
        if (request.getQuantity() != null){
            actualProduct.setQuantity(request.getQuantity());
        }

        productRepository.save(actualProduct);

        return ProductMapper.fromEntityToResponse(actualProduct);
    }
}
