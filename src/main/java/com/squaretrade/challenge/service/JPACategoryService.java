package com.squaretrade.challenge.service;

import com.squaretrade.challenge.dto.KeywordDto;
import com.squaretrade.challenge.entity.CategoryEntity;
import com.squaretrade.challenge.entity.KeywordEntity;
import com.squaretrade.challenge.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Implementation of the {@link CategoryService} using a Database as data source.
 */
@Service
public class JPACategoryService implements CategoryService {

    private final transient CategoryRepository repository;

    public JPACategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<KeywordDto> getKeywordsFromCategory(final int categoryId) {
        List<KeywordDto> result = null;
        Iterable<CategoryEntity> allCategories = this.repository.findAll();

        // TODO: this is a bruteforce approach. Optimize later
        final CategoryEntity thisCategory =
                StreamSupport.stream(allCategories.spliterator(), false)
                        .filter(categoryEntity -> categoryEntity.getCategoryId() == categoryId)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Category Id des not exists in the DB"));

        /*List<KeywordEntity> keywords = StreamSupport.stream(
                allCategories.spliterator(), false
        )
                .filter(categoryEntity -> categoryEntity.getCategoryId() == categoryId)
                .flatMap(categoryEntity -> categoryEntity.getKeywords().stream())
                .collect(Collectors.toList());*/

        List<KeywordEntity> keywords = thisCategory.getKeywords();

        // if there are no keywords for this category get it from the parent
        if (keywords.isEmpty()) {
            for (CategoryEntity possibleParent: allCategories) {
                boolean isParent = possibleParent.getSubCategories().stream().anyMatch(
                        categoryEntity -> categoryEntity.getCategoryId() == categoryId
                );
                if (isParent) {
                    keywords = possibleParent.getKeywords();
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public int getLevel(final String categoryName) {
        return 1;
    }
}
