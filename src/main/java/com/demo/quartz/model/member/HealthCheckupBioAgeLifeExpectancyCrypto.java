package com.demo.quartz.model.member;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;
import com.demo.quartz.model.member.key.HealthCheckupBioAgeLifeExpectancyCryptoKey;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
        name = "health_checkup_bio_age_life_expectancy_crypto",
        indexes = {
                @Index(name = "health_checkup_bio_age_life_expectancy_crypto_index_1", columnList = "mb_uid"),
                @Index(name = "health_checkup_bio_age_life_expectancy_crypto_index_2", columnList = "mb_uid,checkup_date")
        }
)
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(HealthCheckupBioAgeLifeExpectancyCryptoKey.class)
public class HealthCheckupBioAgeLifeExpectancyCrypto implements Serializable {

    @Id
    @Column(name = "mb_uid", nullable = false)
    private Long mbUid;

    @Id
    @Column(name = "checkup_date", nullable = false, length = 512)
    private String checkupDate;

    @Column(name = "aging_index", length = 512)  // 노화 지수
    private String agingIndex;

    @Column(name = "aging_rank", length = 512)   // 노화 등수
    private String agingRank;

    @Column(name = "tle", length = 512)  // 기대 수명
    private String tle;

    @Column(name = "tle_avg", length = 512)  // 평균 기대수명
    private String tleAvg;

    @Column(name = "caa", length = 512)  // 심장나이
    private String caa;

    @Column(name = "hea", length = 512)  // 간나이
    private String hea;

    @Column(name = "paa", length = 512)  // 췌장나이
    private String paa;

    @Column(name = "rea", length = 512)  // 신장나이
    private String rea;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @Transient
    @Builder.Default
    private String agingIndexDecrypt = "";

    @Transient
    @Builder.Default
    private String agingRankDecrypt = "";

    @Transient
    @Builder.Default
    private String totalGuideDecrypt = "";

    @Transient
    @Builder.Default
    private String tleDecrypt = "";

    @Transient
    @Builder.Default
    private String tleAvgDecrypt = "";

    @Transient
    @Builder.Default
    private String caaDecrypt = "";

    @Transient
    @Builder.Default
    private String heaDecrypt = "";

    @Transient
    @Builder.Default
    private String paaDecrypt = "";

    @Transient
    @Builder.Default
    private String reaDecrypt = "";

    @Builder(builderClassName = "updatedBuilder", builderMethodName = "updatedBuilder")
    public void updatedBuilder (String agingIndex, String agingRank, String tle, String tleAvg, String caa, String hea,
                                String paa, String rea, Timestamp deletedAt, String agingIndexDecrypt,
                                String agingRankDecrypt, String totalGuideDecrypt,
                                String tleDecrypt, String tleAvgDecrypt, String caaDecrypt, String heaDecrypt,
                                String paaDecrypt, String reaDecrypt) {
        this.agingIndex = StringUtils.isEmpty(agingIndex) ? this.agingIndex : agingIndex;
        this.agingRank = StringUtils.isEmpty(agingRank) ? this.agingRank : agingRank;
        this.tle = StringUtils.isEmpty(tle) ? this.tle : tle;
        this.tleAvg = StringUtils.isEmpty(tleAvg) ? this.tleAvg : tleAvg;
        this.caa = StringUtils.isEmpty(caa) ? this.caa : caa;
        this.hea = StringUtils.isEmpty(hea) ? this.hea : hea;
        this.paa = StringUtils.isEmpty(paa) ? this.paa : paa;
        this.rea = StringUtils.isEmpty(rea) ? this.rea : rea;
        this.deletedAt = Objects.isNull(deletedAt) ? this.deletedAt : deletedAt;

        this.agingIndexDecrypt = StringUtils.isEmpty(agingIndexDecrypt) ? this.agingIndexDecrypt : agingIndexDecrypt;
        this.agingRankDecrypt = StringUtils.isEmpty(agingRankDecrypt) ? this.agingRankDecrypt : agingRankDecrypt;
        this.totalGuideDecrypt = StringUtils.isEmpty(totalGuideDecrypt) ? this.totalGuideDecrypt : totalGuideDecrypt;
        this.tleDecrypt = StringUtils.isEmpty(tleDecrypt) ? this.tleDecrypt : tleDecrypt;
        this.tleAvgDecrypt = StringUtils.isEmpty(tleAvgDecrypt) ? this.tleAvgDecrypt : tleAvgDecrypt;
        this.caaDecrypt = StringUtils.isEmpty(caaDecrypt) ? this.caaDecrypt : caaDecrypt;
        this.heaDecrypt = StringUtils.isEmpty(heaDecrypt) ? this.heaDecrypt : heaDecrypt;
        this.paaDecrypt = StringUtils.isEmpty(paaDecrypt) ? this.paaDecrypt : paaDecrypt;
        this.reaDecrypt = StringUtils.isEmpty(reaDecrypt) ? this.reaDecrypt : reaDecrypt;
    }
}
