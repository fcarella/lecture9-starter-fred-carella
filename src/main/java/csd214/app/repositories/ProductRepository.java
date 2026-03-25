package csd214.app.repositories;

import csd214.app.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository tells Spring's IoC container to manage this bean
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // Spring Data JPA automatically provides basic methods like:
    // save(), findById(), findAll(), deleteById(), count()

    // --------------------------------------------------------
    // CUSTOM QUERY MAGIC:
    // Spring parses this method name and automatically generates:
    // SELECT * FROM products WHERE UPPER(name) LIKE UPPER('%name%')
    // --------------------------------------------------------
    List<ProductEntity> findByNameContainingIgnoreCase(String name);

//    // We can also search specifically by author since BookEntity inherits from ProductEntity
//    List<ProductEntity> findByAuthorContainingIgnoreCase(String author);
}