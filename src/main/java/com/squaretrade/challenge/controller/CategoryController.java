package com.squaretrade.challenge.controller;

import com.squaretrade.challenge.dto.KeywordDto;
import com.squaretrade.challenge.exception.CategoryNotFoundException;
import com.squaretrade.challenge.response.GetKeywordsResponse;
import com.squaretrade.challenge.response.GetLevelResponse;
import com.squaretrade.challenge.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller to interact with Categories.
 */
@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final transient CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryId}/level")
    public ResponseEntity<GetLevelResponse> getLevel(@PathVariable final int categoryId) {
        final int level = categoryService.getLevel(categoryId);

        return ResponseEntity.ok(GetLevelResponse.builder()
                .level(level)
                .categoryId(categoryId)
                .build());
    }

    @GetMapping("/{categoryId}/keywords")
    public ResponseEntity<GetKeywordsResponse> getKeywords(@PathVariable final int categoryId) {
        List<KeywordDto> keywordsFromCategory = categoryService.getKeywordsFromCategory(categoryId);

        return ResponseEntity.ok(
                GetKeywordsResponse.builder()
                        .id(categoryId)
                        .keywords(keywordsFromCategory)
                .build()
        );
    }
}
