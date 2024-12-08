package joy.programm.Spring.Data.JPA.repository;

import joy.programm.Spring.Data.JPA.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

//    Where name = string name
    Optional<Category> findFirstByNameEquals(String name);

//    Where name like string name
    List<Category> findAllByNameLike(String name);
}
