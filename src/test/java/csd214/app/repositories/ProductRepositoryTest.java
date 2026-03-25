package csd214.app.repositories;

import csd214.app.entities.BookEntity;
import csd214.app.entities.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        // Wipes the database clean (removes any CommandLineRunner data)
        productRepository.deleteAll();
    }

    @Test
    void testSaveAndSearchByName() {
        // 1. CREATE Entities
        BookEntity book1 = new BookEntity();
        book1.setTitle("The Hobbit");
        book1.setName("The Hobbit");
        book1.setPrice(15.99);

        BookEntity book2 = new BookEntity();
        book2.setTitle("The History of Rome");
        book2.setName("The History of Rome");
        book2.setPrice(25.00);

        // 2. SAVE to DB
        productRepository.saveAll(Arrays.asList(book1, book2));

        // 3. VERIFY (Assert)
        assertNotNull(book1.getId());

        // Test our custom Query Method used by the Web Controller
        List<ProductEntity> foundItems = productRepository.findByNameContainingIgnoreCase("history");

        // We expect exactly 1 book containing "history"
        assertEquals(1, foundItems.size());
        assertEquals("The History of Rome", foundItems.get(0).getName());
    }
}



