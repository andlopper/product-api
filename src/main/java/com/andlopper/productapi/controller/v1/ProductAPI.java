package com.andlopper.productapi.controller.v1;

import com.andlopper.productapi.controller.v1.request.ProductRequest;
import com.andlopper.productapi.controller.v1.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

/**
 * Classe utilitária para centralizar as annotations do swagger
 */

public interface ProductAPI {
    @Operation(summary = "Cria um novo produto", description = "Retorna o produto recém criado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))})
    })
    ProductResponse saveProduct(@RequestBody ProductRequest productEntity);

    @Operation(summary = "Busca um produto pelo ID", description = "Retorna um produto por seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Não encontrado - O produto não foi encontrado", content = @Content)
    })
    ProductResponse getProductById(@PathVariable("id") @Parameter(name = "id", description = "ID do produto", example = "1") Long id);

    @Operation(summary = "Busca todos os produtos", description = "Retorna todos produtos encontrados no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))})
    })
    ResponseEntity<List<ProductResponse>> getAllProducts();

    @Operation(summary = "Atualiza um produto", description = "Retorna o produto recém atualizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Não encontrado - O produto não foi encontrado", content = @Content)
    })
    ProductResponse updateProduct(@PathVariable("id") @Parameter(name = "id", description = "ID do produto", example = "1") Long id,
                                    @Valid @org.springframework.web.bind.annotation.RequestBody ProductRequest request);

    @Operation(summary = "Remove um produto", description = "Remove o produto com ID especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem conteúdo", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado - O produto não foi encontrado", content = @Content)
    })
    ResponseEntity<Void> deleteProduct(@PathVariable("id") @Parameter(name = "id", description = "ID do produto", example = "1") Long id);

    @Operation(summary = "Atualiza parcialmente um produto", description = "Retorna o produto atualizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Não encontrado - O produto não foi encontrado", content = @Content)
    })
    ProductResponse partialUpdateProduct(@PathVariable("id") @Parameter(name = "id", description = "ID do produto", example = "1") Long id,
                                           @Valid @org.springframework.web.bind.annotation.RequestBody ProductRequest request);
}
