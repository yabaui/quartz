package com.demo.quartz.model.member;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
        name = "family_health_checkup_crypto",
        indexes = {
                @Index(name = "family_health_checkup_crypto_index_1", columnList = "mb_uid"),
                @Index(name = "family_health_checkup_crypto_index_2", columnList = "mb_uid,risk_score")
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FamilyHealthCheckupCrypto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false, updatable = false)
    private Long uid;

    @Column(name = "mb_uid", nullable = false, updatable = false)
    private Long mbUid;

    @Column(name = "question", nullable = false, length = 20)
    private String question;

    @Column(name = "question_key", nullable = false, length = 40)
    private String questionKey;

    @Column(name = "item", nullable = false, length = 60)
    private String item;

    @Column(name = "item_key", nullable = false, length = 40)
    private String itemKey;

    @Column(name = "risk_score", nullable = false, length = 128)
    private String riskScore;

    @Column(name = "target_diseases_key", nullable = false, length = 40)
    private String targetDiseasesKey;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @Transient
    private int riskScoreDecrypt = 0;

    @Builder(builderClassName = "insertBuilder", builderMethodName = "insertBuilder")
    public FamilyHealthCheckupCrypto(Long mbUid, String question,
                                     String questionKey, String item,
                                     String itemKey, String riskScore,
                                     String targetDiseasesKey) {
        this.mbUid = mbUid;
        this.question = question;
        this.questionKey = questionKey;
        this.item = item;
        this.itemKey = itemKey;
        this.riskScore = riskScore;
        this.targetDiseasesKey = targetDiseasesKey;
    }

    @Builder(builderClassName = "updatedBuilder", builderMethodName = "updatedBuilder")
    public void updatedBuilder(Long mbUid, String question,
                               String questionKey, String item,
                               String itemKey, String riskScore,
                               String targetDiseasesKey,
                               Integer riskScoreDecrypt) {
        if (!Objects.isNull(mbUid)) {
            this.mbUid = mbUid;
        }

        if (StringUtils.isNoneEmpty(question)) {
            this.question = question;
        }

        if (StringUtils.isNoneEmpty(questionKey)) {
            this.questionKey = questionKey;
        }

        if (StringUtils.isNoneEmpty(item)) {
            this.item = item;
        }

        if (StringUtils.isNoneEmpty(itemKey)) {
            this.itemKey = itemKey;
        }

        if (StringUtils.isNoneEmpty(riskScore)) {
            this.riskScore = riskScore;
        }

        if (StringUtils.isNoneEmpty(targetDiseasesKey)) {
            this.targetDiseasesKey = targetDiseasesKey;
        }

        if (!Objects.isNull(riskScoreDecrypt)) {
            this.riskScoreDecrypt = riskScoreDecrypt;
        }
    }
}
