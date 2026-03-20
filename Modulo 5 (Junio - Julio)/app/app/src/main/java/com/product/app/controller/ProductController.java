package com.product.app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.product.app.model.Product;
import com.product.app.repository.ProductRepository;

import jakarta.websocket.server.PathParam;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// esta clase es el controlador que se encarga de manejar las peticiones HTTP relacionadas con los productos, es el punto de entrada para las operaciones CRUD sobre los productos
// siempre retorna en formato JSON, es el encargado de recibir las peticiones del cliente, procesarlas y devolver una respuesta adecuada
@RestController
@RequestMapping("/api/products")
public class ProductController {

    // inyeccion de dependencias, se inyecta el repositorio de productos para poder
    // acceder a la base de datos y realizar operaciones sobre los productos
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/api/products")
    public List<Product> listarProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> obtenerProductPorId(@PathVariable("id") Long id) {
        return productRepository.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Product crearProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

}
