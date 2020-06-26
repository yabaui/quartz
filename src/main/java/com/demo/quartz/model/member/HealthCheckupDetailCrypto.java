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
        name = "health_checkup_detail_crypto",
        indexes = {
                @Index(name = "health_checkup_detail_crypto_index_1", columnList = "mb_uid"),
                @Index(name = "health_checkup_detail_crypto_index_2", columnList = "mb_uid,checkup_date"),
                @Index(name = "health_checkup_detail_crypto_index_3", columnList = "mb_uid,checkup_date,target_diseases_key"),
                @Index(name = "health_checkup_detail_crypto_index_4", columnList = "mb_uid,checkup_date,item_key")
        }
)
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthCheckupDetailCrypto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Long uid;

    @Column(name = "mb_uid", nullable = false)
    private Long mbUid;

    @Column(name = "checkup_type", length = 64)
    private String checkupType;

    @Column(name = "checkup_date", nullable = false, length = 10)
    private String checkupDate;

    @Column(name = "target_diseases", nullable = false, length = 256)
    private String targetDiseases;

    @Column(name = "inspection_item", nullable = false, length = 128)
    private String inspectionItem;

    @Column(name = "reference_unit", length = 16)
    private String referenceUnit;

    @Column(name = "checkup_value", nullable = false, length = 512)
    private String checkupValue;

    @Column(name = "target_diseases_key", nullable = false, length = 256)
    private String targetDiseasesKey;

    @Column(name = "item_key", nullable = false, length = 40)
    private String itemKey;

    @Column(name = "reference_normal", nullable = false, length = 64)
    private String referenceNormal;

    @Column(name = "reference_caution", nullable = false, length = 64)
    private String referenceCaution;

    @Column(name = "reference_danger", nullable = false, length = 64)
    private String referenceDanger;

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
    private String targetDiseasesDecrypt = "";

    @Transient
    @Builder.Default
    private String targetDiseasesKeyDecrypt = "";

    @Transient
    @Builder.Default
    private String checkupValueDecrypt = "";

    @Builder(builderClassName = "updatedBuilder", builderMethodName = "updatedBuilder")
    public void updatedBuilder (Long mbUid, String checkupType, String checkupDate, String targetDiseases,
                                      String inspectionItem, String referenceUnit, String checkupValue,
                                      String targetDiseasesKey, String itemKey, String referenceNormal,
                                      String referenceCaution, String referenceDanger, Timestamp deletedAt,
                                String targetDiseasesDecrypt, String targetDiseasesKeyDecrypt, String checkupValueDecrypt) {
        this.mbUid = Objects.isNull(mbUid) ? this.mbUid : mbUid;
        this.checkupType = StringUtils.isEmpty(checkupType) ? this.checkupType : checkupType;
        this.checkupDate = StringUtils.isEmpty(checkupDate) ? this.checkupDate : checkupDate;
        this.targetDiseases = StringUtils.isEmpty(targetDiseases) ? this.targetDiseases : targetDiseases;
        this.inspectionItem = StringUtils.isEmpty(inspectionItem) ? this.inspectionItem : inspectionItem;
        this.referenceUnit = StringUtils.isEmpty(referenceUnit) ? this.referenceUnit : referenceUnit;
        this.checkupValue = StringUtils.isEmpty(checkupValue) ? this.checkupValue : checkupValue;
        this.targetDiseasesKey = StringUtils.isEmpty(targetDiseasesKey) ? this.targetDiseasesKey : targetDiseasesKey;
        this.itemKey = StringUtils.isEmpty(itemKey) ? this.itemKey : itemKey;
        this.referenceNormal = StringUtils.isEmpty(referenceNormal) ? this.referenceNormal : referenceNormal;
        this.referenceCaution = StringUtils.isEmpty(referenceCaution) ? this.referenceCaution : referenceCaution;
        this.referenceDanger = StringUtils.isEmpty(referenceDanger) ? this.referenceDanger : referenceDanger;
        this.deletedAt = Objects.isNull(deletedAt) ? this.deletedAt : deletedAt;

        this.targetDiseasesDecrypt = StringUtils.isEmpty(targetDiseasesDecrypt) ? this.targetDiseasesDecrypt : targetDiseasesDecrypt;
        this.targetDiseasesKeyDecrypt = StringUtils.isEmpty(targetDiseasesKeyDecrypt) ? this.targetDiseasesKeyDecrypt : targetDiseasesKeyDecrypt;
        this.checkupValueDecrypt = StringUtils.isEmpty(checkupValueDecrypt) ? this.checkupValueDecrypt : checkupValueDecrypt;
    }
}
