package com.example.demo.dto;

import java.util.List;

public interface PagedDTO {

    record PagedResponse<T>(
            List<T> content,
            int pageNumber,
            int pageSize,
            long totalElements,
            int totalPages,
            boolean lastPage
    ){}
}
