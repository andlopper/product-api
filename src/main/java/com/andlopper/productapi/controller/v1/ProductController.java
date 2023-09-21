package com.andlopper.productapi.controller.v1;

import com.andlopper.productapi.controller.v1.request.ProductRequest;
import com.andlopper.productapi.controller.v1.response.ProductResponse;
import com.andlopper.productapi.entity.ProductEntity;
import com.andlopper.productapi.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "product-api")
@RequestMapping("/products")
public class ProductController implements ProductAPI{

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @PostMapping
    public ProductResponse saveProduct(@RequestBody ProductRequest productEntity) {
        return productService.saveProduct(productEntity);
    }

    @Override
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productEntities = productService.getAllProducts();
        return new ResponseEntity<>(productEntities, HttpStatus.OK);
    }

    @Override
    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long id,
                                         @Valid @RequestBody ProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PatchMapping("/{id}")
    public ProductResponse partialUpdateProduct(@PathVariable("id") Long id,
                                                @Valid @RequestBody ProductRequest request) {
        return productService.partialUpdateProduct(id, request);
    }
}
