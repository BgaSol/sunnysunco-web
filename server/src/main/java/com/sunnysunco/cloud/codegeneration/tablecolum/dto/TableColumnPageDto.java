package com.sunnysunco.cloud.codegeneration.tablecolum.dto;

import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.codegeneration.tablecolum.TableColumnEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "表字段分页查询参数")
public class TableColumnPageDto extends BasePageDto<TableColumnEntity> {
}
