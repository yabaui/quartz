package com.demo.quartz;

import java.util.ArrayList;
import java.util.List;

import com.demo.quartz.model.member.FamilyHealthCheckupCrypto;
import com.demo.quartz.model.member.HealthCheckupAnalysisPdfCrypto;
import com.demo.quartz.repository.member.*;
import com.demo.quartz.utils.CryptoUtil;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RepositoryTest {
    @Value("${dev.security.db.key}")
    private String devSecurityDbKey;
    @Value("${stg.security.db.key}")
    private String stgSecurityDbKey;
    @Value("${prod.security.db.key}")
    private String prodSecurityDbKey;

    @Autowired
    private FamilyHealthCheckupCryptoRepository familyHealthCheckupCryptoRepository;
    @Autowired
    private HealthCheckupAnalysisPdfCryptoRepository healthCheckupAnalysisPdfCryptoRepository;
    @Autowired
    private HealthCheckupBioAgeAnalysisGuideCryptoRepository healthCheckupBioAgeAnalysisGuideCryptoRepository;
    @Autowired
    private HealthCheckupBioAgeLifeExpectancyCryptoRepository healthCheckupBioAgeLifeExpectancyCryptoRepository;
    @Autowired
    private HealthCheckupCryptoRepository healthCheckupCryptoRepository;
    @Autowired
    private HealthCheckupDetailCryptoRepository healthCheckupDetailCryptoRepository;

    @Test
    public void queryTest() throws Exception {
        final String key = CryptoUtil.getCryptoKey(devSecurityDbKey);
        final String iv = CryptoUtil.getCryptoIv(key);

        this.updateFamilyHealthCheckupCrypto();
    }

    private void updateFamilyHealthCheckupCrypto() throws Exception {
        final String devKey = CryptoUtil.getCryptoKey(devSecurityDbKey);
        final String devIv = CryptoUtil.getCryptoIv(devKey);
        final String stgKey = CryptoUtil.getCryptoKey(stgSecurityDbKey);
        final String stgIv = CryptoUtil.getCryptoIv(stgKey);
        final String prodKey = CryptoUtil.getCryptoKey(prodSecurityDbKey);
        final String prodIv = CryptoUtil.getCryptoIv(prodKey);

        List<FamilyHealthCheckupCrypto> familyHealthCheckupCryptoList = familyHealthCheckupCryptoRepository.findAll();
        List<DecodeInfo> stgDecodeInfoList = new ArrayList<>();
        List<DecodeInfo> prodDecodeInfoList = new ArrayList<>();
        List<DecodeInfo> notingDecodeInfoList = new ArrayList<>();

        for (FamilyHealthCheckupCrypto familyHealthCheckupCrypto : familyHealthCheckupCryptoList) {
            String decryptionValue = CryptoUtil.aesDecryption(familyHealthCheckupCrypto.getRiskScore(), devKey, devIv);

            if (StringUtils.isNotEmpty(decryptionValue)) {
                continue;
            }

            decryptionValue = CryptoUtil.aesDecryption(familyHealthCheckupCrypto.getRiskScore(), stgKey, stgIv);

            if (StringUtils.isNotEmpty(decryptionValue)) {
                stgDecodeInfoList.add(DecodeInfo.builder().decodeValue(decryptionValue).decodeProfile("stg").build());
                familyHealthCheckupCrypto.updateRiskScore(CryptoUtil.aesEncryption(decryptionValue, devKey, devIv));
                continue;
            }

            decryptionValue = CryptoUtil.aesDecryption(familyHealthCheckupCrypto.getRiskScore(), prodKey, prodIv);

            if (StringUtils.isNotEmpty(decryptionValue)) {
                prodDecodeInfoList.add(DecodeInfo.builder().decodeValue(decryptionValue).decodeProfile("prod").build());
                familyHealthCheckupCrypto.updateRiskScore(CryptoUtil.aesEncryption(decryptionValue, devKey, devIv));
                continue;
            }

            notingDecodeInfoList.add(DecodeInfo.builder()
                    .decodeValue(String.valueOf(familyHealthCheckupCrypto.getUid())).decodeProfile("noting").build());
        }

        System.out.println(stgDecodeInfoList);
        System.out.println(prodDecodeInfoList);
        System.out.println(notingDecodeInfoList);

//        familyHealthCheckupCryptoRepository.saveAll(familyHealthCheckupCryptoList);
    }

    private void updateHealthCheckupAnalysisPdfCrypto() throws Exception {
        final String devKey = CryptoUtil.getCryptoKey(devSecurityDbKey);
        final String devIv = CryptoUtil.getCryptoIv(devKey);
        final String stgKey = CryptoUtil.getCryptoKey(stgSecurityDbKey);
        final String stgIv = CryptoUtil.getCryptoIv(stgKey);
        final String prodKey = CryptoUtil.getCryptoKey(prodSecurityDbKey);
        final String prodIv = CryptoUtil.getCryptoIv(prodKey);

        List<HealthCheckupAnalysisPdfCrypto> healthCheckupAnalysisPdfCryptoList = healthCheckupAnalysisPdfCryptoRepository.findAll();
        List<DecodeInfo> stgDecodeInfoList = new ArrayList<>();
        List<DecodeInfo> prodDecodeInfoList = new ArrayList<>();
        List<DecodeInfo> notingDecodeInfoList = new ArrayList<>();

        for (HealthCheckupAnalysisPdfCrypto healthCheckupAnalysisPdfCrypto : healthCheckupAnalysisPdfCryptoList) {
            String decryptionValue = CryptoUtil.aesDecryption(healthCheckupAnalysisPdfCrypto.getPdfPath(), devKey, devIv);

            if (StringUtils.isNotEmpty(decryptionValue)) {
                continue;
            }

            decryptionValue = CryptoUtil.aesDecryption(healthCheckupAnalysisPdfCrypto.getPdfPath(), stgKey, stgIv);

            if (StringUtils.isNotEmpty(decryptionValue)) {
                stgDecodeInfoList.add(DecodeInfo.builder().decodeValue(decryptionValue).decodeProfile("stg").build());
                healthCheckupAnalysisPdfCrypto.updatePdfPath(CryptoUtil.aesEncryption(decryptionValue, devKey, devIv));
                continue;
            }

            decryptionValue = CryptoUtil.aesDecryption(healthCheckupAnalysisPdfCrypto.getPdfPath(), prodKey, prodIv);

            if (StringUtils.isNotEmpty(decryptionValue)) {
                prodDecodeInfoList.add(DecodeInfo.builder().decodeValue(decryptionValue).decodeProfile("prod").build());
                healthCheckupAnalysisPdfCrypto.updatePdfPath(CryptoUtil.aesEncryption(decryptionValue, devKey, devIv));
                continue;
            }

            notingDecodeInfoList.add(DecodeInfo.builder()
                    .decodeValue(String.valueOf(healthCheckupAnalysisPdfCrypto.getCheckupDate())).decodeProfile("noting").build());
        }

        System.out.println(stgDecodeInfoList);
        System.out.println(prodDecodeInfoList);
        System.out.println(notingDecodeInfoList);

//        familyHealthCheckupCryptoRepository.saveAll(familyHealthCheckupCryptoList);
    }

    @Builder
    private static class DecodeInfo {
        private String decodeValue;
        private String decodeProfile;
    }
}