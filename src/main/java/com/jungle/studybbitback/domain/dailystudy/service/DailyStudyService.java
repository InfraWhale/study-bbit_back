package com.jungle.studybbitback.domain.dailystudy.service;

import com.jungle.studybbitback.domain.dailystudy.dto.GetDailyStudyByPeriodResponseDto;
import com.jungle.studybbitback.domain.dailystudy.dto.GetDailyStudyResponseDto;
import com.jungle.studybbitback.domain.dailystudy.dto.WriteStudyRequestDto;
import com.jungle.studybbitback.domain.dailystudy.dto.WriteStudyResponseDto;
import com.jungle.studybbitback.domain.dailystudy.entity.DailyStudy;
import com.jungle.studybbitback.domain.dailystudy.repositody.DailyStudyRepository;
import com.jungle.studybbitback.domain.member.entity.Member;
import com.jungle.studybbitback.domain.member.repository.MemberRepository;
import com.jungle.studybbitback.jwt.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class DailyStudyService {

	private final MemberRepository memberRepository;
	private final DailyStudyRepository dailyStudyRepository;

	@Transactional
	public WriteStudyResponseDto saveDailyStudy(WriteStudyRequestDto request) {

		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long memberId = userDetails.getMemberId();

		// 1. Member 엔티티 조회
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

		LocalDateTime start = request.getStart();
		LocalDateTime end = request.getEnd();

		Map<LocalDate, Duration> studyRecord = new HashMap<>();

		// 2. 날짜별로 나누어 공부 시간을 계산
		LocalDate currentDate = start.toLocalDate();
		while (!currentDate.isAfter(end.toLocalDate())) {
			LocalDateTime dailyStart = currentDate.isEqual(start.toLocalDate()) ? start : currentDate.atStartOfDay();
			LocalDateTime dailyEnd = currentDate.isEqual(end.toLocalDate()) ? end : currentDate.atTime(23, 59, 59);

			Duration dailyDuration = Duration.between(dailyStart, dailyEnd);

			// 3. 해당 날짜의 기록을 데이터베이스에서 조회
			DailyStudy dailyStudy = dailyStudyRepository.findByMemberAndStudyDate(member, currentDate)
					.orElse(null);

			if (dailyStudy != null) {
				// 기존 기록에 공부 시간 추가
				dailyStudy.updateStudyTime(dailyStudy.getStudyTime().plus(dailyDuration));
			} else {
				// 새로운 기록 생성
				dailyStudy = new DailyStudy(currentDate, dailyDuration, member);
				dailyStudyRepository.save(dailyStudy);
			}

			studyRecord.put(currentDate, dailyStudy.getStudyTime());

			currentDate = currentDate.plusDays(1);
		}

		return new WriteStudyResponseDto(member, studyRecord);
	}

	public Page<GetDailyStudyResponseDto> getDailyStudyAll(Pageable pageable) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return dailyStudyRepository.findByMemberId(userDetails.getMemberId(), pageable).map(GetDailyStudyResponseDto::new);
	}

	public GetDailyStudyResponseDto getStudyByDate(LocalDate studyDate) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		DailyStudy dailyStudy = dailyStudyRepository.findByMemberIdAndStudyDate(userDetails.getMemberId(), studyDate)
						.orElse(null);
		if (dailyStudy != null) {
			return new GetDailyStudyResponseDto(dailyStudy);
		} else {
			return new GetDailyStudyResponseDto(studyDate);
		}
	}

	public List<GetDailyStudyResponseDto> getStudyByYear(Integer year) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<DailyStudy> dailyStudies = dailyStudyRepository.findAllByMemberIdAndStudyYearNative(userDetails.getMemberId(), year);

		return dailyStudies.stream().map(GetDailyStudyResponseDto::new)
				.collect(Collectors.toList());
	}

	public GetDailyStudyByPeriodResponseDto getStudyByPeriod(Long memberId, LocalDate startDate, LocalDate endDate) {
		//log.info("startDate : {}, endDate : {}", startDate, endDate);

		Duration studyByPeriod = Duration.ZERO;

		String studyByPeriodAsText = dailyStudyRepository.findStudyByPeriod(memberId, startDate, endDate);

		//log.info("avgIntervalAsText : {}", avgIntervalAsText);

		if (studyByPeriodAsText != null) {
			studyByPeriod = parsePostgresInterval(studyByPeriodAsText);
		}

		//log.info("studyByPeriod : {}", studyByPeriod);

		return new GetDailyStudyByPeriodResponseDto(studyByPeriod);
	}

	private Duration parsePostgresInterval(String interval) {
		String[] parts = interval.split(":");
		long hours = Long.parseLong(parts[0]);
		long minutes = Long.parseLong(parts[1]);
		long seconds = Long.parseLong(parts[2].split("\\.")[0]);

		return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
	}
}
