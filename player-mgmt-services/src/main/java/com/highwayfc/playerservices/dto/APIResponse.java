package com.highwayfc.playerservices.dto;
import java.util.List;

public record APIResponse<T>(
        List<T> content,
        int pageNo,
        int pageSize,
        long totalElement,
        int totalPages,
        boolean last
) {
}
