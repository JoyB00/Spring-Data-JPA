package joy.programm.Spring.Data.JPA.service;

import joy.programm.Spring.Data.JPA.entity.Category;
import joy.programm.Spring.Data.JPA.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionOperations;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public void error(){
        throw new RuntimeException("Ups Rollback please");
    }

    public void transactionManual(){
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setTimeout(10);
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus transaction = transactionManager.getTransaction(transactionDefinition);
        try {
            for (int i = 0; i < 5; i++) {
                Category category = new Category();
                category.setName("Category " + i);
                categoryRepository.save(category);
            }
            error();
            transactionManager.commit(transaction);
        } catch (Throwable throwable) {
            transactionManager.rollback(transaction);
            throw throwable;
        }
    }

    public void createCategories(){
        transactionOperations.executeWithoutResult(transactionStatus -> {
            for (int i = 0; i < 5; i++) {
                Category category = new Category();
                category.setName("Category " + i);
                categoryRepository.save(category);
            }
            error();
        });
    }

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
