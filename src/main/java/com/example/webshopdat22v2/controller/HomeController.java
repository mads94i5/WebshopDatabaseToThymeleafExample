package com.example.webshopdat22v2.controller;
import com.example.webshopdat22v2.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    ProductRepository productRepository;
    public HomeController(ProductRepository p) {
        productRepository = p;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productRepository.getAll());
        return "Home/index";
    }
}
