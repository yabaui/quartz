package com.demo.quartz.model.member;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import com.demo.quartz.model.member.key.HealthCheckupBioAgeAnalysisGuideCryptoKey;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
        name = "health_checkup_bio_age_analysis_guide_crypto",
        indexes = {
                @Index(name = "health_checkup_bio_age_analysis_guide_crypto_index_1", columnList = "mb_uid"),
                @Index(name = "health_checkup_bio_age_analysis_guide_crypto_index_2", columnList = "mb_uid,checkup_date")
        }
)
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(HealthCheckupBioAgeAnalysisGuideCryptoKey.class)
public class HealthCheckupBioAgeAnalysisGuideCrypto implements Serializable {

    @Id
    @Column(name = "mb_uid", nullable = false)
    private Long mbUid;

    @Id
    @Column(name = "checkup_date", nullable = false, length = 10)
    private String checkupDate;

    @Column(name = "age", length = 8)  // 나이
    private String age;

    @Column(name = "bio_age", length = 512)   // 생체 나이
    private String bioAge;

    @Column(name = "total_guide", columnDefinition = "text")  // 종합 가이드
    private String totalGuide;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @Transient
    private String bioAgeDecrypt = "";

    @Transient
    private String totalGuideDecrypt = "";
}
