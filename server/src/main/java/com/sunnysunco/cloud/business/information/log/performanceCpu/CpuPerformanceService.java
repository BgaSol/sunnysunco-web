package com.sunnysunco.cloud.business.information.log.performanceCpu;

import com.sunnysunco.cloud.business.base.BaseRepository;
import com.sunnysunco.cloud.business.base.BaseSCMapper;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.information.log.performanceCpu.dto.CpuPerformancePageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CpuPerformanceService extends BaseService<CpuPerformanceEntity, CpuPerformancePageDto> {
    private final CpuPerformanceMapper cpuPerformanceMapper;

    private final CpuPerformanceRepository cpuPerformanceRepository;

    @Override
    public BaseSCMapper<CpuPerformanceEntity> commonBaseMapper() {
        return cpuPerformanceMapper;
    }

    @Override
    public BaseRepository<CpuPerformanceEntity> commonBaseRepository() {
        return cpuPerformanceRepository;
    }
}
