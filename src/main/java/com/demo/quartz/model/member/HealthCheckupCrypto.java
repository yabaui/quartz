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
        name = "health_checkup_crypto",
        indexes = {
                @Index(name = "health_checkup_crypto_index_1", columnList = "mb_uid"),
                @Index(name = "health_checkup_crypto_index_2", columnList = "mb_uid,checkup_date"),
                @Index(name = "health_checkup_crypto_index_3", columnList = "mb_uid,checkup_date,checkup_type")
        }
)
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthCheckupCrypto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Long uid;

    @Column(name = "acid", length = 64)
    private String acid;

    @Column(name = "mb_uid", nullable = false)
    private Long mbUid;

    @Column(name = "checkup_date", nullable = false, length = 10)
    private String checkupDate;

    @Column(name = "checkup_institution", length = 128)
    private String checkupInstitution;

    @Column(name = "checkup_type", nullable = false, length = 64)
    private String checkupType;

    @Column(name = "optional", nullable = false, length = 512)
    private String optional;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @Builder.Default
    @Transient
    private String optionalDecrypt = "";

    @Builder(builderClassName = "updatedBuilder", builderMethodName = "updatedBuilder")
    public void updatedBuilder (Long mbUid, String acid, String checkupDate, String checkupInstitution,
                                String checkupType, String optional, Timestamp deletedAt, String optionalDecrypt) {
        this.acid = StringUtils.isEmpty(acid) ? this.acid : acid;
        this.mbUid = Objects.isNull(mbUid) ? this.mbUid : mbUid;
        this.checkupDate = StringUtils.isEmpty(checkupDate) ? this.checkupDate : checkupDate;
        this.checkupInstitution = StringUtils.isEmpty(checkupInstitution) ? this.checkupInstitution : checkupInstitution;
        this.checkupType = StringUtils.isEmpty(checkupType) ? this.checkupType : checkupType;
        this.optional = StringUtils.isEmpty(optional) ? this.optional : optional;
        this.deletedAt = Objects.isNull(deletedAt) ? this.deletedAt : deletedAt;
        this.optionalDecrypt = StringUtils.isEmpty(optionalDecrypt) ? this.optionalDecrypt : optionalDecrypt;
    }
}
