package com.andlopper.productapi.controller.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class ProductRequest {
    @Schema(name = "name", example = "Mouse RGB")
    @NotBlank()
    private String name;

    @Schema(name = "quantity", example = "70")
    @NotBlank()
    private Integer quantity;

    @Schema(name = "price", example = "50.00")
    @NotBlank()
    private Double price;

    public ProductRequest() {
    }

    public ProductRequest(String name, Integer quantity, Double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
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