package com.demo.quartz.model.member;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;
import com.demo.quartz.model.member.key.HealthCheckupAnalysisPdfCryptoKey;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
        name = "health_checkup_analysis_pdf_crypto",
        indexes = {
                @Index(name = "health_checkup_analysis_pdf_crypto_index_1", columnList = "mb_uid,checkup_date,analysis_type")
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(HealthCheckupAnalysisPdfCryptoKey.class)
public class HealthCheckupAnalysisPdfCrypto implements Serializable {

    @Id
    @Column(name = "mb_uid", nullable = false)
    private Long mbUid;

    @Id
    @Column(name = "checkup_date", nullable = false, length = 10)
    private String checkupDate;

    @Column(name = "analysis_type", length = 1, columnDefinition = "char(1)")
    private String analysisType = "b";

    @Column(name = "pdf_path", length = 512) // PDF 파일 경로
    private String pdfPath;

    @Column(name = "pdf_status", length = 10)   // C: 생성완료, B: 생성전, F: 오류발생
    private String pdfStatus = "b";

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @Transient
    private String pdfPathDecrypt = "";

    @Builder
    public HealthCheckupAnalysisPdfCrypto(Long mbUid, String checkupDate, String analysisType, String pdfPath,
                                          String pdfStatus, Timestamp deletedAt) throws Exception {
        if (Objects.isNull(mbUid) || StringUtils.isEmpty(checkupDate)) {
            throw new Exception();
        }

        if (StringUtils.isEmpty(analysisType)) {
            analysisType = "b";
        }

        if (StringUtils.isEmpty(pdfStatus)) {
            pdfStatus = "b";
        }

        this.mbUid = mbUid;
        this.checkupDate = checkupDate;
        this.analysisType = analysisType;
        this.pdfPath = pdfPath;
        this.pdfStatus = pdfStatus;
        this.deletedAt = deletedAt;
    }

    public void updatePdfPath(String pdfPath) throws Exception {
        if (StringUtils.isEmpty(pdfPath)) {
            throw new Exception();
        }
        this.pdfPath = pdfPath;
    }
}
