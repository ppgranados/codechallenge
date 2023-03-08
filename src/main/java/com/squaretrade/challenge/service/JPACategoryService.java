package com.squaretrade.challenge.service;

import com.squaretrade.challenge.dto.KeywordDto;
import com.squaretrade.challenge.entity.CategoryEntity;
import com.squaretrade.challenge.entity.KeywordEntity;
import com.squaretrade.challenge.exception.CategoryNotFoundException;
import com.squaretrade.challenge.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
        final CategoryEntity thisCategory = Optional.ofNullable(this.repository.getByCategoryId(categoryId)).orElseThrow(
                () -> new CategoryNotFoundException("Category not found in the DB")
        );

        CategoryEntity parent = thisCategory.getParent();

        List<KeywordEntity> keywords = Optional.ofNullable(thisCategory.getKeywords()).orElseGet(ArrayList::new);

        // if there are no keywords for this category get it from the parent
        while (keywords.isEmpty() && parent != null) {
            keywords = parent.getKeywords();
            parent = parent.getParent();
        }

        result = keywords.stream().map(
                entity -> KeywordDto.builder()
                        .keywordId(entity.getKeywordId())
                        .name(entity.getName())
                        .build()
        ).collect(Collectors.toList());
        return result;
    }

    @Override
    public int getLevel(final int categoryId) {
        int level = 0; // root level
        final CategoryEntity thisCategory = Optional.ofNullable(this.repository.getByCategoryId(categoryId)).orElseThrow(
                () -> new CategoryNotFoundException("Category not found in the DB")
        );

        CategoryEntity parent = thisCategory.getParent();

        // Navigate from bottom to top parent.
        while (parent != null) {
            level++;
            parent = parent.getParent();
        }

        return level;
    }
}
