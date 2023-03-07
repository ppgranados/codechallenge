package com.squaretrade.challenge.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO Class for the Keyword entity.
 */
@Data
@Builder
public class KeywordDto {
    private int keywordId;

    private String name;
}
