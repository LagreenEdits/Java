package com.product.app.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.product.app.model.Product;

// Es la interfaz que se encarga de la comunicacion con la base de datos, es el puente entre la aplicacion y la base de datos
// se crean metodos por debajo del capó para realizar operaciones CRUD (Create, Read, Update, Delete) sobre la entidad Product
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
