package com.jungle.studybbitback.domain.room.dto.schedule;

import com.jungle.studybbitback.domain.room.entity.schedule.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class UpdateScheduleResponseDto {
    private Long scheduleId;
    private String title;
    private String detail;
    private LocalDate startDate; // 시작 날짜
    private LocalTime startTime;  // 시작 시간
    private LocalTime endTime;    // 종료 시간
    private boolean repeatFlag;
    private String repeatPattern;
    private String daysOfWeek;
    private LocalDate repeatEndDate;
    private Long scheduleCycleId; //단일일정은 null로 받음 & 반복 일정에서 하루만 수정하면 역시 null로 옴

    // Schedule 엔터티를 기반으로 DTO를 생성하는 생성자
    public UpdateScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getId();
        this.title = schedule.getTitle();
        this.detail = schedule.getDetail();
        this.startDate = schedule.getStartDate();
        this.startTime = schedule.getStartDateTime().toLocalTime();
        this.endTime = schedule.getEndDateTime().toLocalTime();
        this.repeatFlag = schedule.isRepeatFlag();
        this.repeatPattern = schedule.getRepeatPattern();
        this.daysOfWeek = schedule.getDaysOfWeek();
        this.repeatEndDate = schedule.getRepeatEndDate();
        this.scheduleCycleId = schedule.getScheduleCycleId();
    }
}
