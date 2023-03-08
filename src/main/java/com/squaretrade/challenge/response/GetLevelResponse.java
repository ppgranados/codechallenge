package com.squaretrade.challenge.response;

import lombok.Builder;
import lombok.Data;

/**
 * Response object to getLevel API call.
 */
@Data
@Builder
public class GetLevelResponse {
    private int level;
    private int categoryId;
}
