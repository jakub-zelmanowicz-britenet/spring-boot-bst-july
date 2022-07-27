package pl.britenet.springbootbstjuly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.object.Product;
import pl.britenet.campus.service.ProductService;
import pl.britenet.springbootbstjuly.service.AuthenticationService;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:63342")
@RestController
@RequestMapping("/api/v1/product")
public class ProductsController {

    private final ProductService productService;
    private final AuthenticationService authenticationService;

    @Autowired
    public ProductsController(ProductService productService, AuthenticationService authenticationService) {
        this.productService = productService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable int id) {
        return this.productService.getProduct(id);
    }

    @GetMapping
    public List<Product> getProducts() {
        return this.productService.getProducts();
    }

    @PostMapping
    public void createProduct(@RequestHeader("Authorization") String user_token) {
        System.out.println("Token: " + user_token);
        int user_id = authenticationService.getUserId(user_token);
        System.out.println("Retrieved User ID: " + user_id);
         // this.productService.createProduct(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        // this.productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        // this.productService.deleteProduct(id);
    }
}
