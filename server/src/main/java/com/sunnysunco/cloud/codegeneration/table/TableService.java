package com.sunnysunco.cloud.codegeneration.table;

import com.sunnysunco.cloud.business.base.BaseRepository;
import com.sunnysunco.cloud.business.base.BaseSCMapper;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.codegeneration.table.dto.TablePageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TableService extends BaseService<TableEntity, TablePageDto> {

    private final TableRepository tableRepository;

    private final TableMapper tableMapper;

    @Override
    public BaseSCMapper<TableEntity> commonBaseMapper() {
        return tableMapper;
    }

    @Override
    public BaseRepository<TableEntity> commonBaseRepository() {
        return tableRepository;
    }
}
