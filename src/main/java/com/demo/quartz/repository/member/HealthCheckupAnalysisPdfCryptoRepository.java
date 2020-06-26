package com.demo.quartz.repository.member;

import com.demo.quartz.model.member.HealthCheckupAnalysisPdfCrypto;
import com.demo.quartz.model.member.key.HealthCheckupAnalysisPdfCryptoKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthCheckupAnalysisPdfCryptoRepository
        extends JpaRepository<HealthCheckupAnalysisPdfCrypto, HealthCheckupAnalysisPdfCryptoKey> {
}
