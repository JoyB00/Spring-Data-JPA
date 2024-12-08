package joy.programm.Spring.Data.JPA;

import joy.programm.Spring.Data.JPA.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void success() {
        assertThrows(RuntimeException.class, () -> {
            categoryService.create();
        });
    }

    @Test
    void failed() {
        assertThrows(RuntimeException.class, () -> {
            categoryService.test();
        });
    }

    @Test
    void programmatic() {
        assertThrows(RuntimeException.class, () -> {
            categoryService.createCategories();
        });
    }

    @Test
    void transactionManual() {
        assertThrows(RuntimeException.class, () -> {
            categoryService.transactionManual();
        });
    }
}
