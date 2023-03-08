package com.squaretrade.challenge.service;

import com.squaretrade.challenge.dto.CategoryDto;
import com.squaretrade.challenge.dto.KeywordDto;

import java.util.List;

public interface CategoryService {

    List<KeywordDto> getKeywordsFromCategory(int id);

    int getLevel(int id);
}
