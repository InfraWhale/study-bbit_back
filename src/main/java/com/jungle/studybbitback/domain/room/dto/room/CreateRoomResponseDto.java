package com.jungle.studybbitback.domain.room.dto.room;

import com.jungle.studybbitback.domain.room.entity.Room;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateRoomResponseDto {
    private Long id;
    private String name;
    private String roomUrl;
    private String password;
    private String detail;
    private Integer participants;
    private Integer maxParticipants;
    private String profileImageUrl;
    private Long leaderId;
    private boolean isPrivate;

    private LocalDateTime createdAt;

    public CreateRoomResponseDto(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.roomUrl = room.getRoomUrl();
        this.password = room.getPassword();
        this.detail = room.getDetail();
        this.participants = room.getParticipants();
        this.maxParticipants = room.getMaxParticipants();
        this.profileImageUrl = room.getProfileImageUrl();
        this.createdAt = room.getCreatedAt();
        this.leaderId = room.getLeaderId();
        this.isPrivate = room.isPrivate();
    }
}
