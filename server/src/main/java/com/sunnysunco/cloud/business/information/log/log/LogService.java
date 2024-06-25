package com.sunnysunco.cloud.business.information.log.log;

import com.sunnysunco.cloud.business.base.BaseRepository;
import com.sunnysunco.cloud.business.base.BaseSCMapper;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.information.log.log.dto.LogPageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LogService extends BaseService<LogEntity, LogPageDto> {
    private final LogMapper logMapper;

    private final LogRepository logRepository;

    @Override
    public BaseSCMapper<LogEntity> commonBaseMapper() {
        return logMapper;
    }

    @Override
    public BaseRepository<LogEntity> commonBaseRepository() {
        return logRepository;
    }
}
