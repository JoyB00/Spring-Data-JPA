package joy.programm.Spring.Data.JPA;

import joy.programm.Spring.Data.JPA.entity.Category;
import joy.programm.Spring.Data.JPA.entity.Product;
import joy.programm.Spring.Data.JPA.repository.CategoryRepository;
import joy.programm.Spring.Data.JPA.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository   productRepository;

    @Test
    void createProducts() {
        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        {
            Product product = new Product();
            product.setName("Laptop");
            product.setPrice(1000);
            product.setCategory(category);
            productRepository.save(product);
        }

        {
            Product product = new Product();
            product.setName("Mouse");
            product.setPrice(2000);
            product.setCategory(category);
            productRepository.save(product);
        }
    }

    @Test
    void findByCategoryName() {
        List<Product> products = productRepository.findAllByCategory_Name("Gadget Murah");
        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Laptop", products.get(0).getName());
        Assertions.assertEquals("Mouse", products.get(1).getName());

    }
}
