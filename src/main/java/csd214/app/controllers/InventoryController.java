package csd214.app.controllers;

import csd214.app.entities.ProductEntity;
import csd214.app.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final ProductRepository productRepository;

    // Constructor Injection
    public InventoryController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Handle GET http://localhost:8080/inventory
    @GetMapping
    public String getFullInventory(Model model) {
        // 1. Fetch data from DB
        List<ProductEntity> products = productRepository.findAll();

        // 2. Put data into the Model suitcase
        model.addAttribute("products", products);

        // 3. Return view name "inventory.html"
        return "inventory";
    }

    // Handle GET http://localhost:8080/inventory/search?name=Gallic
    @GetMapping("/search")
    public String searchProducts(@RequestParam String name, Model model) {
        List<ProductEntity> products = productRepository.findByNameContainingIgnoreCase(name);
        model.addAttribute("products", products);
        return "inventory";
    }
}

