package joy.programm.Spring.Data.JPA.repository;

import joy.programm.Spring.Data.JPA.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
