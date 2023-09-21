package com.andlopper.productapi.mapper;

import com.andlopper.productapi.controller.v1.response.ProductResponse;
import com.andlopper.productapi.entity.ProductEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductResponse fromEntityToResponse(ProductEntity from){
        var toResponse = new ProductResponse();
        toResponse.setId(from.getId());
        toResponse.setName(from.getName());
        toResponse.setPrice(from.getPrice());
        toResponse.setQuantity(from.getQuantity());
        return toResponse;
    }

    public static List<ProductResponse> fromEntityToResponse(List<ProductEntity> products) {

        return products.stream()
                .map(ProductMapper::fromEntityToResponse) // Mapear cada entidade para resposta
                .collect(Collectors.toList());
    }
}
