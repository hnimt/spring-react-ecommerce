package com.minh.Backend.repository;

import com.minh.Backend.entity.Category;
import com.minh.Backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("Select p from Product p where p.name like %:name%")
    List<Product> findBySearchName(@Param("name") String searchName, Pageable pageable);

    @Query(value = "SELECT * FROM products WHERE category_id = ?1 ORDER BY NAME ASC", nativeQuery = true)
    List<Product> findByCategoryOrderByNameAsc(Integer catId, Pageable pageable);

    @Query(value = "SELECT * FROM products WHERE category_id = ?1 ORDER BY NAME DESC", nativeQuery = true)
    List<Product> findByCategoryOrderByNameDesc(Integer categoryId, Pageable pageable);

    @Query(value = "SELECT * FROM products WHERE category_id = :catId AND price BETWEEN :minPrice AND :maxPrice", nativeQuery = true)
    List<Product> findByCatByPrice(@Param("catId") Integer catId,
                                   @Param("minPrice") Double minPrice,
                                   @Param("maxPrice") Double maxPrice,
                                   Pageable pageable);

    @Query(value = "SELECT * FROM products WHERE category_id = :catId ORDER BY price ASC", nativeQuery = true)
    List<Product> findByCatOrderByPriceAsc(@Param("catId") Integer catId, Pageable pageable);

    @Query(value = "SELECT * FROM products WHERE category_id = :catId ORDER BY price DESC", nativeQuery = true)
    List<Product> findByCatOrderByPriceDesc(@Param("catId") Integer catId, Pageable pageable);

    List<Product> findByCategory(Category category, Pageable pageable);

    Page<Product> findAll(Pageable pageable);
}
