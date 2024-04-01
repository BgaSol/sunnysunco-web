package com.sunnysunco.cloud.codegeneration.tablecolum.vo;

import com.sunnysunco.cloud.codegeneration.tablecolum.TableColumnEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class TableColumnVo extends TableColumnEntity {
    public TableColumnVo(TableColumnEntity tableColumnEntity) {

    }
}
