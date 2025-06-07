package ru.lytvenkovmv.parkingservice.mapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.lytvenkovmv.parkingservice.dto.pageable.PageableDto;

public class PageableMapper {
    public static Pageable map(PageableDto pageableDto) {
        String property = !pageableDto.getSortBy().isEmpty() ? pageableDto.getSortBy() : "updated_at";
        Sort.Direction dir = pageableDto.getDirection().equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;

        return PageRequest.of(pageableDto.getPage(), pageableDto.getSize(), dir, property);
    }
}
