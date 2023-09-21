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

    /**
     * Cria um novo produto.
     * @param request Objeto contendo os dados do produto a ser criado.
     * @return O produto criado.
     */
    public ProductResponse saveProduct(ProductRequest request) {
        var actualProduct = new ProductEntity();

        actualProduct.setName(request.getName());
        actualProduct.setPrice(request.getPrice());
        actualProduct.setQuantity(request.getQuantity());

        productRepository.save(actualProduct);

        return ProductMapper.fromEntityToResponse(actualProduct);
    }

    /**
     * Busca um produto pelo ID.
     * @param id ID do produto a ser buscado.
     * @return O produto encontrado ou null se nenhum produto com o ID especificado for encontrado.
     */
    public ProductResponse getProductById(Long id) {
        var actualProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(messageSource.getMessage("product.not.found", new Object[]{id}, null)));

        return ProductMapper.fromEntityToResponse(actualProduct);
    }

    /**
     * Busca todos os produtos.
     * @return Todos os produtos encontrados.
     */
    public List<ProductResponse> getAllProducts() {
        var products = productRepository.findAll();

        return ProductMapper.fromEntityToResponse(products);
    }

    /**
     * Atualiza completamente um produto.
     * @param id ID do produto a ser atualizado.
     * @param request Objeto contendo todos os dados do produto a ser alterado.
     * @return O produto atualizado.
     */
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        var actualProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(messageSource.getMessage("product.not.found", new Object[]{id}, null)));

        actualProduct.setName(request.getName());
        actualProduct.setPrice(request.getPrice());
        actualProduct.setQuantity(request.getQuantity());

        productRepository.save(actualProduct);

        return ProductMapper.fromEntityToResponse(actualProduct);
    }

    /**
     * Deleta um produto.
     * @param id ID do produto a ser deletado.
     */
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(messageSource.getMessage("product.not.found", new Object[]{id}, null)));

        productRepository.deleteById(id);
    }

    /**
     * Atualiza parcialmente um produto.
     * @param id ID do produto a ser parcialmente atualizado.
     * @param request Objeto contendo os dados do produto a ser parcialmente atualizado.
     * @return O produto atualizado.
     */
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
