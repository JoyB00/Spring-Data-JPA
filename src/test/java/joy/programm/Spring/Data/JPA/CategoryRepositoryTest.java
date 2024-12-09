package joy.programm.Spring.Data.JPA;

import joy.programm.Spring.Data.JPA.entity.Category;
import joy.programm.Spring.Data.JPA.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

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

    @Test
    void queryMethod(){
        Category category = categoryRepository.findFirstByNameEquals("Gadget Murah").orElse(null);
        Assertions.assertNotNull(category);
        Assertions.assertEquals("Gadget Murah", category.getName());

        List<Category> categories = categoryRepository.findAllByNameLike("Gadget%");
        Assertions.assertEquals(1, categories.size());
        Assertions.assertEquals("Gadget Murah", categories.get(0).getName());

    }

    @Test
    void audit() {
        Category category = new Category();
        category.setName("Sample Audit");
        categoryRepository.save(category);

        Assertions.assertNotNull(category.getId());
        Assertions.assertNotNull(category.getCreatedDate());
        Assertions.assertNotNull(category.getLastModifiedDate());
    }

    @Test
    void example1() {
        Category category = new Category();
        category.setName("Gadget Murah");
        category.setId(1L);

        Example<Category> example = Example.of(category);
        List<Category> categories = categoryRepository.findAll(example);
        Assertions.assertEquals(1, categories.size());
        Assertions.assertEquals("Gadget Murah", categories.get(0).getName());
    }

    @Test
    void example2() {
        Category category = new Category();
        category.setName("gadget Murah");

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase();

        Example<Category> example = Example.of(category, exampleMatcher);
        List<Category> categories = categoryRepository.findAll(example);
        Assertions.assertEquals(1, categories.size());
        Assertions.assertEquals("Gadget Murah", categories.get(0).getName());
    }
}
