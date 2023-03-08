package com.squaretrade.challenge.service;

import com.squaretrade.challenge.dto.KeywordDto;
import com.squaretrade.challenge.entity.CategoryEntity;
import com.squaretrade.challenge.entity.KeywordEntity;
import com.squaretrade.challenge.repository.CategoryRepository;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CategoryServiceTest {

    private JPACategoryService categoryService;

    private List<CategoryEntity> mockDB;

    private CategoryRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = Mockito.mock(CategoryRepository.class);
        categoryService = new JPACategoryService(repository);
        initMockData();
    }

    @Test
    public void getKeywordsFromCategory() {
        Mockito.when(this.repository.getByCategoryId(1)).thenReturn(this.mockDB.get(0));
        List<KeywordDto> actual = this.categoryService.getKeywordsFromCategory(1);

        // assertions
        assertEquals(actual.size(), 3);
        assertEquals(actual.get(0).getName(), "Root KW1");
        assertEquals(actual.get(1).getName(), "Root KW2");
        assertEquals(actual.get(2).getName(), "Root KW3");
    }

    @Test
    public void shouldGetKeywordsFromParentCategory() {
        Mockito.when(this.repository.getByCategoryId(4)).thenReturn(this.mockDB.get(3));
        List<KeywordDto> actual = this.categoryService.getKeywordsFromCategory(4);

        // assertions
        assertEquals(actual.size(), 3);
        assertEquals(actual.get(0).getName(), "Root2 KW1");
        assertEquals(actual.get(1).getName(), "Root2 KW2");
        assertEquals(actual.get(2).getName(), "Root2 KW3");
    }

    @Test
    public void getLevelRoot() {
        // setup test data
        Mockito.when(this.repository.getByCategoryId(1)).thenReturn(this.mockDB.get(0));

        int actual = this.categoryService.getLevel(1);

        // assertions
        assertEquals(0, actual);
    }

    @Test
    public void getLevelFromSubcategory() {
        // setup test data
        Mockito.when(this.repository.getByCategoryId(4)).thenReturn(this.mockDB.get(3));

        int actual = this.categoryService.getLevel(4);

        // Level 0 is the root
        assertEquals(1, actual);
    }

    private void initMockData() {
        CategoryEntity root1 = new CategoryEntity.Builder()
                .id(1)
                .name("Root1")
                .keywords(Arrays.asList(
                        new KeywordEntity.Builder().id(1).name("Root KW1").build(),
                        new KeywordEntity.Builder().id(2).name("Root KW2").build(),
                        new KeywordEntity.Builder().id(3).name("Root KW3").build()
                ))
                .build();

        final List<CategoryEntity> subCategories = new ArrayList<>();
        subCategories.add(
                new CategoryEntity.Builder()
                        .id(2)
                        .parent(root1)
                        .name("Root 1 Child")
                        .keywords(
                                Arrays.asList(
                                        new KeywordEntity.Builder().id(4).name("Root 1 Child KW1").build(),
                                        new KeywordEntity.Builder().id(5).name("Root 1 Child KW2").build()
                                )
                        )
                        .build()
        );
        root1.setSubCategories(subCategories);

        CategoryEntity root2 = new CategoryEntity.Builder()
                .id(3)
                .name("Root 2")
                .keywords(Arrays.asList(
                        new KeywordEntity.Builder().id(6).name("Root2 KW1").build(),
                        new KeywordEntity.Builder().id(7).name("Root2 KW2").build(),
                        new KeywordEntity.Builder().id(8).name("Root2 KW3").build()
                ))
                .build();

        root2.setSubCategories(
                Collections.singletonList(
                        // child with no keywords
                        new CategoryEntity.Builder()
                                .id(4)
                                .name("Root 2 Child")
                                .parent(root2)
                                .build()
                )
        );

        this.mockDB = new ArrayList<>();
        this.mockDB.add(root1);
        this.mockDB.add(root2);
        this.mockDB.addAll(root1.getSubCategories());
        this.mockDB.addAll(root2.getSubCategories());
    }
}