package ru.lytvenkovmv.parkingservice.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.lytvenkovmv.parkingservice.dto.pageable.PageableDto;
import ru.lytvenkovmv.parkingservice.dto.parking.ParkRecordResponseDto;
import ru.lytvenkovmv.parkingservice.entity.ParkRecord;
import ru.lytvenkovmv.parkingservice.mapper.PageableMapper;
import ru.lytvenkovmv.parkingservice.mapper.ParkRecordMapper;
import ru.lytvenkovmv.parkingservice.repository.ParkRecordRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkRecordsServiceImplFindAllTest {
    @Mock
    private ParkRecordRepository repository;
    @Mock
    private ParkRecordMapper mapper;
    @Mock
    private PageableMapper pageableMapper;
    @InjectMocks
    private ParkRecordsServiceImpl service;

    @Test
    void when_findAll_then_return_mapped_list() {
        PageableDto pageableDto = new PageableDto();
        pageableDto.setPage(0);
        pageableDto.setSize(2);

        Pageable pageable = PageRequest.of(0, 2);

        ParkRecord record1 = new ParkRecord();
        record1.setId(UUID.fromString("11111111-1111-1111-1111-111111111111"));
        ParkRecord record2 = new ParkRecord();
        record2.setId(UUID.fromString("22222222-2222-2222-2222-222222222222"));
        List<ParkRecord> records = List.of(record1, record2);
        Page<ParkRecord> page = new PageImpl<>(records);

        ParkRecordResponseDto dto1 = new ParkRecordResponseDto();
        dto1.setId(record1.getId());
        ParkRecordResponseDto dto2 = new ParkRecordResponseDto();
        dto2.setId(record2.getId());

        when(pageableMapper.map(any(PageableDto.class))).thenReturn(pageable);
        when(repository.findAll(pageable)).thenReturn(page);
        when(mapper.parkRecordResponseDtoFrom(record1)).thenReturn(dto1);
        when(mapper.parkRecordResponseDtoFrom(record2)).thenReturn(dto2);

        List<ParkRecordResponseDto> result = service.findAll(pageableDto);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(record1.getId(), result.get(0).getId());
        assertEquals(record2.getId(), result.get(1).getId());
    }
}