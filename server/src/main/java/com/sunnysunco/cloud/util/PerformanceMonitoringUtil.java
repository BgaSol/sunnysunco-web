package com.sunnysunco.cloud.util;

import com.sunnysunco.cloud.business.information.log.performanceCpu.dto.CPUPerformanceDto;
import com.sunnysunco.cloud.business.information.log.performanceGpu.dto.GPUPerformanceDto;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PerformanceMonitoringUtil {
    // 实例化操作系统对象。
    public final OperatingSystem os;
    // 实例化中央处理器对象。这表示一个或多个逻辑CPU。
    public final CentralProcessor processor;
    // 实例化内存对象。这表示物理内存。
    public final GlobalMemory memory;

    public boolean hasNvidiaGPU = false;

    public PerformanceMonitoringUtil() {
        // 实例化系统对象。
        SystemInfo systemInfo = new SystemInfo();

        // 实例化硬件对象。
        HardwareAbstractionLayer hardware = systemInfo.getHardware();

        // 实例化显卡对象。
        List<GraphicsCard> graphicsCards = hardware.getGraphicsCards();
        os = systemInfo.getOperatingSystem();
        processor = hardware.getProcessor();
        memory = systemInfo.getHardware().getMemory();
        log.info("操作系统: " + os.getFamily());
        log.info("CPU信息: " + processor.getProcessorIdentifier().getName() + " " + processor.getPhysicalProcessorCount() + "核心 " + processor.getLogicalProcessorCount() + "线程");
        for (int i = 0, graphicsCardsSize = graphicsCards.size(); i < graphicsCardsSize; i++) {
            GraphicsCard graphicsCard = graphicsCards.get(i);
            if (graphicsCard.getVendor().equals("NVIDIA")) {
                hasNvidiaGPU = true;
            }
            log.info("GPU" + i + "信息: " + graphicsCard.getName() + " " + graphicsCard.getVRam() / Math.pow(1024, 3) + "GB " + graphicsCard.getVersionInfo());
        }
    }

    public CPUPerformanceDto getCpuAndMemoryData() {
        Date date = new Date();
        // 获取cpu负载
        long[] systemCpuLoadTicks = processor.getSystemCpuLoadTicks();
        long[][] processorCpuLoadTicks = processor.getProcessorCpuLoadTicks();

        Util.sleep(1000);

        double systemCpuLoadBetweenTicks = processor.getSystemCpuLoadBetweenTicks(systemCpuLoadTicks);
        double[] coresLoad = processor.getProcessorCpuLoadBetweenTicks(processorCpuLoadTicks);

        // 获取cpu型号以及cpu使用率
        CPUPerformanceDto cpuPerformanceDto = new CPUPerformanceDto();
        cpuPerformanceDto.setModel(processor.getProcessorIdentifier().getName());
        cpuPerformanceDto.setUsagePercent(systemCpuLoadBetweenTicks * 100);
        cpuPerformanceDto.setCoreUsagePercent(Arrays.stream(coresLoad).mapToObj(v -> v * 100).collect(Collectors.toList()));
        // 获取可用内存
        long availableMemory = memory.getAvailable();
        // 获取总内存
        long totalMemory = memory.getTotal();
        cpuPerformanceDto.setMemoryTotal(totalMemory / (1024.0 * 1024));
        cpuPerformanceDto.setMemoryPercent(100.0 * (totalMemory - availableMemory) / totalMemory);
        cpuPerformanceDto.setMemoryUsage((totalMemory - availableMemory) / (1024.0 * 1024));

        cpuPerformanceDto.setTime(date);
        return cpuPerformanceDto;
    }

    public List<GPUPerformanceDto> getNvidiaGpuData() {
        List<GPUPerformanceDto> gpuPerformanceDtoList = new ArrayList<>();
        Date date = new Date();
        try {
            // 创建一个ProcessBuilder来运行nvidia-smi命令
            ProcessBuilder processBuilder = new ProcessBuilder("nvidia-smi",
                    "--query-gpu=index,name,temperature.gpu,utilization.gpu,memory.total,memory.used,memory.free,fan.speed,power.draw",
                    "--format=csv,noheader,nounits");
            processBuilder.redirectErrorStream(true);
            // 启动进程
            Process process = processBuilder.start();
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            // 读取输出
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                gpuPerformanceDtoList.add(getGpuPerformanceDto(line, date));
            }
            // 等待进程结束
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                log.error("nvidia-smi命令执行失败，退出代码：" + exitCode);
            }

            reader.close();
            process.destroy();
        } catch (IOException | InterruptedException e) {
            log.error("获取nvidia-smi命令执行失败", e);
        }
        return gpuPerformanceDtoList;
    }

    @NotNull
    private static GPUPerformanceDto getGpuPerformanceDto(String line, Date date) {
        String[] gpu = line.split(",");
        GPUPerformanceDto gpuPerformanceDto = new GPUPerformanceDto();
        gpuPerformanceDto.setTime(date);
        gpuPerformanceDto.setIndex(Integer.parseInt(gpu[0].trim()));
        gpuPerformanceDto.setName(gpu[1].trim());
        gpuPerformanceDto.setTemperature(Double.parseDouble(gpu[2].trim()));
        gpuPerformanceDto.setUsagePercent(Double.parseDouble(gpu[3].trim()));
        gpuPerformanceDto.setMemoryTotal(Double.parseDouble(gpu[4].trim()));
        gpuPerformanceDto.setMemoryUsage(Double.parseDouble(gpu[5].trim()));
        gpuPerformanceDto.setMemoryPercent(gpuPerformanceDto.getMemoryUsage() / gpuPerformanceDto.getMemoryTotal() * 100);
        gpuPerformanceDto.setFan(gpu[7].trim());
        gpuPerformanceDto.setPower(Double.parseDouble(gpu[8].trim()));
        return gpuPerformanceDto;
    }
}
