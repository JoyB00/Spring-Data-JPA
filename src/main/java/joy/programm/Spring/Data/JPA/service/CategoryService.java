package joy.programm.Spring.Data.JPA.service;

import joy.programm.Spring.Data.JPA.entity.Category;
import joy.programm.Spring.Data.JPA.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void create(){
        for (int i = 0; i < 5; i++) {
            Category category = new Category();
            category.setName("Category " + i);
            categoryRepository.save(category);
        }
        throw new RuntimeException("Ups Rollback please");
    }

//    contoh bahwa propagation bisa diubah-ubah
//    @Transactional (propagation = Propagation.MANDATORY)
//    public void create(){
//        for (int i = 0; i < 5; i++) {
//            Category category = new Category();
//            category.setName("Category " + i);
//            categoryRepository.save(category);
//        }
//        throw new RuntimeException("Ups Rollback please");
//    }

    public void test(){
        create();
    }
}
