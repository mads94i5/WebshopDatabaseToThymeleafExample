package com.example.webshopdat22v2.controller;
import com.example.webshopdat22v2.model.Product;
import com.example.webshopdat22v2.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/create")
    public String showCreateProduct() {
        return "Home/create";
    }

    @PostMapping("/create")
    public String createProduct(@RequestParam("name") String name, @RequestParam("price") int price) {
        Product newProduct = new Product();

        newProduct.setName(name);
        newProduct.setPrice(price);

        productRepository.create(newProduct);

        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "Home/update";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product product) {
        productRepository.update(product);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteProduct(@PathVariable("id") int id) {
        productRepository.deleteById(id);
        return "redirect:/";
    }
}
