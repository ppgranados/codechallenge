package com.squaretrade.challenge.response;

import com.squaretrade.challenge.dto.KeywordDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Response for the Get Keywords API call.
 */
@Data
@Builder
public class GetKeywordsResponse {
    private int id;

    // TODO: add code to fetch category name
    //private String categoryName;

    private List<KeywordDto> keywords;
}
