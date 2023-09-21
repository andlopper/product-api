package com.andlopper.productapi.controller.v1.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductResponse {
    @Schema(name = "id", example = "1")
    private Long id;

    @Schema(name = "name", example = "Mouse RGB")
    private String name;

    @Schema(name = "quantity", example = "70")
    private Integer quantity;

    @Schema(name = "price", example = "50.00")
    private Double price;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String name, Integer quantity, Double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
