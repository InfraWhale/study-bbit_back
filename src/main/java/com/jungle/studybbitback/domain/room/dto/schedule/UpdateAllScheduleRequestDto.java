package com.jungle.studybbitback.domain.room.dto.schedule;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@ToString
public class UpdateAllScheduleRequestDto {
    private String title;
    private String detail;
    private LocalDate startDate;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean repeatFlag;
    private String repeatPattern;
    private String daysOfWeek;
    private LocalDate repeatEndDate;

    @Builder
    public UpdateAllScheduleRequestDto(String title, String detail, LocalDate startDate, String day, LocalTime startTime,
                                    LocalTime endTime, boolean repeatFlag, String repeatPattern, String daysOfWeek,
                                    LocalDate repeatEndDate) {
        this.title = title;
        this.detail = detail;
        this.startDate = startDate;
        this.day = day;
        this.startTime = (startTime != null) ? startTime : LocalTime.of(0, 0);
        this.endTime = (endTime != null) ? endTime : LocalTime.of(23, 59);
        this.repeatFlag = repeatFlag;
        this.repeatPattern = repeatPattern;
        this.daysOfWeek = daysOfWeek;
        this.repeatEndDate = repeatEndDate;
    }
}
