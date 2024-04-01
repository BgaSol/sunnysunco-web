package com.sunnysunco.cloud.codegeneration.tablecolum;

import com.sunnysunco.cloud.business.base.BaseRepository;
import com.sunnysunco.cloud.business.base.BaseSCMapper;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.codegeneration.tablecolum.dto.TableColumnPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TableColumnService extends BaseService<TableColumnEntity, TableColumnPageDto> {

    private final TableColumnRepository tableColumnRepository;

    private final TableColumnMapper tableColumnMapper;

    @Override
    public BaseSCMapper<TableColumnEntity> commonBaseMapper() {
        return tableColumnMapper;
    }

    @Override
    public BaseRepository<TableColumnEntity> commonBaseRepository() {
        return tableColumnRepository;
    }
}
