package org.example.scheduler.dto;

import java.util.Comparator;

public class SchedulerResponseDtoDateComparator implements Comparator<SchedulerResponseDto> {
    @Override
    public int compare(SchedulerResponseDto dto1, SchedulerResponseDto dto2) {
        return dto1.getModificationDate().compareTo(dto2.getModificationDate());
    }
}
