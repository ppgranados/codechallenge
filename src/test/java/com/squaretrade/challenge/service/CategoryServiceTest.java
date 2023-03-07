package com.squaretrade.challenge.service;

import com.squaretrade.challenge.dto.KeywordDto;
import com.squaretrade.challenge.entity.CategoryEntity;
import com.squaretrade.challenge.entity.KeywordEntity;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

public class CategoryServiceTest {

    private JPACategoryService categoryService;

    private CategoryEntity rootCategory;

    @Before
    public void setUp() throws Exception {
        categoryService = new JPACategoryService(repository);
        initMockData();
    }

    @Test
    public void getKeywordsFromCategory() {
        // setup test data
        final List<CategoryEntity> subCategories = new ArrayList<>();
        subCategories.add(
                new CategoryEntity.Builder()
                        .id(2)
                        .keywords(
                                Arrays.asList(
                                        new KeywordEntity.Builder().name("cat2_KW1").build(),
                                        new KeywordEntity.Builder().name("cat2_KW2").build()
                                )
                        )
                .build()
        );
        this.rootCategory.setSubCategories(subCategories);

        List<KeywordDto> actual = this.categoryService.getKeywordsFromCategory(2);

        // assertions
        assertEquals(actual.size(), 2);
        assertEquals(actual.get(0).getName(), "cat2_KW1");
        assertEquals(actual.get(1).getName(), "cat2_KW1");
    }

    @Test
    public void shouldGetKeywordsFromParentCategory() {
        // setup test data
        final List<CategoryEntity> subCategories = new ArrayList<>();
        // Adding a subcategory that does not have any keywords
        subCategories.add(
                new CategoryEntity.Builder().id(2).build()
        );
        this.rootCategory.setSubCategories(subCategories);

        List<KeywordDto> actual = this.categoryService.getKeywordsFromCategory(2);

        // assertions
        assertEquals(actual.size(), 3);
        assertEquals(actual.get(0).getName(), "Root KW1");
        assertEquals(actual.get(1).getName(), "Root KW2");
        assertEquals(actual.get(2).getName(), "Root KW3");
    }

    @Test
    public void getLevelRoot() {
        // setup test data
        final List<CategoryEntity> subCategories = new ArrayList<>();

        int actual = this.categoryService.getLevel("Root");

        // assertions
        assertEquals(0, actual);
    }

    @Test
    public void getLevelFromSubcategory() {
        // setup test data
        final List<CategoryEntity> subCategories = new ArrayList<>();

        subCategories.add(
                new CategoryEntity.Builder()
                        .id(2)
                        .subCategories(
                                Collections.singletonList(
                                        new CategoryEntity.Builder()
                                                .name("Level 2")
                                                .id(2)
                                        .build()
                                )
                        )
                        .build()
        );
        this.rootCategory.setSubCategories(subCategories);

        int actual = this.categoryService.getLevel("Level2");

        // assertions
        assertEquals(2, actual);
    }

    private void initMockData() {
        this.rootCategory = new CategoryEntity.Builder()
                .id(1)
                .name("Furniture")
                .keywords(Arrays.asList(
                        new KeywordEntity.Builder().name("Root KW1").build(),
                        new KeywordEntity.Builder().name("Root KW2").build(),
                        new KeywordEntity.Builder().name("Root KW3").build()
                ))
                .build();
    }
}