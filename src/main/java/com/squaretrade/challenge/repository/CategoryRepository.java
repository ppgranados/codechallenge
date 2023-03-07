package com.squaretrade.challenge.repository;

import com.squaretrade.challenge.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for Categoty entities,
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

    CategoryEntity getByCategoryId(Integer categoryId);

}
