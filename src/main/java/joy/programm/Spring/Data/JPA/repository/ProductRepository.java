package joy.programm.Spring.Data.JPA.repository;

import joy.programm.Spring.Data.JPA.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory_Name(String name);
}
