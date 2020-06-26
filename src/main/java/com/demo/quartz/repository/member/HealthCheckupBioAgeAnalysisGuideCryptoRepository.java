package com.demo.quartz.repository.member;

import com.demo.quartz.model.member.HealthCheckupBioAgeAnalysisGuideCrypto;
import com.demo.quartz.model.member.key.HealthCheckupBioAgeAnalysisGuideCryptoKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthCheckupBioAgeAnalysisGuideCryptoRepository
        extends JpaRepository<HealthCheckupBioAgeAnalysisGuideCrypto, HealthCheckupBioAgeAnalysisGuideCryptoKey> {
}
