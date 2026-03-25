package csd214.app.repositories;

import csd214.app.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    // Now this works perfectly!
    // Spring knows BookEntity has an "author" field.
    List<BookEntity> findByAuthorContainingIgnoreCase(String author);

}

