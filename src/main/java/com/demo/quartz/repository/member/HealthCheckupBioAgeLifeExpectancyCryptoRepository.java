package com.demo.quartz.repository.member;

import com.demo.quartz.model.member.HealthCheckupBioAgeLifeExpectancyCrypto;
import com.demo.quartz.model.member.key.HealthCheckupBioAgeLifeExpectancyCryptoKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthCheckupBioAgeLifeExpectancyCryptoRepository
        extends JpaRepository<HealthCheckupBioAgeLifeExpectancyCrypto, HealthCheckupBioAgeLifeExpectancyCryptoKey> {
}
