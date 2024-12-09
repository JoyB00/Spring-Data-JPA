package joy.programm.Spring.Data.JPA.repository;

import jakarta.persistence.LockModeType;
import joy.programm.Spring.Data.JPA.entity.Category;
import joy.programm.Spring.Data.JPA.entity.Product;
import joy.programm.Spring.Data.JPA.model.SimpleProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    <T> List<T> findAllByNameLike(String name, Class<T> tClass);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findFirstByIdEquals(Long id);

    Slice<Product> findAllByCategory(Category category, Pageable pageable);

    Stream<Product> streamAllByCategory(Category category);

    @Modifying
    @Query(value = "delete from Product p where p.name = :name")
    int deleteProductUsingName(@Param("name") String name);

    @Modifying
    @Query(value =" update Product p set p.price = 0 where p.id = :id")
    int updateProductPriceToZero(@Param("id")Long id);


//    kalo query annotation bisa menggunakan sort dan pageable
    @Query(
            value = "select p from Product p where p.name like :name or p.category.name like :name",
            countQuery = "select count(p) from Product p where p.name like :name or p.category.name like :name"
    )
    Page<Product> searchProduct(@Param("name") String name, Pageable pageable);

//    menggunakan Named Query (tidak bisa menambahkan sort, harus ditambahkan di named query nya menggunakan order by)
    List<Product> searchProductUsingName(@Param("name") String name, Pageable pageable);

    @Transactional
    int deleteByName(String name);

    boolean existsByName(String name);
    Long countByCategory_Name(String name);
    List<Product> findAllByCategory_Name(String name);
    List<Product> findAllByCategory_Name(String name, Sort sort);
    Page<Product> findAllByCategory_Name(String name, Pageable pageable);


}
