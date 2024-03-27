package com.sunnysunco.cloud.codegeneration.tablecolum;

import com.sunnysunco.cloud.business.base.BaseRepository;
import com.sunnysunco.cloud.business.base.BaseSCMapper;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.codegeneration.tablecolum.dto.TableColumPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TableColumService extends BaseService<TableColumEntity, TableColumPageDto> {

    private final TableColumRepository tableColumRepository;

    private final TableColumMapper tableColumMapper;

    @Override
    public BaseSCMapper<TableColumEntity> commonBaseMapper() {
        return tableColumMapper;
    }

    @Override
    public BaseRepository<TableColumEntity> commonBaseRepository() {
        return tableColumRepository;
    }
}
