package com.jungle.studybbitback.domain.room.dto.room;

import com.jungle.studybbitback.domain.room.entity.Room;
import lombok.Getter;

@Getter
public class GetRoomDetailResponseDto {
    private Long id;
    private String name;
    private String detail;
    private Integer participants;
    private Integer maxParticipants;
    private String profileImageUrl;
    private boolean isPrivate;
    private String leaderNickname;

    public GetRoomDetailResponseDto(Room room, String leaderNickname) {
        this.id = room.getId();
        this.name = room.getName();
        this.detail = room.getDetail();
        this.participants = room.getParticipants();
        this.maxParticipants = room.getMaxParticipants();
        this.profileImageUrl = room.getProfileImageUrl();
        this.isPrivate = room.isPrivate();
        this.leaderNickname = leaderNickname;
    }

}
