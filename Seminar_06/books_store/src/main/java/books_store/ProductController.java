package books_store;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id){
        return productService.findById(id);
    }
    @PutMapping("/{id}")
    public Product updateById(@RequestBody Product product, @PathVariable Long id){
        product.setId(id);
        return productService.save(product);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
