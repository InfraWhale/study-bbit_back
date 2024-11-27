package com.jungle.studybbitback.common.file.dto;

import com.jungle.studybbitback.common.file.entity.UserFile;
import lombok.Getter;

@Getter
public class GetRoomFileResponseDto {
	private Long id;

	private String uploadName;
	private String fileType;
	private Long fileSize;
	private String fileUploadPath;
	private String createdBy;

	public GetRoomFileResponseDto(UserFile file, String nickname) {
		this.id = file.getId();
		this.uploadName = file.getUploadName();
		this.fileType = file.getFileType();
		this.fileSize = file.getFileSize();
		this.fileUploadPath = file.getFileUploadPath();
		this.createdBy = nickname; // 닉네임 추가
	}
}