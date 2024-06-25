package com.sunnysunco.cloud.business.information.log.performanceGpu;

import com.sunnysunco.cloud.business.base.BaseRepository;
import com.sunnysunco.cloud.business.base.BaseSCMapper;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.information.log.performanceGpu.dto.GpuPerformancePageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GpuPerformanceService extends BaseService<GpuPerformanceEntity, GpuPerformancePageDto> {
    private final GpuPerformanceMapper gpuPerformanceMapper;

    private final GpuPerformanceRepository gpuPerformanceRepository;

    @Override
    public BaseSCMapper<GpuPerformanceEntity> commonBaseMapper() {
        return gpuPerformanceMapper;
    }

    @Override
    public BaseRepository<GpuPerformanceEntity> commonBaseRepository() {
        return gpuPerformanceRepository;
    }
}
