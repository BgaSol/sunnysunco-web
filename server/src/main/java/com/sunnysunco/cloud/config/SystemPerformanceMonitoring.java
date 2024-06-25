package com.sunnysunco.cloud.config;

import com.sunnysunco.cloud.business.information.log.performanceCpu.CpuPerformanceService;
import com.sunnysunco.cloud.business.information.log.performanceCpu.dto.CPUPerformanceDto;
import com.sunnysunco.cloud.business.information.log.performanceGpu.GpuPerformanceService;
import com.sunnysunco.cloud.business.information.log.performanceGpu.dto.GPUPerformanceDto;
import com.sunnysunco.cloud.util.PerformanceMonitoringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@EnableAsync
@EnableScheduling
public class SystemPerformanceMonitoring {
    private final PerformanceMonitoringUtil performanceMonitoringUtil;

    private final CpuPerformanceService cpuPerformanceService;

    private final GpuPerformanceService gpuPerformanceService;

    @Async
    @Scheduled(fixedRate = 5 * 1000, initialDelay = 10 * 1000)
    public void getCpuPerformance() {
        CPUPerformanceDto cpuAndMemoryData = performanceMonitoringUtil.getCpuAndMemoryData();
        cpuPerformanceService.save(cpuAndMemoryData.toEntity());
    }

    @Async
    @Scheduled(fixedRate = 5000, initialDelay = 10 * 1000)
    public void getGpuPerformance() {
        if (performanceMonitoringUtil.hasNvidiaGPU) {
            List<GPUPerformanceDto> nvidiaGpuData = performanceMonitoringUtil.getNvidiaGpuData();
            for (GPUPerformanceDto gpuPerformanceDto : nvidiaGpuData) {
                gpuPerformanceService.save(gpuPerformanceDto.toEntity());
            }
        }
    }
}
