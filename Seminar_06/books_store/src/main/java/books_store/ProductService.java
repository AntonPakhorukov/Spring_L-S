package books_store;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public Product save(Product product){
        return productRepository.save(product);
    }
    public Product updateById(Product product, Long id){
        Product newProduct = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        newProduct.setProductName(product.getProductName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        return productRepository.save(newProduct);
    }
    public void deleteById(Long id){
        productRepository.findById(id);
        productRepository.deleteById(id);
    }
}
