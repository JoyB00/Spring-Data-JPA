package joy.programm.Spring.Data.JPA;

import joy.programm.Spring.Data.JPA.entity.Category;
import joy.programm.Spring.Data.JPA.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void insert() {
        Category category = new Category();
        category.setName("Electronics");
        categoryRepository.save(category);

        Assertions.assertNotNull(category.getId());
    }


    @Test
    void update() {
        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        category.setName("Gadget Murah");
        categoryRepository.save(category);

        category= categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);
        Assertions.assertEquals("Gadget Murah", category.getName());
    }
}
