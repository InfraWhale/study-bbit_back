package com.jungle.studybbitback.domain.room.entity.schedule;

import com.jungle.studybbitback.common.entity.ModifiedTimeEntity;
import com.jungle.studybbitback.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@NoArgsConstructor
public class ScheduleMember extends ModifiedTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_member_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    //@Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ParticipateStatusEnum participateStatus;

    private String notedDetail;

    public ScheduleMember(Schedule schedule, Member member, ParticipateStatusEnum status, String detail) {
        this.schedule = schedule;
        this.member = member;
        this.participateStatus = status;
        this.notedDetail = detail;
    }

    public ScheduleMember(Schedule schedule, Member member, ParticipateStatusEnum status) {
        this.schedule = schedule;
        this.member = member;
        this.participateStatus = status;
    }

    public void updateStatus(ParticipateStatusEnum status) {
        this.participateStatus = status;
        if(status != ParticipateStatusEnum.NOTED) {
            this.notedDetail = null;
        }
    }
}
