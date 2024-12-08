package joy.programm.Spring.Data.JPA;

import joy.programm.Spring.Data.JPA.entity.Category;
import joy.programm.Spring.Data.JPA.entity.Product;
import joy.programm.Spring.Data.JPA.repository.CategoryRepository;
import joy.programm.Spring.Data.JPA.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.support.TransactionOperations;

import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository   productRepository;
    @Autowired
    private TransactionOperations transactionOperations;

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

    @Test
    void sort() {
//        Sort sort = Sort.by(Sort.Order.desc("id"), Sort.Order.asc("name"));
        Sort sort = Sort.by(Sort.Order.desc("id"));
        List<Product> products = productRepository.findAllByCategory_Name("Gadget Murah", sort);
        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Mouse", products.get(0).getName());
        Assertions.assertEquals("Laptop", products.get(1).getName());
    }

    @Test
    void pageable(){
//        page 0
        Pageable pageable = PageRequest.of(0,1,Sort.by(Sort.Order.desc("id")));
        Page<Product> products = productRepository.findAllByCategory_Name("Gadget Murah", pageable);
        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(0, products.getNumber());
        Assertions.assertEquals(2, products.getTotalElements());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals("Mouse", products.getContent().get(0).getName());

//        page 1
        pageable = PageRequest.of(1,1,Sort.by(Sort.Order.desc("id")));
        products = productRepository.findAllByCategory_Name("Gadget Murah", pageable);
        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(1, products.getNumber());
        Assertions.assertEquals(2, products.getTotalElements());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals("Laptop", products.getContent().get(0).getName());
    }

    @Test
    void countCategoryByName() {
        Long count = productRepository.count();
        Assertions.assertEquals(2, count);

        count = productRepository.countByCategory_Name("Gadget Murah");
        Assertions.assertEquals(2, count);

        count = productRepository.countByCategory_Name("Gak ada");
        Assertions.assertEquals(0, count);
    }

    @Test
    void existProductByName() {
        boolean exist = productRepository.existsByName("Laptop");
        Assertions.assertTrue(exist);

        exist = productRepository.existsByName("Gak ada");
        Assertions.assertFalse(exist);
    }

    @Test
    void deleteByProductName() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Category category = categoryRepository.findById(1L).orElse(null);
            Assertions.assertNotNull(category);

            Product product = new Product();
            product.setName("Keyboard");
            product.setPrice(1000);
            product.setCategory(category);
            productRepository.save(product);


            int deleted = productRepository.deleteByName("Keyboard");
            Assertions.assertEquals(1, deleted);

//           After delete
            deleted = productRepository.deleteByName("Keyboard");
            Assertions.assertEquals(0, deleted);
        });
    }
}
