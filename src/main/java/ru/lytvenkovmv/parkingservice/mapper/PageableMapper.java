package ru.lytvenkovmv.parkingservice.mapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.lytvenkovmv.parkingservice.dto.pageable.PageableDto;

import java.util.Objects;

@Component
public class PageableMapper {
    public Pageable map(PageableDto pageableDto) {
        String direction = pageableDto.getDirection();
        Sort.Direction sortDir = Objects.nonNull(direction) && direction.equalsIgnoreCase("DESC")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        String sortBy = pageableDto.getSortBy();
        String property = Objects.nonNull(sortBy) && !sortBy.isEmpty() ? sortBy : "id";

        return PageRequest.of(pageableDto.getPage(), pageableDto.getSize(), sortDir, property);
    }
}
